package org.rapidpm.commons.cdi.contextresolver;


import org.rapidpm.commons.cdi.CDINotMapped;

/**
 * User: Sven Ruppert
 * Date: 24.10.13
 * Time: 13:47
 */
@CDINotMapped
public interface CDIContext {

    public boolean isMockedModusActive();

}
