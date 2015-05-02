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

import org.rapidpm.demo.producers.demo002.impl_a.DemoKlasse_A;
import org.rapidpm.demo.producers.demo002.impl_a.Impl_A;
import org.rapidpm.demo.producers.demo002.impl_b.DemoKlasse_B;
import org.rapidpm.demo.producers.demo002.impl_b.Impl_B;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.*;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sven Ruppert on 02.11.2014.
 */
public class Producer {

  @Inject BeanManager beanManager;
  @Inject Instance<DemoInterface> instance;

  private final AnnotationLiteral literalA = new AnnotationLiteral<Impl_A>() {
  };
  private final AnnotationLiteral literalB = new AnnotationLiteral<Impl_B>() {
  };

  @Produces
  @Demo002
  DemoInterface create() {

    final Bean<DemoInterface> bean = beanManager.createBean(new BeanAttributes<DemoInterface>() {
                                                              @Override
                                                              public Set<Type> getTypes() {
                                                                return new HashSet<>(Arrays.asList(DemoInterface.class, Object.class));
                                                              }

                                                              @Override
                                                              public Set<Annotation> getQualifiers() {
                                                                return Collections.singleton(literalA);
                                                              }

                                                              @Override
                                                              public Class<? extends Annotation> getScope() {
                                                                return Dependent.class;
                                                              }

                                                              @Override
                                                              public String getName() {
                                                                return DemoInterface.class.getName();
                                                              }

                                                              @Override
                                                              public Set<Class<? extends Annotation>> getStereotypes() {
                                                                return Collections.emptySet();
                                                              }

                                                              @Override
                                                              public boolean isAlternative() {
                                                                return false;
                                                              }
                                                            }, DemoInterface.class,
        new ProducerFactory<DemoInterface>() {
          @Override
          public <T> javax.enterprise.inject.spi.Producer<T> createProducer(Bean<T> bean) {
            return new javax.enterprise.inject.spi.Producer<T>() {
              @Override
              public T produce(CreationalContext<T> ctx) {
                final DemoKlasse_B demoKlasse_b = new DemoKlasse_B();
                ctx.push((T) demoKlasse_b);
                return (T) demoKlasse_b; //not managed
              }

              @Override
              public void dispose(T instance) {

              }

              @Override
              public Set<InjectionPoint> getInjectionPoints() {
                return Collections.emptySet();
              }
            };
          }
        });

    System.out.println("bean = " + bean);

    final DemoInterface o = bean.create(new CreationalContext<DemoInterface>() {
      @Override
      public void push(DemoInterface incompleteInstance) {
        System.out.println("incompleteInstance = " + incompleteInstance);
      }

      @Override
      public void release() {

      }
    });
    o.doSomething();


    final Set<Bean<?>> beanSet = beanManager.getBeans(DemoInterface.class, literalA);
    System.out.println("beanSet.size() = " + beanSet.size());

//    return instance.select(new AnnotationLiteral<Impl_A>(){}).get();
    return new DemoKlasse_A();


  }

  @Produces
  @Impl_A
  DemoInterface createA() {
    return new DemoKlasse_A();
  }

  @Produces
  @Impl_B
  DemoInterface createB() {
    return new DemoKlasse_B();
  }


}
