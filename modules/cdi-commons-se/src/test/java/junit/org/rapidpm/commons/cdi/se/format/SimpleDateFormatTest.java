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

package junit.org.rapidpm.commons.cdi.se.format;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;

import org.junit.*;
import org.rapidpm.commons.cdi.format.CDISimpleDateFormatter;
import org.rapidpm.commons.cdi.format.CDISimpleDateFormatterQualifier;
import org.rapidpm.commons.cdi.se.CDIContainerSingleton;

/**
 * User: Sven Ruppert
 * Date: 20.06.13
 * Time: 16:51
 */
public class SimpleDateFormatTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    public static class TestClassDemoA {
        public String str = "A - " + System.nanoTime();

        @Inject
        @CDISimpleDateFormatter
        SimpleDateFormat sdf;

        @Inject
        @CDISimpleDateFormatter("date.yyyy")
        SimpleDateFormat sdfYYYY;

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
//        final SimpleDateFormat simpleDateFormat = instance.getManagedInstance(new AnnotationLiteral<CDISimpleDateFormatter>() { },SimpleDateFormat.class);
        final SimpleDateFormat simpleDateFormat = instance.getManagedInstance(new CDISimpleDateFormatterQualifier(),SimpleDateFormat.class);
        Assert.assertNotNull(simpleDateFormat);
    }


    /**
     * Method: createDefault(InjectionPoint injectionPoint)
     */
    @Test
    public void testProduceSimpleDateFormatter01() throws Exception {
        final CDIContainerSingleton instance = CDIContainerSingleton.getInstance();
        final TestClassDemoA testClassDemoA = instance.getManagedInstance(TestClassDemoA.class);
        final SimpleDateFormat sdfYYYY = testClassDemoA.sdfYYYY;
        Assert.assertNotNull(sdfYYYY);

        final Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(2010, Calendar.JANUARY, 1, 00, 00, 00);
        final Date date = calendar.getTime();
        System.out.println("date = " + date);
        final String format = sdfYYYY.format(date);
        System.out.println("format = " + format);
        Assert.assertTrue(format.equals("2010"));
    }

    @Test
    public void testProduceSimpleDateFormatter02() throws Exception {
        final CDIContainerSingleton instance = CDIContainerSingleton.getInstance();
        final TestClassDemoA testClassDemoA = instance.getManagedInstance(TestClassDemoA.class);
        final SimpleDateFormat sdfYYYY = testClassDemoA.sdfYYYY;

        Assert.assertNotNull(sdfYYYY);
        final Date parse = sdfYYYY.parse("2010");
        System.out.println("parse = " + parse);
        final Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(2010, Calendar.JANUARY, 1, 00, 00, 00);
        final Date date = calendar.getTime();
        System.out.println("date = " + date);
        Assert.assertTrue(date.equals(parse));
    }


}
