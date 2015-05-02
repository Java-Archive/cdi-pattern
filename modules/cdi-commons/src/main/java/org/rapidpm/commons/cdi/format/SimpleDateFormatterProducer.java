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

import java.lang.annotation.Annotation;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import org.rapidpm.commons.cdi.locale.CDILocale;
import org.rapidpm.commons.cdi.registry.property.CDIPropertyRegistryService;
import org.rapidpm.commons.cdi.registry.property.PropertyRegistryService;

/**
 * User: Sven Ruppert
 * Date: 03.06.13
 * Time: 08:28
 */

public class SimpleDateFormatterProducer {

    public SimpleDateFormat createDefault(InjectionPoint injectionPoint) {
        //default res key -> company wide yyyyMMdd
        //return new SimpleDateFormat("dd.MM.yyyy");
        final String ressource = propertyRegistryService.getRessourceForKey("date.yyyyMMdd");
        return new SimpleDateFormat(ressource, defaultLocale);
    }

    private
    @Inject
    @CDIPropertyRegistryService
    PropertyRegistryService propertyRegistryService;

    private
    @Inject
    @CDILocale
    Locale defaultLocale;


    /**
     * es fehlt noch die auflösung des Locale
     *
     * @param ip
     * @return
     */
    @Produces
    @CDISimpleDateFormatter
    public SimpleDateFormat produceSimpleDateFormatter(InjectionPoint ip) {

        final Set<Annotation> qualifiers = ip.getQualifiers();

       //Version mit Transferobject
        final SimpleDateFormat sdf = qualifiers.stream()
                .filter((e) -> e instanceof CDISimpleDateFormatter)
                .map((qualifier) -> {
                    HolderClass holder = new HolderClass();
                    holder.ressourcenKey =  ((CDISimpleDateFormatter) qualifier).value();
                    holder.mappedKey = propertyRegistryService.getRessourceForKey(holder.ressourcenKey);
                    return holder;
                })
                .findFirst()
                .map((h) -> {
                    if( h.mappedKey.equals("###" + h.ressourcenKey + "###" ) ){
                        return createDefault(ip); //Fallback
                    } else{
                        return new SimpleDateFormat(h.mappedKey, defaultLocale);
                    }
                })
                .get();

        return sdf;


        //Version ohne Transferobject
//        final SimpleDateFormat sdf = qualifiers.stream()
//                .filter((e) -> e instanceof CDISimpleDateFormatter)
//                .map((qualifier) -> {
//                    String ressourceKey = ((CDISimpleDateFormatter) qualifier).value();
//                    return propertyRegistryService.getRessourceForKey(ressourceKey);
//                })
//                .findFirst()
//                .map((r) -> {
//                    final boolean startsWith = r.startsWith("###");
//                    final boolean endsWith = r.endsWith("###");
//                    if( startsWith && endsWith ){
//                        return createDefault(ip); //Fallback
//                    } else{
//                        return new SimpleDateFormat(r, defaultLocale);
//                    }
//                })
//                .get();




//        for (final Annotation qualifier : qualifiers) {
//            if (qualifier instanceof CDISimpleDateFormatter) {
//                final String ressourceKey = ((CDISimpleDateFormatter) qualifier).value();
//                final String ressource = propertyRegistryService.getRessourceForKey(ressourceKey);
//                if (ressource.equals("###" + ressourceKey + "###")) {
//                    return createDefault(ip);
//                } else {
////                    final Locale locale = propertyRegistryService.resolveLocale();
//                    return new SimpleDateFormat(ressource, defaultLocale);
//                }
//            } else {
//
//            }
//        }
//        for (final Annotation qualifier : qualifiers) {
//            if (qualifier instanceof CDISimpleDateFormatter) {
//                final String ressourceKey = ((CDISimpleDateFormatter) qualifier).value();
//                final String ressource = propertyRegistryService.getRessourceForKey(ressourceKey);
//                if (ressource.equals("###" + ressourceKey + "###")) {
//                    return createDefault(ip);
//                } else {
////                    final Locale locale = propertyRegistryService.resolveLocale();
//                    return new SimpleDateFormat(ressource, defaultLocale);
//                }
//            } else {
//
//            }
//        }

//        return createDefault(ip);
//        final Annotated annotated = injectionPoint.getAnnotated();
//        if (annotated.isAnnotationPresent(CDISimpleDateFormatter.class)) {
//            final CDISimpleDateFormatter annotation = annotated.getAnnotation(CDISimpleDateFormatter.class);
//            final String ressourceKey = annotation.value();
//            final String ressource = propertyRegistryService.getRessourceForKey(ressourceKey);
//            if (ressource.equals("###" + ressourceKey + "###")) {
//                return createDefault(injectionPoint);
//            } else {
//                return new SimpleDateFormat(ressource, defaultLocale);
//            }
//        } else {
//            return createDefault(injectionPoint);
//        }
    }

    private static class HolderClass {
        public String ressourcenKey;
        public String mappedKey;
    }


}
