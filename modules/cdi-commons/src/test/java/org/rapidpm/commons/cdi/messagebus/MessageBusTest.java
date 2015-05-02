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

package org.rapidpm.commons.cdi.messagebus;

import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rapidpm.commons.cdi.messagebus.model.TestCallbackData;

/**
 * User: Sven Ruppert
 * Date: 01.08.13
 * Time: 14:58
 */
@RunWith(Arquillian.class)
public class MessageBusTest {

  @Deployment
  public static JavaArchive createDeployment() {
      JavaArchive javaArchive = ShrinkWrap.create(JavaArchive.class)
              .addPackages(true, "org.rapidpm")
//        .addPackages(true, "com.google")
//        .addPackages(true, "javassist")
//        .addPackages(true, "org")
              .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
      System.out.println(javaArchive.toString(true));
      return javaArchive;
  }


  @Inject @CDIMessageBus MessageBus messageBus;

  @Test
  public void testMessageBus001() throws Exception {
    Assert.assertNotNull(messageBus);

    //someone that want to get the message - start
//    final MessageBusCallback<TestCallbackData> callBack = new MessageBusCallback<TestCallbackData>() {
//      @Override
//      public void recordCallbackMessage(Message<TestCallbackData> m) {
//        Assert.assertNotNull(m);
//        final TestCallbackData value = m.getValue();
//        Assert.assertNotNull(value.getValueLong());
//        Assert.assertEquals(-1L, value.getValueLong().longValue());
//
//        Assert.assertNotNull(value.getValueTxt());
//        Assert.assertEquals("AEAEAE", value.getValueTxt());
//      }
//    };

//    final MessageBusCallback<TestCallbackData> callBack = new MessageBusCallback<>();
//    callBack.setCallBackAction(m->{
//        Assert.assertNotNull(m);
//        final TestCallbackData value = m.getValue();
//        Assert.assertNotNull(value.getValueLong());
//        Assert.assertEquals(-1L, value.getValueLong().longValue());
//
//        Assert.assertNotNull(value.getValueTxt());
//        Assert.assertEquals("AEAEAE", value.getValueTxt());
//    });
//
//    messageBus.registerCallBack(callBack);

    final String callbackUID = "testCallBack";
    messageBus.<TestCallbackData>registerCallBack(callbackUID, m -> {
        Assert.assertNotNull(m);
        final TestCallbackData value = m.getValue();
        Assert.assertNotNull(value.getValueLong());
        Assert.assertEquals(-1L, value.getValueLong().longValue());

        Assert.assertNotNull(value.getValueTxt());
        Assert.assertEquals("AEAEAE", value.getValueTxt());
    });



    //someone that want to get the message - stop

    //someone that want to give the message - start
    final TestCallbackData testCallbackData = new TestCallbackData();
    testCallbackData.setValueTxt("AEAEAE");
    testCallbackData.setValueLong(-1L);

    final Message<TestCallbackData> message = new Message<>();

    message.setAnnotationLiteral(new AnnotationLiteral<TestCDIMessageBus>() { });

    message.setValue(testCallbackData);
    messageBus.post(message);
    //someone that want to give the message - stop


    //no listening enymore
    messageBus.destroyCallBack(callbackUID);

  }
}
