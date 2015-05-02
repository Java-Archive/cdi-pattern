package org.rapidpm.commons.cdi;


import org.rapidpm.commons.cdi.contextresolver.CDIContext;

import javax.inject.Singleton;

/**
 * User: Sven Ruppert
 * Date: 24.10.13
 * Time: 13:53
 */
@Singleton
public class TestContext implements CDIContext {

    private boolean testModus = false;

    @Override public boolean isMockedModusActive() {
        return testModus;
    }

    public boolean isTestModus() {
        return testModus;
    }

    public void setTestModus(boolean testModus) {
        this.testModus = testModus;
    }
}
