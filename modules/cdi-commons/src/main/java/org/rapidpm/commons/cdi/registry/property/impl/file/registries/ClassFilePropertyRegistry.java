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

package org.rapidpm.commons.cdi.registry.property.impl.file.registries;

import org.rapidpm.commons.cdi.locale.CDILocale;
import org.rapidpm.commons.cdi.logger.CDILogger;
import org.rapidpm.commons.cdi.logger.Logger;
import org.rapidpm.commons.cdi.registry.property.impl.ClassPropertyRegistry;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * User: Sven Ruppert
 * Date: 20.06.13
 * Time: 07:36
 */
public class ClassFilePropertyRegistry implements ClassPropertyRegistry, Serializable {

  private ResourceBundle messages;

   @Inject @CDILocale private Locale defaultLocale;
   @Inject @CDILogger private Logger logger;

  @Override
  public void loadProperties() {
    try {
      messages = ResourceBundle.getBundle("i18n/classes", defaultLocale);
    } catch (MissingResourceException e) {
      logger.warn("ressource not found loading dummy");
      messages = ResourceBundle.getBundle("i18n/classes_dummy", defaultLocale);
    }
  }

  @Override
  public String getProperty(String key) {
    final boolean contains = messages.containsKey(key);
    if (contains) {
      return messages.getString(key);
    } else {
      return "###" + key + "###";
    }
  }

  @Override
  public boolean hasProperty(String key) {
    return messages.containsKey(key);
  }
}
