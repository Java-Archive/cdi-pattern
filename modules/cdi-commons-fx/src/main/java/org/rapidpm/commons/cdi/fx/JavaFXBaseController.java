package org.rapidpm.commons.cdi.fx;

import javafx.application.Platform;
import org.rapidpm.commons.cdi.CDINotMapped;
import org.rapidpm.commons.cdi.logger.CDILogger;
import org.rapidpm.commons.cdi.logger.Logger;
import org.rapidpm.lang.CachedThreadPoolSingleton;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * Created by Sven Ruppert
 */
@CDINotMapped
public abstract class JavaFXBaseController implements CDIJavaFxBaseController {

  public static final String DONE = "done";
  public CompletableFuture<String> supplyAsync;
  private boolean mockModusActive = false;
   @Inject @CDILogger private Logger logger;
  private Boolean initCompleteCDI = false;
  private Boolean initCompleteFX = false;
  public final Supplier<String> task = () -> {
//        Warten bis alle true
    while (!(initCompleteCDI && initCompleteFX)) {
      try {
        //evtl loggen
        if (logger.isDebugEnabled()) {
          logger.debug("initCompleteCDI = " + initCompleteCDI);
          logger.debug("initCompleteFX = " + initCompleteFX);
          logger.debug("getClass().getName() = " + getClass().getName());
        }
        TimeUnit.MILLISECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
        return e.toString();
      }
    }

    if (logger.isInfoEnabled()) {
      logger.info("initBusinessLogic() => called now");
    }
    final boolean fxApplicationThread = Platform.isFxApplicationThread();
    if (!fxApplicationThread) {
      Platform.runLater(this::initBusinessLogic);
    } else {
      initBusinessLogic();
    }


    if (logger.isInfoEnabled()) {
      logger.info("initBusinessLogic() => done now");
    }
    return DONE;
  };

  public boolean isMockModusActive() {
    return mockModusActive;
  }

  public void setMockModusActive(boolean mockModusActive) {
    this.mockModusActive = mockModusActive;
  }

  public abstract void cleanUp();

  public abstract void setI18n();

  @Override
  public final void initInstance() {
    final CachedThreadPoolSingleton instance = CachedThreadPoolSingleton.getInstance();
    supplyAsync = CompletableFuture.supplyAsync(task, instance.cachedThreadPool);
    if (logger.isDebugEnabled()) supplyAsync.thenAccept(logger::debug);  //logger
  }

  @PostConstruct
  public final void postconstruct() {
    if (logger.isDebugEnabled()) {
      logger.debug("PostConstruct mockModusActive == " + mockModusActive);
    }
    cdiPostConstruct();
    initCompleteCDI = true;
    if (logger.isDebugEnabled()) {
      logger.debug("postconstruct ready " + getClass().getName());
    }
  }

  public abstract void cdiPostConstruct();

  @Override
  public final void initialize(URL url, ResourceBundle resourceBundle) {
    if (logger.isDebugEnabled()) {
      logger.debug("initialize mockModusActive== " + mockModusActive);
    }
    initializeFX(url, resourceBundle);
    initCompleteFX = true;
    if (logger.isDebugEnabled()) {
      logger.debug("initialize ready " + getClass().getName());
    }
  }


  protected abstract void initializeFX(URL url, ResourceBundle resourceBundle);

  /**
   * wird nach der init von CDI und JavaFX aufgerufen,
   * egal in welcher Reihenfolge die init durchlaufen wird.
   *
   * ein blockierender method call
   */
  public abstract void initBusinessLogic();
}