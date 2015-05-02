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

package org.rapidpm.commons.cdi.lang;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * User: Sven Ruppert
 * Date: 28.10.13
 * Time: 16:22
 */

@RunWith(Arquillian.class)
public class String2NumberTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, "org.rapidpm.commons")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject String2Number string2Number = new String2Number();

    @Test
    public void testNullIsDouble() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isDouble(null);
        Assert.assertFalse(aDouble);
    }

    @Test
    public void testNullIsLong() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isLong(null);
        Assert.assertFalse(aDouble);
    }

    @Test
    public void testEmptyIsDouble() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isDouble("");
        Assert.assertFalse(aDouble);
    }

    @Test
    public void testEmptyIsLong() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isLong("");
        Assert.assertFalse(aDouble);
    }

    @Test
    public void testTextIsDouble() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isDouble("text");
        Assert.assertFalse(aDouble);
    }

    @Test
    public void testTextIsLong() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isLong("text");
        Assert.assertFalse(aDouble);
    }

    @Test
    public void testOneDoubleA() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isDouble("1");
        Assert.assertTrue(aDouble);
        Assert.assertEquals(Double.valueOf(1), string2Number.toDouble("1"));
    }

    @Test
    public void testOneLongA() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isLong("1");
        Assert.assertTrue(aDouble);
        Assert.assertEquals(Long.valueOf(1), string2Number.toLong("1"));
    }

    @Test
    public void testOneDoubleB() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isDouble("1.0");
        Assert.assertTrue(aDouble);
        Assert.assertEquals(Double.valueOf(1), string2Number.toDouble("1.0"));
    }

    @Test
    public void testOneLongB() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isLong("1.0");
        Assert.assertTrue(aDouble);
        Assert.assertEquals(Long.valueOf(1), string2Number.toLong("1.0"));
    }

    @Test
    public void testOneDoubleC() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isDouble("1,0");
        Assert.assertTrue(aDouble);
        Assert.assertEquals(Double.valueOf(1), string2Number.toDouble("1,0"));
    }

    @Test
    public void testOneLongC() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isLong("1,0");
        Assert.assertTrue(aDouble);
        Assert.assertEquals(Long.valueOf(1), string2Number.toLong("1,0"));
    }

    @Test
    public void testDouble001() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isDouble("1,020.78");
        Assert.assertTrue(aDouble);
        Assert.assertEquals(Double.valueOf(1020.78), string2Number.toDouble("1,020.78"));
    }

    @Test
    public void testLong001() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isLong("1,020.78");
        Assert.assertFalse(aDouble);
    }


    @Test
    public void testLong001RndDEFAULT001() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isLongRndDEFAULT("1,020.78");
        Assert.assertTrue(aDouble);
        Assert.assertEquals(Long.valueOf(1021), string2Number.toLongRndDEFAULT("1,020.78"));
    }

    @Test
    public void testLong001RndDEFAULT002() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isLongRndDEFAULT("1,020.48");
        Assert.assertTrue(aDouble);
        Assert.assertEquals(Long.valueOf(1020), string2Number.toLongRndDEFAULT("1,020.48"));
    }

    @Test
    public void testLong001RndDEFAULT003() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isLongRndDEFAULT("1,020.50");
        Assert.assertTrue(aDouble);
        Assert.assertEquals(Long.valueOf(1021), string2Number.toLongRndDEFAULT("1,020.50"));
    }

    @Test
    public void testLong001RndDEFAULT004() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isLongRndDEFAULT("-1,020.78");
        Assert.assertTrue(aDouble);
        Assert.assertEquals(Long.valueOf(-1021), string2Number.toLongRndDEFAULT("-1,020.78"));
    }

    @Test
    public void testLong001RndDEFAULT005() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isLongRndDEFAULT("-1,020.48");
        Assert.assertTrue(aDouble);
        Assert.assertEquals(Long.valueOf(-1020), string2Number.toLongRndDEFAULT("-1,020.48"));
    }

    @Test
    public void testLong001RndDEFAULT006() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isLongRndDEFAULT("-1,020.50");
        Assert.assertTrue(aDouble);
        Assert.assertEquals(Long.valueOf(-1020), string2Number.toLongRndDEFAULT("-1,020.50"));
    }

    @Test
    public void testLong001RndDOWN001() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isLongRndDOWN("1,020.78");
        Assert.assertTrue(aDouble);
        Assert.assertEquals(Long.valueOf(1020), string2Number.toLongRndDOWN("1,020.78"));
    }

    @Test
    public void testLong001RndDOWN002() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isLongRndDOWN("1,020.48");
        Assert.assertTrue(aDouble);
        Assert.assertEquals(Long.valueOf(1020), string2Number.toLongRndDOWN("1,020.48"));
    }

    @Test
    public void testLong001RndDOWN003() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isLongRndDOWN("1,020.50");
        Assert.assertTrue(aDouble);
        Assert.assertEquals(Long.valueOf(1020), string2Number.toLongRndDOWN("1,020.50"));
    }

    @Test
    public void testLong001RndDOWN004() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isLongRndDOWN("-1,020.78");
        Assert.assertTrue(aDouble);
        Assert.assertEquals(Long.valueOf(-1020), string2Number.toLongRndDOWN("-1,020.78"));
    }

    @Test
    public void testLong001RndDOWN005() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isLongRndDOWN("-1,020.48");
        Assert.assertTrue(aDouble);
        Assert.assertEquals(Long.valueOf(-1020), string2Number.toLongRndDOWN("-1,020.48"));
    }

    @Test
    public void testLong001RndDOWN006() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isLongRndDOWN("-1,020.50");
        Assert.assertTrue(aDouble);
        Assert.assertEquals(Long.valueOf(-1020), string2Number.toLongRndDOWN("-1,020.50"));
    }


    @Test
    public void testLong001RndUP001() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isLongRndUP("1,020.78");
        Assert.assertTrue(aDouble);
        Assert.assertEquals(Long.valueOf(1021), string2Number.toLongRndUP("1,020.78"));
    }

    @Test
    public void testLong001RndUP002() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isLongRndUP("1,020.48");
        Assert.assertTrue(aDouble);
        Assert.assertEquals(Long.valueOf(1021), string2Number.toLongRndUP("1,020.48"));
    }

    @Test
    public void testLong001RndUP003() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isLongRndUP("1,020.50");
        Assert.assertTrue(aDouble);
        Assert.assertEquals(Long.valueOf(1021), string2Number.toLongRndUP("1,020.50"));
    }

    @Test
    public void testLong001RndUP004() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isLongRndUP("-1,020.78");
        Assert.assertTrue(aDouble);
        Assert.assertEquals(Long.valueOf(-1020), string2Number.toLongRndUP("-1,020.78"));
    }

    @Test
    public void testLong001RndUP005() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isLongRndUP("-1,020.48");
        Assert.assertTrue(aDouble);
        Assert.assertEquals(Long.valueOf(-1020), string2Number.toLongRndUP("-1,020.48"));
    }

    @Test
    public void testLong001RndUP006() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isLongRndUP("-1,020.50");
        Assert.assertTrue(aDouble);
        Assert.assertEquals(Long.valueOf(-1020), string2Number.toLongRndUP("-1,020.50"));
    }


    @Test
    public void testDouble002() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isDouble("1.020,78");
        Assert.assertTrue(aDouble);
        Assert.assertEquals(Double.valueOf(1020.78), string2Number.toDouble("1.020,78"));
    }

//    @Test
//    public void testLong002() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
//        final boolean aDouble = string2Number.isLong("1.020,78");
//        Assert.assertTrue(aDouble);
//        Assert.assertEquals(Long.valueOf(1020.78), string2Number.toLong("1.020,78"));
//    }

    @Test
    public void testDouble003() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isDouble("-1,0");
        Assert.assertTrue(aDouble);
        Assert.assertEquals(Double.valueOf(-1), string2Number.toDouble("-1,0"));
    }

    @Test
    public void testLong003() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isLong("-1,0");
        Assert.assertTrue(aDouble);
        Assert.assertEquals(Long.valueOf(-1), string2Number.toLong("-1,0"));
    }

    @Test
    public void testDouble004() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isDouble("-10001,23");
        Assert.assertTrue(aDouble);
        Assert.assertEquals(Double.valueOf(-10001.23), string2Number.toDouble("-10001,23"));
    }

//    @Test
//    public void testLong004() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
//        final boolean aDouble = string2Number.isLong("-10001,23");
//        Assert.assertTrue(aDouble);
//        Assert.assertEquals(Long.valueOf(-10001.23), string2Number.toLong("-10001,23"));
//    }

    @Test
    public void testDouble005() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isDouble("-10.001,23");
        Assert.assertTrue(aDouble);
        Assert.assertEquals(Double.valueOf(-10001.23), string2Number.toDouble("-10.001,23"));
    }

//    @Test
//    public void testLong005() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
//        final boolean aDouble = string2Number.isLong("-10.001,23");
//        Assert.assertTrue(aDouble);
//        Assert.assertEquals(Long.valueOf(-10001.23), string2Number.toLong("-10.001,23"));
//    }

    @Test
    public void testDouble006() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isDouble("12122121212");
        Assert.assertTrue(aDouble);
        Assert.assertEquals(Double.valueOf(12122121212L), string2Number.toDouble("12122121212"));
    }

    @Test
    public void testLong006() throws Exception {
//        final String2Number string2Number = string2NumberInstance.get();
        final boolean aDouble = string2Number.isLong("12122121212");
        Assert.assertTrue(aDouble);
        Assert.assertEquals(Long.valueOf(12122121212L), string2Number.toLong("12122121212"));
    }


}
