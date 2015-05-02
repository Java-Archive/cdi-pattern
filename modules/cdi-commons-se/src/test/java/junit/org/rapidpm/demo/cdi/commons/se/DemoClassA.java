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

package junit.org.rapidpm.demo.cdi.commons.se;

import java.util.Objects;

import org.jboss.weld.environment.se.contexts.ThreadScoped;

/**
 * User: Sven Ruppert
 * Date: 29.10.13
 * Time: 13:46
 */

@ThreadScoped
public class DemoClassA {


    private String txNumber = System.nanoTime()+"";

    /**
     * Gets tx number.
     *
     * @return the tx number
     */
    public String getTxNumber() {
        return txNumber;
    }

    /**
     * Sets tx number.
     *
     * @param txNumber the tx number
     */
    public void setTxNumber(String txNumber) {
        this.txNumber = txNumber;
    }


    @Override public int hashCode() {
        return Objects.hash(txNumber);
    }

    @Override public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final DemoClassA other = (DemoClassA) obj;
        return Objects.equals(this.txNumber, other.txNumber);
    }

    @Override public String toString() {
        final StringBuilder sb = new StringBuilder("DemoClassA{");
        sb.append("txNumber='").append(txNumber).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
