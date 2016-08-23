package org.rapidpm.commons.cdi.fx.components.tableview.cell.deletebutton;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 * User: Sven Ruppert
 * Date: 02.10.13
 * Time: 14:15
 */
public class DeleteButtonCellValueFactory
    implements Callback<TableColumn.CellDataFeatures<TransientTableRow, Boolean>, ObservableValue<Boolean>> {


  @Override
  public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<TransientTableRow, Boolean> p) {
    final TransientTableRow value = p.getValue();
    return new SimpleBooleanProperty(value != null);
  }
}
