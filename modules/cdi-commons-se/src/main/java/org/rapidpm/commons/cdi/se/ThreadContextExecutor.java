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

package org.rapidpm.commons.cdi.se;

import org.jboss.weld.environment.se.contexts.ThreadScoped;
import org.rapidpm.commons.cdi.ManagedInstanceCreator;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Sven Ruppert
 * Date: 29.10.13
 * Time: 13:36
 */

@ThreadScoped
@CDICommonsSE
public class ThreadContextExecutor implements Runnable {

  @Inject private ManagedInstanceCreator managedInstanceCreator;

  private List<CDITransactionStep> steps = new ArrayList<>();

  public void addStep(final CDITransactionStep step) {
    steps.add(step);
  }


  @Override
  public void run() {
    for (final CDITransactionStep stepNoCDI : steps) {
      final CDITransactionStep step = managedInstanceCreator.activateCDI(stepNoCDI);
      step.doIt();
    }
  }

  @ThreadScoped
  public abstract static  class CDITransactionStep {

    protected CDITransactionStep() {

    }

    public abstract void doIt();
  }


}
