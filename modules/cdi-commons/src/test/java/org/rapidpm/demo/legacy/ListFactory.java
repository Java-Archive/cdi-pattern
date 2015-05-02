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

import org.rapidpm.commons.cdi.contextresolver.ContextResolver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by Sven Ruppert on 02.08.13.
 */
public class ListFactory {


  public List createArrayList() {
    return new ArrayList();
  }

  public List createLinkedList() {
    return new LinkedList();
  }

  public List createList() {
    return new ArrayList();
  }

  public List createList(final ContextResolver contextResolver) {
    //trivial Implementierung
    if (contextResolver == null) {
      return createArrayList();
    } else {
      //triviale Fallunterscheidung
      if (contextResolver.resolveContext(List.class).equals(null)) {
        return createArrayList();
      } else {
        return createLinkedList();
      }
    }
  }
}
