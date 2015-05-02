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

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * User: Sven Ruppert
 * Date: 31.07.13
 * Time: 10:55
 */
public class FutureUnwrapper<V extends Future<Object>> implements Future<Object> {
    //future which wraps a future
    private final V wrappingFuture;

    FutureUnwrapper(V future) {
        this.wrappingFuture = future;
    }

    public boolean cancel(boolean mayInterruptIfRunning) {
        return this.wrappingFuture.cancel(mayInterruptIfRunning);
    }

    public Object get() throws InterruptedException, ExecutionException {
        Object result = wrappingFuture.get();

        if (result instanceof Future) {
            return ((Future<Object>) result).get();
        }
        return result;
    }

    public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        Object result = wrappingFuture.get(timeout, unit);

        if (result instanceof Future) {
            return ((Future<Object>) result).get();
        }
        return result;
    }

    public boolean isCancelled() {
        return wrappingFuture.isCancelled();
    }

    public boolean isDone() {
        return wrappingFuture.isDone();
    }
}