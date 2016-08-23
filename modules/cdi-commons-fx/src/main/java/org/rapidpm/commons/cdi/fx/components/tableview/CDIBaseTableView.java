/*
 * Copyright [2014] [www.rapidpm.org / Sven Ruppert (sven.ruppert@rapidpm.org)]
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

package org.rapidpm.commons.cdi.fx.components.tableview;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * Created by Sven Ruppert on 05.08.2014.
 */
public class CDIBaseTableView extends TableView {

  public CDIBaseTableView() {
    AnchorPane.setBottomAnchor(this, 0.0);
    AnchorPane.setTopAnchor(this, 0.0);
    AnchorPane.setLeftAnchor(this, 0.0);
    AnchorPane.setRightAnchor(this, 0.0);
  }

  public CDIBaseTableView(ObservableList items) {
    super(items);
    AnchorPane.setBottomAnchor(this, 0.0);
    AnchorPane.setTopAnchor(this, 0.0);
    AnchorPane.setLeftAnchor(this, 0.0);
    AnchorPane.setRightAnchor(this, 0.0);
  }


}
