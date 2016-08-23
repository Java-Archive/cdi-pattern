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

package org.rapidpm.commons.cdi.fx.components.tableview.cell;


import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableRow;
import org.rapidpm.commons.cdi.ManagedInstanceCreator;
import org.rapidpm.commons.cdi.logger.CDILogger;
import org.rapidpm.commons.cdi.logger.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * User: Sven Ruppert
 * Date: 02.10.13
 * Time: 13:58
 */
public abstract class CDIButtonCell<T> extends TableCell<T, Boolean> {

  public Button cellButton;
   @Inject @CDILogger public Logger logger;
   @Inject public ManagedInstanceCreator instanceCreator;
  private List<Consumer<T>> cellActionList = new ArrayList<>();

//  private List<CDIButtonCellAction<T>> cellActionList = new ArrayList<>();
//  private List<CDIButtonRowAction<T>> rowActionList = new ArrayList<>();
  private List<Consumer<TableRow<T>>> rowActionList = new ArrayList<>();
  public CDIButtonCell() {
  }

  @PostConstruct
  public void init() {
    if (logger.isDebugEnabled()) {
      logger.debug("CDIButtonCell->init");
    }
    cellButton = new Button(getButtonLabelText());

    cellButton.setOnAction(t -> {

      rowActionList.forEach(c -> {
        if (logger.isDebugEnabled()) {
          logger.debug("execute rowActionList buttonCellAction-> " + c);
        }
        final TableRow<T> tableRow = CDIButtonCell.this.getTableRow();
        c.accept(tableRow);
      });

      cellActionList.forEach(c -> {
        if (logger.isDebugEnabled()) {
          logger.debug("execute cellActionList buttonCellAction-> " + c);
        }
        final TableRow<T> tableRow = CDIButtonCell.this.getTableRow();
        final T item = tableRow.getItem();
        c.accept(item);
      });
    });
  }

  public abstract String getButtonLabelText();

  @Override
  protected void updateItem(Boolean t, boolean empty) {
    super.updateItem(t, empty);
    if (!empty) {
      setGraphic(cellButton);
    } else {
      setText(null);
      setGraphic(null);
    }
  }


  public void addCellAction(Consumer<T> action) {
    cellActionList.add(action);
  }


//  private class CDIButtonCellAction<T> {
//    public @Inject @CDILogger Logger logger;
//
//    private Consumer<T> c;
//
//    protected void execute(ActionEvent t, T item) {
//      c.accept(item);
//    }
//  }

//  private class CDIButtonRowAction<T> {
//    public @Inject @CDILogger Logger logger;
//    private Consumer<TableRow<T>> c;
//
//    public void execute(ActionEvent t, TableRow<T> tableRow) {
//      c.accept(tableRow);
//    }
//  }

  public void addRowAction(Consumer<TableRow<T>> action) {
//    final CDIButtonRowAction<T> a = new CDIButtonRowAction<T>() {};
//    a.c = action;
//    rowActionList.add(instanceCreator.activateCDI(a));
    rowActionList.add(action);
  }
}
