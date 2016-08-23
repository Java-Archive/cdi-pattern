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

package org.rapidpm.commons.cdi;

import org.rapidpm.commons.cdi.logger.CDILogger;
import org.rapidpm.commons.cdi.logger.Logger;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.InjectionTarget;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import java.util.Set;

/**
 * User: Sven Ruppert
 * Date: 15.10.13
 * Time: 15:06
 */
public class ManagedInstanceCreator {

  @Inject BeanManager beanManager;
   @Inject @CDILogger private Logger logger;

  public <T> T getManagedInstance(final Class<T> beanType, final AnnotationLiteral annotationLiteral) {

    T result = null;

    final Set<Bean<?>> beanSet;
    if (annotationLiteral == null) {
      beanSet = beanManager.getBeans(beanType, new AnnotationLiteral<Default>() {
      });
    } else {
      beanSet = beanManager.getBeans(beanType, annotationLiteral);
    }
    result = beanSet.stream()
        .map((b) -> b.getTypes()
            .stream()
            .filter(t -> t.equals(beanType))
            .findFirst()
            .map((bean) -> {
              final Bean<T> beanTyped = (Bean<T>) b;
              final T t = beanTyped.create(beanManager.createCreationalContext(beanTyped));
              return t;
            })
            .get())
        .findFirst()
        .get();
    return result;
  }

  public <T> T activateCDI(T t) {
    final Class aClass = t.getClass();
    if (logger.isDebugEnabled()) {
      logger.debug("activateCDI-> " + aClass);
    }

    final AnnotatedType annotationType = beanManager.createAnnotatedType(aClass);
    final InjectionTarget injectionTarget = beanManager.createInjectionTarget(annotationType);
    final CreationalContext creationalContext = beanManager.createCreationalContext(null);
    injectionTarget.inject(t, creationalContext);
    injectionTarget.postConstruct(t);
    return t;
  }

}
