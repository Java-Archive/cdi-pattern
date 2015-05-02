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

import java.util.concurrent.CountDownLatch;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.rapidpm.commons.cdi.logger.Logger;

/**
 * User: Sven Ruppert
 * Date: 31.07.13
 * Time: 11:01
 */
@ApplicationScoped
public class TestObserver {
    static final CountDownLatch TEST_LATCH = new CountDownLatch(1);
    private static final Logger logger = Logger.getLogger(TestObserver.class);

    private boolean called = false;

    @Async
    public void observeAsync(@Observes AsyncTestEvent testEvent) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("testEvent " + testEvent);
            }
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
        this.called = true;
        TEST_LATCH.countDown();
    }

    public boolean isCalled() {
        return called;
    }
}
