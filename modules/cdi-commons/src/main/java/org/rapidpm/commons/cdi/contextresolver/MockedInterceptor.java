package org.rapidpm.commons.cdi.contextresolver;

import org.rapidpm.commons.cdi.CDICommonsMocked;
import org.rapidpm.commons.cdi.logger.CDILogger;
import org.rapidpm.commons.cdi.logger.Logger;

import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

/**
 * User: Sven Ruppert
 * Date: 24.10.13
 * Time: 13:31
 */
@CheckMockedContext
@Interceptor
public class MockedInterceptor implements Serializable {


  @Inject CDIContext context;
  //    private static final Logger logger = Logger.getLogger(MockedInterceptor.class);
  @Inject @CDILogger Logger logger;

  @AroundInvoke
  public Object checkMockedMode(InvocationContext ctx) throws Exception {
    if (context.isMockedModusActive()) {
      if (logger.isDebugEnabled()) {
        logger.debug("MockedInterceptor-> MockedModus active");
      }
      return new AnnotationLiteral<CDICommonsMocked>() {
      };
    } else {
      return ctx.proceed();
    }
  }
}
