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

import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;

import org.rapidpm.commons.cdi.registry.property.impl.file.CDIPropertyRegistryFileBased;

/**
 * User: Sven Ruppert
 * Date: 20.06.13
 * Time: 09:06
 */
public class FileBasedRegistriesProducer {


    @Produces
    @CDIPropertyRegistryFileBased
    public CompanyFilePropertyRegistry createCompayPropertyRegistry(@New CompanyFilePropertyRegistry service) {
        service.loadProperties();
        return service;
    }

    @Produces
    @CDIPropertyRegistryFileBased
    public ApplicationFilePropertyRegistry createApplicationPropertyRegistry(@New ApplicationFilePropertyRegistry service) {
        service.loadProperties();
        return service;
    }

    @Produces
    @CDIPropertyRegistryFileBased
    public ModulFilePropertyRegistry createModulPropertyRegistry(@New ModulFilePropertyRegistry service) {
        service.loadProperties();
        return service;
    }

    @Produces
    @CDIPropertyRegistryFileBased
    public ClassFilePropertyRegistry createClassPropertyRegistry(@New ClassFilePropertyRegistry service) {
        service.loadProperties();
        return service;
    }
}
