package org.rapidpm.commons.cdi.contextresolver;

import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;

import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rapidpm.commons.cdi.CDICommons;
import org.rapidpm.commons.cdi.CDICommonsMocked;
import org.rapidpm.commons.cdi.TestContext;

/**
 * User: Sven Ruppert
 * Date: 24.10.13
 * Time: 13:52
 */
@RunWith(Arquillian.class)
public class ContextResolverMockedTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, "org.rapidpm")
                .addAsManifestResource("META-INF/beans.xml");
    }


    @Inject CDIContext context;
    @Inject @CDICommons ContextResolver contextResolver;

    @Test
    public void testMockedModus001() throws Exception {
        Assert.assertNotNull(context);
        Assert.assertFalse(context.isMockedModusActive());

        ((TestContext)context).setTestModus(true);
        Assert.assertTrue(context.isMockedModusActive());

        Assert.assertNotNull(contextResolver);

        final AnnotationLiteral annotationLiteral = contextResolver.resolveContext(this.getClass());
        Assert.assertNotNull(annotationLiteral);

        Assert.assertEquals(new AnnotationLiteral<CDICommonsMocked>() {},annotationLiteral);

    }
}
