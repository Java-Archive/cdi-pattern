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

package org.rapidpm.commons.cdi.registry.property;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;

import org.rapidpm.commons.cdi.contextresolver.ContextResolver;
import org.rapidpm.commons.cdi.registry.property.impl.file.CDIPropertyRegistryFileBased;
import org.rapidpm.commons.cdi.CDICommons;
import org.rapidpm.commons.cdi.ManagedInstanceCreator;
import org.rapidpm.commons.cdi.logger.CDILogger;
import org.rapidpm.commons.cdi.logger.Logger;

/**
 * User: Sven Ruppert
 * Date: 17.07.13
 * Time: 09:12
 */
public class PropertyRegistryServiceProducer {

    private @Inject @CDILogger Logger logger;
    private @Inject @CDIPropertyRegistryFileBased PropertyRegistryService defaultRegistry;
    private @Inject BeanManager beanManager;
    private @Inject ManagedInstanceCreator creator;

    @Produces @CDIPropertyRegistryService
    public PropertyRegistryService create(@CDICommons ContextResolver contextResolver) {
        if (logger.isDebugEnabled()) {
            logger.debug("used ContextResolver - " + contextResolver.getClass().getName());
        }
        final Class<PropertyRegistryService> beanType = PropertyRegistryService.class;
        final AnnotationLiteral annotationLiteral = contextResolver.resolveContext(beanType);
        final PropertyRegistryService propertyRegistryService = creator.getManagedInstance(beanType, annotationLiteral);
        //return Default
        if (logger.isInfoEnabled()) {
            logger.info("PropertyRegistryService - Using default filebased implementation ");
        }

        if(propertyRegistryService == null){
            return defaultRegistry;
        } else{
            return propertyRegistryService;
        }
    }








}
