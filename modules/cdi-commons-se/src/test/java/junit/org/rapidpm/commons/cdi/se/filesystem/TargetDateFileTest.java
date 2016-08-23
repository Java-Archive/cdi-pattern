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

package junit.org.rapidpm.commons.cdi.se.filesystem;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rapidpm.commons.cdi.se.CDIContainerSingleton;
import org.rapidpm.commons.cdi.se.filesystem.TargetDateFile;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

/**
 * TargetDateFile Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Jun 11, 2013</pre>
 */
public class TargetDateFileTest {

  private File baseDir;

  @Before
  public void before() throws Exception {
    baseDir = new File("./data_3456/");
  }

  @After
  public void after() throws Exception {
    if (baseDir.exists()) {
      baseDir.delete();
    } else {
      //logger
    }
  }

  /**
   * Method: createDailyDir(File baseDir, Date date)
   */
  @Test
  public void testCreateDailyDir() throws Exception {

    final TargetDateFile targetDateFile = CDIContainerSingleton.getInstance().getManagedInstance(TargetDateFile.class);

    final Calendar instance = Calendar.getInstance();
    instance.set(2010, Calendar.OCTOBER, 10);
    final Date time = instance.getTime();

    final File dailyDir = targetDateFile.createDailyDir(baseDir, time);
    final boolean mkdirs = dailyDir.mkdirs();
    Assert.assertTrue(mkdirs);

    final String dailyDirPath = dailyDir.toPath().toString();
    System.out.println("dailyDirPath = " + dailyDirPath);
    final String fileSeparator = System.getProperty("file.separator");
    System.out.println("fileSeparator = " + fileSeparator);
    final String[] split;
    if (fileSeparator.equals("/")) {
      split = dailyDirPath.split(fileSeparator);
    } else {
      split = dailyDirPath.replace('\\', '/').split("/");
    }

    Assert.assertEquals(".", split[0]);
    Assert.assertEquals(baseDir.getName(), split[1]);
    Assert.assertEquals("2010", split[2]);
    Assert.assertEquals("10", split[3]);
    Assert.assertEquals("10", split[4]);

    deleteNextLevel(baseDir);


  }

  private void deleteNextLevel(File f) {
    if (f.isDirectory()) {
      final File[] files = f.listFiles();
      for (final File file : files) {
        if (file.isDirectory()) {
          deleteNextLevel(file);
        } else {
          Assert.assertTrue(file.delete());
        }
      }
      Assert.assertTrue(f.delete());
    } else {
      Assert.assertTrue(f.delete());
    }
  }


} 
