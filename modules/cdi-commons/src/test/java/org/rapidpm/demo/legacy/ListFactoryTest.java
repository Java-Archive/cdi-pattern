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

package org.rapidpm.demo.legacy;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * Created by Sven Ruppert on 02.08.13.
 */
@RunWith(Arquillian.class)
public class ListFactoryTest {

  @Deployment
  public static JavaArchive createDeployment() {
    return ShrinkWrap.create(JavaArchive.class)
        .addPackages(true, "org.rapidpm.commons")
        .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
  }

  @Test
  public void testListFactory001() throws Exception {
    final List list = new ListFactory().createArrayList();
    Assert.assertNotNull(list);
    Assert.assertTrue(list.isEmpty());
  }


//  @Inject @CDILegacyTest Instance<List> listInstance;
//  @Inject @CDILegacyTest List list;


  @Test
  @Ignore  //TODO CDILegacyTest muss wieder hergestellt werden
  public void testListFactory002() throws Exception {

//        final List list = listInstance.select(new AnnotationLiteral<CDILegacyTest>() {}).get();
//    final List list = listInstance.get();
//    Assert.assertNotNull(list);
//    Assert.assertTrue(list.isEmpty());

  }


}
