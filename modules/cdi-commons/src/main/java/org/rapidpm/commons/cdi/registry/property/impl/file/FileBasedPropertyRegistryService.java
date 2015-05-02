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

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.rapidpm.commons.cdi.logger.CDILogger;
import org.rapidpm.commons.cdi.registry.property.PropertyRegistry;
import org.rapidpm.commons.cdi.registry.property.PropertyRegistryService;
import org.rapidpm.commons.cdi.registry.property.impl.file.registries.ClassFilePropertyRegistry;
import org.rapidpm.commons.cdi.registry.property.impl.file.registries.ModulFilePropertyRegistry;
import org.rapidpm.commons.cdi.registry.property.impl.CompanyPropertyRegistry;
import org.rapidpm.commons.cdi.registry.property.impl.file.registries.ApplicationFilePropertyRegistry;
import org.rapidpm.commons.cdi.logger.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Sven Ruppert
 * Date: 20.06.13
 * Time: 09:50
 */
public class FileBasedPropertyRegistryService extends PropertyRegistryService implements Serializable {

    private @Inject @CDILogger Logger logger;

    private @Inject @CDIPropertyRegistryFileBased
    CompanyPropertyRegistry companyPropertyRegistry;

    private @Inject @CDIPropertyRegistryFileBased
    ApplicationFilePropertyRegistry applicationFilePropertyRegistry;

    private @Inject @CDIPropertyRegistryFileBased
    ModulFilePropertyRegistry modulFilePropertyRegistry;

    private @Inject @CDIPropertyRegistryFileBased
    ClassFilePropertyRegistry classFilePropertyRegistry;


    private List<PropertyRegistry> registries = new ArrayList<>();

    @PostConstruct
    public void init(){
        registries.clear();
        registries.add(classFilePropertyRegistry);
        registries.add(modulFilePropertyRegistry);
        registries.add(applicationFilePropertyRegistry);
        registries.add(companyPropertyRegistry);
    }

    @Override
    public String getRessourceForKey(String ressourceKey) {

        return registries.stream()
                .filter(r->r.hasProperty(ressourceKey))
                .map(r -> {
                    if (logger.isDebugEnabled()) {
                        logger.debug(r.getClass().getSimpleName() + " found Property " + ressourceKey);
                    }
                    return r.getProperty(ressourceKey);
                })
                .findFirst()
                .orElse("###" + ressourceKey + "###");
    }
}
