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

import com.google.common.collect.Maps;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.rapidpm.commons.cdi.logger.CDILogger;
import org.rapidpm.commons.cdi.logger.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.Serializable;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;

/**
 * User: Sven Ruppert
 * Date: 31.07.13
 * Time: 10:16
 */
@Singleton //zu hart def
public class MessageBus implements Serializable {

  //    private EventBus eventBus = new AsyncEventBus(this.getClass().getName(), Executors.newCachedThreadPool());
  private EventBus eventBus = new EventBus(this.getClass().getName());

  //damit nicht mehr die Callback referenzen gehalten werden muessen
  private ConcurrentMap<String, MessageBusCallback> callbacks = Maps.newConcurrentMap();

  private @Inject @CDILogger Logger logger;

  public <T> void registerCallBack(final String callbackUID, Consumer<Message<T>> m) {
    if (logger.isDebugEnabled()) {
      logger.debug("registerCallBack with UID" + callbackUID);
    }
    final MessageBusCallback<T> callback = this.new MessageBusCallback<>();
    callback.setCallBackAction(m);
    callbacks.put(callbackUID, callback);
    eventBus.register(callback);
  }

//  public void destroyCallBack(MessageBusCallback callBack) {
//    if (logger.isDebugEnabled()) {
//      logger.debug("destroyCallBack " + callBack);
//    }
//    eventBus.unregister(callBack);
//  }
  public void destroyCallBack(final String callbackUID) {
    if (logger.isDebugEnabled()) {
      logger.debug("destroyCallBack with UID" + callbackUID);
    }

    if (callbacks.containsKey(callbackUID)) {
      eventBus.unregister(callbacks.get(callbackUID));
    } else {
      logger.warn("Callback with the following UID was not registered. " + callbackUID);
    }
  }




  public void post(Message message) {
    if (logger.isDebugEnabled()) {
      logger.debug("post " + message);
    }
    eventBus.post(message);
  }



  private class MessageBusCallback<T> {

    private Consumer<Message<T>> c;

    @Subscribe
    public void recordCallbackMessage(Message<T> m) {
      c.accept(m);
    }

    public void setCallBackAction(Consumer<Message<T>> m){
      this.c = m;
    }



  }

}
