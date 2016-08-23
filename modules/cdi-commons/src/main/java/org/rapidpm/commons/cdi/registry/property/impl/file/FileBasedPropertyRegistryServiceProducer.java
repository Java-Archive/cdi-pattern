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

package org.rapidpm.commons.cdi.registry.property.impl.file;

import org.rapidpm.commons.cdi.logger.CDILogger;
import org.rapidpm.commons.cdi.logger.Logger;
import org.rapidpm.commons.cdi.registry.property.PropertyRegistryService;

import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 * User: Sven Ruppert
 * Date: 10.06.13
 * Time: 09:09
 */
public class FileBasedPropertyRegistryServiceProducer {

  @Inject @CDILogger Logger logger;

  @Produces
  @CDIPropertyRegistryFileBased
  private PropertyRegistryService createFileBased(@New FileBasedPropertyRegistryService service) {
    return service;
  }
}
