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

package org.rapidpm.commons.cdi.se;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.rapidpm.commons.cdi.ManagedInstanceCreator;
import org.rapidpm.commons.cdi.logger.Logger;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.util.AnnotationLiteral;

/**
 * Created with IntelliJ IDEA.
 * User: Sven Ruppert
 * Date: 05.06.13
 * Time: 22:07
 * <p></p>
 * A Singleton for the SE Applikation.
 */
public class CDIContainerSingleton {

  private  static final CDIContainerSingleton OUR_INSTANCE = new CDIContainerSingleton();
  private final WeldContainer weldContainer;
  private final Logger logger;
  private final ManagedInstanceCreator managedInstanceCreator;

  private CDIContainerSingleton() {
    weldContainer = new Weld().initialize();
    logger = weldContainer.instance().select(Logger.class).get();  //bootstrapping but with Weld itself ;-)
    managedInstanceCreator = weldContainer.instance().select(ManagedInstanceCreator.class).get();
  }

  public static CDIContainerSingleton getInstance() {
    return OUR_INSTANCE;
  }

  public <T> T activateCDI(T t) {
    return managedInstanceCreator.activateCDI(t);
  }


  public <T> T getManagedInstance(final Class<T> clazz) {
    if (logger.isDebugEnabled()) {
      logger.debug("managed instance " + clazz);
    }
    final Instance<T> ref = getInstanceReference(clazz);
    return ref.get();
  }

  public <T> Instance<T> getInstanceReference(final Class<T> clazz) {
    if (logger.isDebugEnabled()) {
      logger.debug("InstanceReference - class " + clazz);
    }
    return weldContainer.instance().select(clazz);
  }

  public <T> T getManagedInstance(final AnnotationLiteral literal, final Class<T> clazz) {
    if (logger.isDebugEnabled()) {
      logger.debug("managed instance " + clazz);
      logger.debug("AnnotationLiteral - literal " + literal);
    }
    final Instance<T> ref = getInstanceReference(literal, clazz);
    return ref.get();
  }

  public <T> Instance<T> getInstanceReference(final AnnotationLiteral literal, final Class<T> clazz) {
    if (logger.isDebugEnabled()) {
      logger.debug("InstanceReference - class " + clazz);
      logger.debug("AnnotationLiteral - literal " + literal);
    }
    return weldContainer.instance().select(clazz, literal);
  }

  public void fireEvent(final Object o) {
    weldContainer.event().fire(o);
  }

  public Event<Object> event() {
    return weldContainer.event();
  }

  public BeanManager getBeanManager() {
    return weldContainer.getBeanManager();
  }
}
