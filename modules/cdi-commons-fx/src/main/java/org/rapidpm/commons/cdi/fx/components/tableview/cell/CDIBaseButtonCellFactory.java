package org.rapidpm.commons.cdi.fx.components.tableview.cell;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import org.rapidpm.commons.cdi.se.CDIContainerSingleton;

import javax.enterprise.inject.Instance;

/**
 * Created by ts40 on 19.03.2014.
 *
 * RT == RowType
 */
public abstract class CDIBaseButtonCellFactory<RT> implements Callback<TableColumn<RT, ?>, TableCell<RT, ?>> {

    public CDIBaseButtonCellFactory() {
        CDIContainerSingleton.getInstance().activateCDI(this);
    }

    @Override public TableCell<RT, ?> call(TableColumn<RT, ?> rtTableColumn) {
        return (TableCell<RT, ?>) getButtonCellInstance().get();
    }

    public abstract Instance getButtonCellInstance();
}
