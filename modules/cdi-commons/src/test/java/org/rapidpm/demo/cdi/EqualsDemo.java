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

package org.rapidpm.demo.cdi;

import org.rapidpm.commons.cdi.CDICommons;

import javax.enterprise.util.AnnotationLiteral;

/**
 * Created by Sven Ruppert on 12.11.2014.
 */
public class EqualsDemo {

  public static void main(String[] args) {

    final AnnotationLiteral<CDICommons> a1 = new AnnotationLiteral<CDICommons>() {
    };
    final AnnotationLiteral<CDICommons> a2 = new AnnotationLiteral<CDICommons>() {
    };

    System.out.println("a1.equals(a2) = " + a1.equals(a2));


  }

}
