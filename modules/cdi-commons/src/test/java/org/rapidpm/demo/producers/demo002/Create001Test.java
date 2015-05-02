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

package org.rapidpm.demo.producers.demo002;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rapidpm.demo.producers.demo002.impl_a.Impl_A;
import org.rapidpm.demo.producers.demo002.impl_b.Impl_B;

import javax.inject.Inject;

/**
 * Created by Sven Ruppert on 02.11.2014.
 */
@RunWith(Arquillian.class)
@Ignore
public class Create001Test {
  @Deployment
  public static JavaArchive createDeployment() {
    return ShrinkWrap.create(JavaArchive.class)
        .addPackages(true, "org.rapidpm")
        .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
  }


  @Inject @Demo002 DemoInterface demo;
  @Inject @Impl_A DemoInterface demoKlasse_a;
  @Inject @Impl_B DemoInterface demoKlasse_b;

  @Test
  public void testCreate000() throws Exception {
    Assert.assertNotNull(demo);
    demo.doSomething();
  }

  @Test
  public void testCreate001() throws Exception {
    Assert.assertNotNull(demoKlasse_a);
    demoKlasse_a.doSomething();
  }

  @Test
  public void testCreate002() throws Exception {
    Assert.assertNotNull(demoKlasse_b);
    demoKlasse_b.doSomething();
  }
}
