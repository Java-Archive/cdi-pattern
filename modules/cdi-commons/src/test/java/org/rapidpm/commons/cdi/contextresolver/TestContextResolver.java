package org.rapidpm.commons.cdi.contextresolver;

import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;

import org.rapidpm.commons.cdi.CDICommons;


/**
 * User: Sven Ruppert
 * Date: 24.10.13
 * Time: 13:56
 */

@CDIContextResolverTest
public class TestContextResolver implements ContextResolver {

    @Inject CDIContext context;

    @CheckMockedContext
    @Override public AnnotationLiteral resolveContext(Class<?> targetClass) {
      if(targetClass.equals(ContextResolverMockedTest.class)){
        return new AnnotationLiteral<CDIContextResolverTest>() {};
      } else {
        return null;
      }
    }
}
