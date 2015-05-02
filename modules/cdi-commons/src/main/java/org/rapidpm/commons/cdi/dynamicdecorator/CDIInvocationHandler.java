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

package org.rapidpm.commons.cdi.dynamicdecorator;

import org.rapidpm.commons.cdi.logger.CDILogger;
import org.rapidpm.commons.cdi.logger.Logger;

import javax.inject.Inject;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sven Ruppert on 09.01.14.
 */
public class CDIInvocationHandler implements InvocationHandler {

    @Inject @CDILogger Logger logger;

    private Map<MethodIdentifier, Method> adaptedMethods = new HashMap<>();

    private Object adapter;
    private Object adaptee;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (adaptedMethods.isEmpty()){
            final Class<?> adapterClass = adapter.getClass();
            Method[] methods = adapterClass.getDeclaredMethods();
            for (Method m : methods) {
                adaptedMethods.put(new MethodIdentifier(m), m);
            }
        }else{
            if (logger.isDebugEnabled()) {
                logger.debug("adaptedMethods is initialized..");
            }
        }
        try {
            Method other = adaptedMethods.get(new MethodIdentifier(method));
            if (other != null) {
                return other.invoke(adapter, args);
            } else {
                return method.invoke(adaptee, args);
            }
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }

    public CDIInvocationHandler adapter(final Object adapter) {
        this.adapter = adapter;
        return this;
    }

    public CDIInvocationHandler adaptee(final Object adaptee) {
        this.adaptee = adaptee;
        return this;
    }


}
