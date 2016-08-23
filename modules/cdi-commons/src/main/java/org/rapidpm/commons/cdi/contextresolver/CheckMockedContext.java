package org.rapidpm.commons.cdi.contextresolver;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.*;

/**
 * User: Sven Ruppert
 * Date: 24.10.13
 * Time: 13:42
 */


@InterceptorBinding
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@Inherited
public @interface CheckMockedContext {
}
