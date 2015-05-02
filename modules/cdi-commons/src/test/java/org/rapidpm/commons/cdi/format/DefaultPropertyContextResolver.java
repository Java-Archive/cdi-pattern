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

package org.rapidpm.commons.cdi.format;

import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;

import org.rapidpm.commons.cdi.contextresolver.ContextResolver;
import org.rapidpm.commons.cdi.logger.CDILogger;
import org.rapidpm.commons.cdi.registry.property.PropertyRegistryService;
import org.rapidpm.commons.cdi.registry.property.impl.file.CDIPropertyRegistryFileBased;
import org.rapidpm.commons.cdi.logger.Logger;

/**
 * User: Sven Ruppert
 * Date: 26.06.13
 * Time: 16:21
 */


public class DefaultPropertyContextResolver implements ContextResolver {

    private @Inject @CDILogger Logger logger;

    public AnnotationLiteral resolveContext(final Class<?> targetClass) {
        if (targetClass.getName().equals(PropertyRegistryService.class.getName())) {

            return new AnnotationLiteral<CDIPropertyRegistryFileBased>() {};
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("class not mapped " + targetClass);
            }
            //return new AnnotationLiteral<CDINotMapped>() {};
            return null;
        }
    }
}
