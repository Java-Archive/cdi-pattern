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

package org.rapidpm.commons.cdi.format;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rapidpm.commons.cdi.format.CDISimpleDateFormatter;

/**
 * SimpleDateFormatterProducer Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Jun 3, 2013</pre>
 */
@RunWith(Arquillian.class)
public class SimpleDateFormatterProducerTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, "org.rapidpm")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    @Inject @CDISimpleDateFormatter()
    SimpleDateFormat sdf;

    @Inject
    @CDISimpleDateFormatter("date.yyyy")
    SimpleDateFormat sdfYYYY;


    /**
     * Method: createDefault(InjectionPoint injectionPoint)
     */
    @Test
    public void testProduceSimpleDateFormatter() throws Exception {
        Assert.assertNotNull(sdf);
        final Date parse = sdf.parse("2010.12.05");
        final Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(2010, Calendar.DECEMBER, 5, 00, 00, 00);
        final Date date = calendar.getTime();
        Assert.assertTrue(parse.equals(date));
    }

    /**
     * Method: createDefault(InjectionPoint injectionPoint)
     */
    @Test
    public void testProduceSimpleDateFormatter01() throws Exception {
        Assert.assertNotNull(sdfYYYY);
        final Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(2010, Calendar.JANUARY, 1, 00, 00, 00);
        final Date date = calendar.getTime();
        System.out.println("date = " + date);
        final String format = sdfYYYY.format(date);
        System.out.println("format = " + format);
        Assert.assertTrue(format.equals("2010"));
    }

    @Test
    public void testProduceSimpleDateFormatter02() throws Exception {
        Assert.assertNotNull(sdfYYYY);
        final Date parse = sdfYYYY.parse("2010");
        System.out.println("parse = " + parse);
        final Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(2010, Calendar.JANUARY, 1, 00, 00, 00);
        final Date date = calendar.getTime();
        System.out.println("date = " + date);
        Assert.assertTrue(date.equals(parse));
    }


} 
