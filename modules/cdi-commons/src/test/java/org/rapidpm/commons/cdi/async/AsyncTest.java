/*
 * Copyright [2013] [www.rapidpm.org / Sven Ruppert (sven.ruppert@rapidpm.org)]
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.rapidpm.commons.cdi.async;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.Asset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rapidpm.commons.cdi.logger.CDILogger;
import org.rapidpm.commons.cdi.logger.Logger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * User: Sven Ruppert
 * Date: 31.07.13
 * Time: 10:58
 */
@RunWith(Arquillian.class)
public class AsyncTest {
//    @Deployment
//    public static WebArchive createTestArchive()
//    {
//        Asset beansXml = new StringAsset("<beans>" +
//                "<interceptors><class>AsyncInterceptor</class></interceptors>" +
//                "</beans>");
//        return ShrinkWrap.create(WebArchive.class, "async-cdi-test.war")
//                .addAsWebInfResource(beansXml, "beans.xml")
//                .addPackage(AsyncTestEvent.class.getPackage())
//
//                .addAsLibraries(ShrinkWrap.create(JavaArchive.class, "async-cdi-lib.jar")
//                        .addPackages(true, Async.class.getPackage())
//                        .addPackages(true, "org.rapidpm.demo.cdi")
//                        .addPackages(true, AsyncInterceptor.class.getPackage())
//                        .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
//                );
//    }

    @Deployment
    public static JavaArchive createDeployment() {
        Asset beansXml = new StringAsset("<beans>" +
                "<interceptors><class>org.rapidpm.commons.cdi.async.AsyncInterceptor</class></interceptors>" +
                "</beans>");

        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, "org.rapidpm")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
//                .addAsManifestResource(beansXml, "beans.xml");
    }

    //private static final Logger logger = Logger.getLogger(AsyncTest.class);
    private
    @Inject
    @CDILogger
    Logger logger;

    @Inject
    private TestService1 testService1;

    @Inject
    private TestService2 testService2;

    @Inject
    private TestObserver testObserver;

    @Inject
    private Event<AsyncTestEvent> asyncEvent;

    @Test @Ignore
    public void testAsyncService1() {
        assertNotNull(testService1);

        testService1.call();
        assertFalse(testService1.isCalled());


        try {
            TestService1.TEST_LATCH.await(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            fail();
        }

        assertTrue(testService1.isCalled());
    }

    @Test @Ignore
    public void testAsyncService2() {
        assertNotNull(testService2);

        Future<Boolean> result = testService2.call();

        assertFalse(testService2.isCalled());

        try {
            assertTrue(result.get(10, TimeUnit.SECONDS));
        } catch (Exception e) {
            fail();
        }
        assertTrue(result.isDone());
        assertTrue(testService2.isCalled());
    }

    @Test @Ignore
    //@Ignore //works with owb 1.2+ and versions of tomee which are based on it
    //owb 1.1.x uses Method#isAccessible instead of Modifier.isPublic(method.getModifiers()) to check if the (interceptor-)proxy should be used
    public void testAsyncEvent() {
        assertNotNull(testObserver);
        assertNotNull(asyncEvent);

        asyncEvent.fire(new AsyncTestEvent());

        assertFalse(testObserver.isCalled());

        try {
            TestObserver.TEST_LATCH.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            if (logger.isDebugEnabled()) {
                logger.debug(" InterruptedException e " + e);
            }
            fail();
        }

        assertTrue(testObserver.isCalled());
    }
}
