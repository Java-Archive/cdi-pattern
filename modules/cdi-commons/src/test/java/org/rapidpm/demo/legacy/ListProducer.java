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

import org.rapidpm.commons.cdi.CDICommons;
import org.rapidpm.commons.cdi.contextresolver.ContextResolver;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Sven Ruppert
 * Date: 02.08.13
 * Time: 07:15
 */
public class ListProducer {

  @Produces
  @CDILegacyTest
  public List createList(InjectionPoint injectionPoint, BeanManager beanManager,
                         @CDICommons ContextResolver contextResolver) {
    //treffen der Entscheidungen...

    return new ArrayList();
  }
}
