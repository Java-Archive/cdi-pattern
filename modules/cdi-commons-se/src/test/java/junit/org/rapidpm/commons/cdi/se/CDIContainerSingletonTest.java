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

package junit.org.rapidpm.commons.cdi.se;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rapidpm.commons.cdi.se.CDIContainerSingleton;

/**
 * CDIContainerSingleton Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Jun 5, 2013</pre>
 */
public class CDIContainerSingletonTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    public static class TestClassDemoA {
        private String str = "A - " + System.nanoTime();

        @Inject TestClassDemoB testClassDemoB;

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("TestClassDemoA{");
            sb.append("str='").append(str).append('\'');
            sb.append(", testClassDemoB=").append(testClassDemoB);
            sb.append('}');
            return sb.toString();
        }

    }


    public static class TestClassDemoB {
        private String str = "B - " + System.nanoTime();

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("TestClassDemoB{");
            sb.append("str='").append(str).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    /**
     * Method: getInstance()
     */
    @Test
    public void testGetInstance() throws Exception {
        final CDIContainerSingleton instance = CDIContainerSingleton.getInstance();
        Assert.assertNotNull(instance);
    }

    /**
     * Method: getManagedInstance(final Class<T> clazz)
     */
    @Test
    public void testGetManagedInstance() throws Exception {
        final CDIContainerSingleton instance = CDIContainerSingleton.getInstance();
        Assert.assertNotNull(instance);
        final TestClassDemoA classDemoA = instance.getManagedInstance(TestClassDemoA.class);
        Assert.assertNotNull(classDemoA);

    }

    /**
     * Method: getInstanceReference(final Class<T> clazz)
     */
    @Test
    public void testGetInstanceReference() throws Exception {
        final CDIContainerSingleton instance = CDIContainerSingleton.getInstance();
        Assert.assertNotNull(instance);
        final Instance<TestClassDemoA> ref = instance.getInstanceReference(TestClassDemoA.class);
        Assert.assertNotNull(ref);
        final boolean unsatisfied = ref.isUnsatisfied();
        //Assert.assertFalse(unsatisfied);
        final TestClassDemoA s = ref.get();
        Assert.assertNotNull(s);
        Assert.assertNotNull(s.testClassDemoB);

        System.out.println("s = " + s);

    }


} 
