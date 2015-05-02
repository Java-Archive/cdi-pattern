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

package org.rapidpm.demo.cdi.commons.annotation;

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
import org.rapidpm.demo.cdi.commons.annotation.cdi.DemoAnnotation;
import org.rapidpm.demo.cdi.commons.annotation.impl.BusinessLogic;
import org.rapidpm.commons.cdi.logger.Logger;

import javax.inject.Inject;

/**
 * Created by Sven Ruppert on 23.05.2014.
 */

@RunWith(Arquillian.class)
public class CDIAnnotationDemo {

  @Deployment
  public static JavaArchive createDeployment() {
    return ShrinkWrap.create(JavaArchive.class)
        .addPackages(true, "org.rapidpm.demo")
        .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
  }

  @Before
  public void before() throws Exception {
  }

  @After
  public void after() throws Exception {
  }


  @Inject @DemoAnnotation BusinessLogic businessLogic;

  /**
   * Method: produceLog4JLogger(InjectionPoint injectionPoint)
   */
  @Test
  public void testProduceLog4JLogger() throws Exception {
    System.out.println(businessLogic.doMore());
  }
}
