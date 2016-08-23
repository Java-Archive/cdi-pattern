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

package org.rapidpm.commons.cdi.fx.components.tableview.cell.callback;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.util.Callback;
import org.rapidpm.commons.cdi.logger.CDILogger;
import org.rapidpm.commons.cdi.logger.Logger;
import org.rapidpm.commons.cdi.se.CDIContainerSingleton;

import javax.inject.Inject;
import java.util.List;

/**
 * User: Sven Ruppert
 * Date: 20.09.13
 * Time: 13:55
 */
public abstract class AbstractEditingComboBoxCellFactoryCallBack<T extends AbstractEditingComboBoxCellFactoryCallBack.GenericComboBoxCell>
    implements Callback<TableColumn<T, ?>, TableCell<T, ?>> {


  protected AbstractEditingComboBoxCellFactoryCallBack() {
    CDIContainerSingleton.getInstance().activateCDI(this);
  }

  @Override
  public TableCell<T, ?> call(TableColumn<T, ?> filteredTableDataRowTableColumn) {
    return getComboBoxCellInstance();
  }

  public abstract T getComboBoxCellInstance();

  public abstract static  class GenericComboBoxCell<S, VT> extends ComboBoxTableCell<S, VT> {

     @Inject @CDILogger private Logger logger;
    private boolean readOnlyView = false;

    @Override
    public void updateItem(VT o, boolean b) {
      if (logger.isDebugEnabled()) {
        logger.debug("ComboBoxTableCell->updateItem " + o);
      }
      super.updateItem(o, b);
      if (o == null) {
        //
      } else {
        final TableRow tableRow = getTableRow();
        if (tableRow == null) {
          if (logger.isDebugEnabled()) {
            logger.debug("getTableRow(); == null");
          }
        } else {
          final S row = (S) tableRow.getItem();
          getItems().clear();
          if (disableComboBox(row)) {
            this.setDisable(true);
          } else if (readOnlyView) {
            this.setDisable(true);
          } else {

            final List<VT> comboBoxValues = createComboBoxValues(row);
            getItems().addAll(comboBoxValues);

            this.setDisable(false);
          }
          workOnRowItself(row);
        }
      }
    }

    /**
     * logic to disable the combobox, for example if the value ist null or ...
     *
     * @return
     */
    public abstract boolean disableComboBox(final S row);

    public abstract List<VT> createComboBoxValues(final S row);

    public abstract void workOnRowItself(final S row);

    protected abstract GenericComboBoxCell<S, VT> getComboBoxCellRef();

    public boolean isReadOnlyView() {
      return readOnlyView;
    }

    public void setReadOnlyView(boolean readOnlyView) {
      this.readOnlyView = readOnlyView;
    }
  }

}
