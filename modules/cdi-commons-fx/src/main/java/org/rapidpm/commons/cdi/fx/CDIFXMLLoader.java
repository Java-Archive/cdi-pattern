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

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.util.BuilderFactory;
import javafx.util.Callback;
import org.rapidpm.commons.cdi.se.CDIContainerSingleton;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.ResourceBundle;

/**
 * User: Sven Ruppert
 * Date: 20.09.13
 * Time: 12:42
 */
public class CDIFXMLLoader extends FXMLLoader {


  private final CDIContainerSingleton cdi = CDIContainerSingleton.getInstance();

  public CDIFXMLLoader() {
    super();
//        activateCDI(this);
  }

  public CDIFXMLLoader(URL url) {
    super(url);
//        activateCDI(this);
  }

  public CDIFXMLLoader(URL url, ResourceBundle resourceBundle) {
    super(url, resourceBundle);
//        activateCDI(this);
  }

  public CDIFXMLLoader(URL url, ResourceBundle resourceBundle, BuilderFactory builderFactory) {
    super(url, resourceBundle, builderFactory);
//        activateCDI(this);
  }

  public CDIFXMLLoader(URL url, ResourceBundle resourceBundle, BuilderFactory builderFactory, Callback<Class<?>, Object> classObjectCallback) {
    super(url, resourceBundle, builderFactory, classObjectCallback);
//        activateCDI(this);
  }

  public CDIFXMLLoader(Charset charset) {
    super(charset);
//        activateCDI(this);
  }

  public CDIFXMLLoader(URL url, ResourceBundle resourceBundle, BuilderFactory builderFactory, Callback<Class<?>, Object> classObjectCallback, Charset charset) {
    super(url, resourceBundle, builderFactory, classObjectCallback, charset);
//        activateCDI(this);
  }

  public CDIFXMLLoader(URL url, ResourceBundle resourceBundle, BuilderFactory builderFactory, Callback<Class<?>, Object> classObjectCallback, Charset charset, LinkedList<FXMLLoader> fxmlLoaders) {
    super(url, resourceBundle, builderFactory, classObjectCallback, charset, fxmlLoaders);
//        activateCDI(this);
  }

  @Override
  public ObservableMap<String, Object> getNamespace() {
    return super.getNamespace();
  }

  @Override
  public <T> T getRoot() {
    final T root = super.getRoot();
//        return root;
    return activateCDI(root);
  }

  public <T> T activateCDI(T t) {
//        final BeanManager beanManager = cdi.getBeanManager();
//        final Class aClass = t.getClass();
//        final AnnotatedType annotationType = beanManager.createAnnotatedType(aClass);
//        final InjectionTarget injectionTarget = beanManager.createInjectionTarget(annotationType);
//        final CreationalContext creationalContext = beanManager.createCreationalContext(null);
//        injectionTarget.inject(t, creationalContext);
//        injectionTarget.postConstruct(t);
    return cdi.activateCDI(t);
  }

  @Override
  public void setRoot(Object o) {
    super.setRoot(o);
  }

  @Override
  public <T> T getController() {
    final T controller = super.getController();
//         return controller;
    return activateCDI(controller);
  }


  @Override
  public void setController(Object o) {
    super.setController(o);
  }

//    @Override public ResourceBundle getResources() {
//        return super.getResources();
//    }
//
//    @Override public void setResources(ResourceBundle resourceBundle) {
//        super.setResources(resourceBundle);
//    }

  @Override
  public BuilderFactory getBuilderFactory() {
    final BuilderFactory builderFactory = super.getBuilderFactory();
    return activateCDI(builderFactory);
  }

  @Override
  public void setBuilderFactory(BuilderFactory builderFactory) {
    super.setBuilderFactory(builderFactory);
  }

  @Override
  public Callback<Class<?>, Object> getControllerFactory() {
    final Callback<Class<?>, Object> controllerFactory = super.getControllerFactory();
    return activateCDI(controllerFactory);
  }

  @Override
  public void setControllerFactory(Callback<Class<?>, Object> classObjectCallback) {
    super.setControllerFactory(classObjectCallback);
  }


  @Override
  public ClassLoader getClassLoader() {
    return super.getClassLoader();
  }

  @Override
  public void setClassLoader(ClassLoader classLoader) {
    super.setClassLoader(classLoader);
  }


  @Override
  public Object load() throws IOException {
    final Object load = super.load();
//        return activateCDI(load);
    return load;
  }

  @Override
  public Object load(InputStream inputStream) throws IOException {
    final Object load = super.load(inputStream);
    return activateCDI(load);
//        return load;
  }

}
