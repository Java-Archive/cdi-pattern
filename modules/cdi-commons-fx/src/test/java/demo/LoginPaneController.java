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

import javafx.application.Application.Parameters;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.rapidpm.commons.cdi.fx.CDIJavaFXBaseApp;
import org.rapidpm.commons.cdi.fx.JavaFXBaseController;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

// Simple application controller that uses injected fields 
// to delegate login process and to get default values from the command line using: --user=SomeUser
public class LoginPaneController extends JavaFXBaseController {
  // Standard FXML injected fields
  @FXML TextField loginField;
  @FXML PasswordField passwordField;
  @FXML Text feedback;

  // CDI Injected field
  @Inject LoginService loginService;

  // Default application parameters
  @Inject @CDIJavaFXBaseApp
  Parameters applicationParameters;

  @FXML
  protected void handleSubmitButtonAction(ActionEvent event) {
    feedback.setText(loginService.login(loginField.getText(), passwordField.getText()));
  }

  @Override
  public void cleanUp() {

  }

  @Override
  public void setI18n() {

  }

  @Override
  public void cdiPostConstruct() {

  }

  @Override
  protected void initializeFX(URL url, ResourceBundle resourceBundle) {
    loginField.setText(applicationParameters.getNamed().get("user"));
  }

  @Override
  public void initBusinessLogic() {

  }
}
