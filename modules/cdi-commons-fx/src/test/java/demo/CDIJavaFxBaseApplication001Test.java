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

package demo;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.Assert;
import org.rapidpm.commons.cdi.format.CDISimpleDateFormatter;
import org.rapidpm.commons.cdi.fx.JavaFXBaseTest;
import org.rapidpm.commons.cdi.logger.CDILogger;
import org.rapidpm.commons.cdi.logger.Logger;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: Sven Ruppert
 * Date: 09.07.13
 * Time: 11:59
 */

public class CDIJavaFxBaseApplication001Test extends JavaFXBaseTest {

//    @Override
//    protected Class<? extends JavaFXBaseTest> getTestClass() {
//        return CDIJavaFxBaseApplication001Test.class;
//    }

  public static class TestImpl extends JavaFXBaseTest.JavaFXBaseTestImpl {

    @Inject
    @CDISimpleDateFormatter(value = "date.yyyyMMdd")
    SimpleDateFormat sdf;

//        @Override
//        protected Class<? extends JavaFXBaseTest> getParentTestClass() {
//            return CDIJavaFxBaseApplication001Test.class;
//        }
    @Inject
    @CDILogger
    Logger logger;
    @Inject
    LoginPane root;

    @Override
    public boolean isExitAfterTest() {
      return false;
    }

    @Override
    public void testImpl(Stage stage) {
      if (logger.isDebugEnabled()) {
        logger.debug("testrunn at -> " + sdf.format(new Date()));
      }
      stage.setTitle("Login");  //i18n
      stage.setScene(new Scene(root, 300, 275));
      stage.show();
      final Scene scene = stage.getScene();

      //TestCode
      final TextField login = (TextField) scene.lookup("#loginField");
      login.setText("LOGIN");
      final PasswordField passwd = (PasswordField) scene.lookup("#passwordField");
      passwd.setText("LOGIN");

      final LoginPaneController controller = root.getController();
      controller.handleSubmitButtonAction(new ActionEvent());

      final Text feedback = (Text) scene.lookup("#feedback");
      Assert.assertEquals("LOGIN logged in successfully", feedback.getText());
    }
  }
}
