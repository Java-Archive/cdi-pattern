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

package org.rapidpm.commons.cdi.async;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.rapidpm.commons.cdi.logger.CDILogger;
import org.rapidpm.commons.cdi.logger.Logger;

/**
 * User: Sven Ruppert
 * Date: 31.07.13
 * Time: 10:54
 */

@Interceptor
@Async
public class AsyncInterceptor implements Serializable {
    private static final long serialVersionUID = 7938266823530974338L;


//    private static final Logger logger = Logger.getLogger(AsyncInterceptor.class);
    @Inject @CDILogger Logger logger;

    @AroundInvoke
    public Object executeAsynchronous(InvocationContext invocationContext) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        try {
            Future result = executor.submit(new AsyncExecutor(invocationContext));

            final Method method = invocationContext.getMethod();
            Class returnType = method.getReturnType();
            final boolean isVoid = "void".equalsIgnoreCase(returnType.getName());
            final boolean assignableFrom = Void.class.isAssignableFrom(returnType);
            if (isVoid || assignableFrom) {
                return null;
            } else {
                final FutureUnwrapper futureUnwrapper = new FutureUnwrapper(result);
                if (logger.isDebugEnabled()) {
                    logger.debug("futureUnwrapper - " + futureUnwrapper);
                }
                return futureUnwrapper;
            }
        } finally {
            executor.shutdown(); //won't stop immediately
        }
    }
}