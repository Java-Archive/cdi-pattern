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

import org.rapidpm.commons.cdi.logger.CDILogger;
import org.rapidpm.commons.cdi.logger.Logger;

import javax.inject.Inject;

/**
 * User: Sven Ruppert
 * Date: 28.10.13
 * Time: 16:06
 */
public class String2Number {

   @Inject @CDILogger private Logger logger;

  public boolean isDouble(final String text) {
    if (text == null) {
      return false;
    } else {
      try {
        final Double aDouble = toDouble(text);
        return true;
      } catch (NumberFormatException e) {
        if (logger.isDebugEnabled()) {
          logger.debug("not a Double - " + e);
        }
      }
      return false;
    }
  }

  public Double toDouble(String text) {
    if (text.contains(",") && text.contains(".")) {
      if (text.indexOf(",") < text.indexOf(".")) {
        //1,020.78
        return Double.valueOf(text.replace(",", ""));
      } else {
        //1.234,56
        return Double.valueOf(text.replace(".", "").replace(",", "."));
      }
    } else if (text.contains(",")) {
      return Double.valueOf(text.replace(",", "."));
    } else {
      return Double.valueOf(text);
    }
  }

  public boolean isLong(final String text) {
    if (text == null) {
      return false;
    } else {
      try {
        final Long along = toLong(text);
        return true;
      } catch (NumberFormatException e) {
        if (logger.isDebugEnabled()) {
          logger.debug("not a Long - " + e);
        }
      }
      return false;
    }
  }

  public Long toLong(String text) {
    if (text.contains(",") && text.contains(".")) {
      if (text.indexOf(",") < text.indexOf(".")) {
        return Double.valueOf(text.replace(".", "")).longValue();
      } else {
        return Double.valueOf(text.replace(".", "").replace(",", ".")).longValue();
      }
    } else if (text.contains(",")) {
      return Double.valueOf(text.replace(",", ".")).longValue();
    } else {
      return Double.valueOf(text).longValue();
    }
  }

  public boolean isLongRndDEFAULT(final String text) {
    if (text == null) {
      return false;
    } else {
      try {
        final Long along = toLongRndDEFAULT(text);
        return true;
      } catch (NumberFormatException e) {
        if (logger.isDebugEnabled()) {
          logger.debug("not a isLongRndDEFAULT - " + e);
        }
      }
      return false;
    }
  }

  public Long toLongRndDEFAULT(String text) {
    final Double aDouble = toDouble(text);
    final long round = Math.round(aDouble);
    return round;
  }

  public boolean isLongRndUP(final String text) {
    if (text == null) {
      return false;
    } else {
      try {
        final Long along = toLongRndUP(text);
        return true;
      } catch (NumberFormatException e) {
        if (logger.isDebugEnabled()) {
          logger.debug("not a isLongRndUP - " + e);
        }
      }
      return false;
    }
  }

  public Long toLongRndUP(String text) {
    final Double aDouble = toDouble(text);
    final long round = Math.round(Math.nextUp(aDouble + 0.5));
    return round;
  }

  public boolean isLongRndDOWN(final String text) {
    if (text == null) {
      return false;
    } else {
      try {
        final Long along = toLongRndDOWN(text);
        return true;
      } catch (NumberFormatException e) {
        if (logger.isDebugEnabled()) {
          logger.debug("not a isLongRndDOWN - " + e);
        }
      }
      return false;
    }
  }

  public Long toLongRndDOWN(String text) {
    final Double aDouble = toDouble(text);
    if (logger.isDebugEnabled()) {
      logger.debug("toLongRndDOWN -> toDouble double " + aDouble);
    }
    final double floor = Math.floor(aDouble);
    if (logger.isDebugEnabled()) {
      logger.debug("toLongRndDOWN -> Math.floor(aDouble) " + floor);
    }
    final double ceil = Math.ceil(aDouble);
    if (logger.isDebugEnabled()) {
      logger.debug("toLongRndDOWN -> Math.ceil(aDouble) " + ceil);
    }
    final double diff = floor - ceil;
    if (logger.isDebugEnabled()) {
      logger.debug("toLongRndDOWN -> diff = floor - ceil " + diff);
    }

    if (diff <= 0 && aDouble >= 0.0) {
      return Double.valueOf(floor).longValue();
    } else {
      return Double.valueOf(ceil).longValue();
    }
  }
}
