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

package org.rapidpm.demo.cdi.commons.tx;

/**
 * User: Sven Ruppert
 * Date: 29.10.13
 * Time: 11:59
 */
public class CallerClassResolver {

    public static String getCallerClassName() {
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        for (int i=1; i<stElements.length; i++) {
            StackTraceElement ste = stElements[i];
            if (!ste.getClassName().equals(CallerClassResolver.class.getName()) && ste.getClassName().indexOf("java.lang.Thread")!=0) {
                return ste.getClassName();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        final ClassA classA = new ClassA();
        classA.doIt();
    }

    public static class ClassA {
        public void doIt(){
            System.out.println("getCallerClassName() = " + getCallerClassName());
            final ClassB b = new ClassB();
            b.doIt();
        }


    }
    public static class ClassB {

        public void doIt(){
            System.out.println("getCallerClassName() = " + getCallerClassName());
            final ClassC c = new ClassC();
            c.doIt();
        }
    }
    public static class ClassC {

        public void doIt(){
            System.out.println("getCallerClassName() = " + getCallerClassName());
            final ClassD d = new ClassD();
            d.doIt();
        }


    }
    public static class ClassD {

        public void doIt(){
            System.out.println("getCallerClassName() = " + getCallerClassName());
//            final ClassA a = new ClassA();
//            a.doIt();
        }


    }




}
