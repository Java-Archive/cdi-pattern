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

package org.rapidpm.demo.producers.dynamic;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.util.AnnotationLiteral;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sven Ruppert on 02.11.2014.
 */
public class DynamicIntegerProducer implements Bean<Integer> {

  @SuppressWarnings("all")
  public static class DefaultAnnotationLiteral extends AnnotationLiteral<Default> implements Default {
    private static final long serialVersionUID = 1L;
  }

  private final DefaultAnnotationLiteral defaultAnnotationLiteral = new DefaultAnnotationLiteral();

  @Override
  public Class<?> getBeanClass() {
    return Integer.class;
  }

  @Override
  public Set<Type> getTypes() {
    return new HashSet<>(Arrays.asList(Integer.class, Object.class));
  }

  @Override
  public Integer create(CreationalContext<Integer> creationalContext) {
    return new Integer(5);
  }

  @Override
  public Set<Annotation> getQualifiers() {
    return Collections.singleton(defaultAnnotationLiteral);
  }

  @Override
  public Class<? extends Annotation> getScope() {
    return Dependent.class;
  }

  @Override
  public Set<Class<? extends Annotation>> getStereotypes() {
    return Collections.emptySet();
  }

  @Override
  public Set<InjectionPoint> getInjectionPoints() {
    return Collections.emptySet();
  }

  @Override
  public boolean isAlternative() {
    return false;
  }

  @Override
  public boolean isNullable() {
    return false;
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public void destroy(Integer instance, CreationalContext<Integer> creationalContext) {

  }
}
