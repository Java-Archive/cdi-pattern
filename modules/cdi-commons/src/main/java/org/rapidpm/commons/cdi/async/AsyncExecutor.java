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

import javax.interceptor.InvocationContext;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * User: Sven Ruppert
 * Date: 31.07.13
 * Time: 10:51
 */
public class AsyncExecutor implements Callable<Future<Object>> {
  private final InvocationContext invocationContext;
//    private static final Logger logger = new Logger(AsyncExecutor.class);
//    private final ContextControl contextControl;

  //    AsyncExecutor(InvocationContext invocationContext, Instance<ContextControl> contextControlInstance)
  public AsyncExecutor(InvocationContext invocationContext) {
    this.invocationContext = invocationContext;
  }

  @Override
  public Future<Object> call() throws Exception {
    try {
      Object result = invocationContext.proceed();
      if (result instanceof Future) {
        return (Future) result;
      } else {
        return null;
      }
    } catch (Exception e) {
      System.out.println("AsyncExecutor e = " + e);
//            throw ExceptionUtils.throwAsRuntimeException(e);
    } finally {

    }
    return null;
  }
}