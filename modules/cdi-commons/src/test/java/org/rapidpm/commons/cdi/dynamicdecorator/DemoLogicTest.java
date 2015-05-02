/*
 * Copyright [2014] [www.rapidpm.org / Sven Ruppert (sven.ruppert@rapidpm.org)]
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

package org.rapidpm.commons.cdi.dynamicdecorator;

import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

/**
 * Created by Sven Ruppert on 09.01.14.
 */
@RunWith(Arquillian.class)
public class DemoLogicTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, "org.rapidpm")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject @DynamicDecoratorTest Instance<DemoLogic> demoLogic;
    @Inject Context context;
    @Test
    public void testDemoLogicOriginalTest() throws Exception {
        Assert.assertNotNull(demoLogic);
        final DemoLogic demoLogic1 = demoLogic.get();
        final int add = demoLogic1.add(1, 1);
        Assert.assertEquals(2,add);
        System.out.println("add = " + add);

        context.original = false;
        final DemoLogic demoLogic2 = demoLogic.get();
        final int addAdapted = demoLogic2.add(1, 1);
        Assert.assertEquals(102,addAdapted);
        System.out.println("addAdapted = " + addAdapted);

    }


}
