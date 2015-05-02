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

import java.util.concurrent.Future;

import javax.enterprise.context.ApplicationScoped;

/**
 * User: Sven Ruppert
 * Date: 31.07.13
 * Time: 11:02
 */
@ApplicationScoped
public class TestService2 {
    private boolean called = false;

    @Async
    public Future<Boolean> call() {
        try {
            System.out.println("TestService2 called = " + called);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
        this.called = true;
        return new AsyncTestResult<Boolean>(called);
    }

    public boolean isCalled() {
        return called;
    }
}
