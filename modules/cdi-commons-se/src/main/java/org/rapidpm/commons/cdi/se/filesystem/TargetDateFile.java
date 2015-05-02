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

package org.rapidpm.commons.cdi.se.filesystem;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import org.rapidpm.commons.cdi.format.CDISimpleDateFormatter;
import org.rapidpm.commons.cdi.logger.CDILogger;
import org.rapidpm.commons.cdi.se.CDICommonsSE;
import org.rapidpm.commons.cdi.logger.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Sven Ruppert
 * Date: 09.06.13
 * Time: 17:33
 * <p></p>
 * TargetFile based on the given date. The File will be in an dir
 * with the following structure.
 * <p></p>
 * yyyy
 * MM
 * dd
 * For example 30.12.2010 would be transformed into an dir
 * 2010
 * 12
 * 30 this file would be given back.
 */
@CDICommonsSE
public class TargetDateFile implements Serializable{

    private @Inject @CDILogger Logger logger;

    @Inject
    @CDISimpleDateFormatter("date.yyyy")
    private SimpleDateFormat sdfYYYY;
    @Inject
    @CDISimpleDateFormatter("date.MM")
    private SimpleDateFormat sdfMM;
    @Inject
    @CDISimpleDateFormatter("date.dd")
    private SimpleDateFormat sdfdd;


    public File createDailyDir(File baseDir, Date date) {
        final File yyyy = new File(baseDir, sdfYYYY.format(date));
        final File mm = new File(yyyy, sdfMM.format(date));
        final File dd = new File(mm, sdfdd.format(date));
        if (!yyyy.exists()) {
            //
            yyyy.mkdir();
        }
        if (! mm.exists()) {
            mm.mkdir();
        }
        if (! dd.exists()) {
            dd.mkdir();
        }
        return dd;
    }

}
