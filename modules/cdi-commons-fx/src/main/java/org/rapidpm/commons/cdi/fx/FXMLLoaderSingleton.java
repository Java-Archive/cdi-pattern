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

package org.rapidpm.commons.cdi.fx;

import javafx.fxml.FXMLLoader;
import org.rapidpm.commons.cdi.logger.CDILogger;
import org.rapidpm.commons.cdi.logger.Logger;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Sven Ruppert
 * Date: 08.07.13
 * Time: 16:38
 */
@Singleton
public class FXMLLoaderSingleton {

  private final ClassLoader cachingClassLoader = new FXClassLoader(FXMLLoader.getDefaultClassLoader());
  private final Map<Class, FXMLLoader> class2LoaderMap = new HashMap<Class, FXMLLoader>();
   @Inject @CDILogger private Logger logger;
   @Inject private Instance<CDIJavaFxBaseController> instance;

  private FXMLLoaderSingleton() {
  }

  public FXMLLoader getFXMLLoader(Class clazz) {
    final Map<Class, FXMLLoader> loaderMap = class2LoaderMap;
    final String name = clazz.getName();
    if (loaderMap.containsKey(clazz)) {
      if (logger.isDebugEnabled()) {
        logger.debug("fx loader fuer diese klasse schon in der map " + name);
      }
    } else {
      final String fxmlFileName = clazz.getSimpleName() + ".fxml";
      if (logger.isDebugEnabled()) {
        logger.debug("fxmlFileName -> " + fxmlFileName);
      }
      final URL resource = clazz.getResource(fxmlFileName);
//            FXMLLoader loader = new CDIFXMLLoader(resource);
      FXMLLoader loader = new FXMLLoader(resource);
      loader.setClassLoader(cachingClassLoader);
      loader.setControllerFactory(param -> {
        final Class<JavaFXBaseController> p = (Class<JavaFXBaseController>) param;
        final JavaFXBaseController controller = instance.select(p).get();
        controller.initInstance(); //trigger async call
        return controller;
      });
      try {  //verpacken in Dynamic Proxy
        final Class<?> aClass = Class.forName(clazz.getName() + "Controller");
        final CDIJavaFxBaseController call = (CDIJavaFxBaseController) loader.getControllerFactory().call(aClass);
        loader.setController(call);
      } catch (ClassNotFoundException e) {
        logger.error(e);
      }
      loaderMap.put(clazz, loader);
    }
    return loaderMap.get(clazz);
  }
}
