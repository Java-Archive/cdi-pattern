package org.rapidpm.commons.cdi.fx.components.tableview.cell.deletebutton;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import org.rapidpm.commons.cdi.se.CDIContainerSingleton;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

/**
 * User: Sven Ruppert
 * Date: 02.10.13
 * Time: 14:15
 */
public class DeleteButtonCellFactory
    implements Callback<TableColumn<TransientTableRow, ?>, TableCell<TransientTableRow, ?>> {

  @Inject Instance<DeleteButtonCell> deleteButtonCellInstance;

  public DeleteButtonCellFactory() {
    CDIContainerSingleton.getInstance().activateCDI(this);
  }

  @Override
  public TableCell<TransientTableRow, ?> call(TableColumn<TransientTableRow, ?> column) {

    return deleteButtonCellInstance.get();
  }
}
