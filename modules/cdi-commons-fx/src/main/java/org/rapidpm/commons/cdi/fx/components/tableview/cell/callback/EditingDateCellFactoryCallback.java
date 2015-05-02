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


import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import org.rapidpm.commons.cdi.format.CDISimpleDateFormatter;
import org.rapidpm.commons.cdi.logger.CDILogger;
import org.rapidpm.commons.cdi.se.CDIContainerSingleton;
import org.rapidpm.commons.cdi.logger.Logger;


import javax.annotation.PostConstruct;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: Sven Ruppert Date: 13.09.13 Time: 07:44
 */
public class EditingDateCellFactoryCallback<S> implements Callback<TableColumn<S, ? extends Date>, TableCell<S, ? extends Date>> {


    public EditingDateCellFactoryCallback() {
        CDIContainerSingleton.getInstance().activateCDI(this);
    }

    @Override
    public TableCell<S, ? extends Date> call(TableColumn<S, ? extends Date> tTableColumn) {
        return editingCellInstance.get();
    }

    @Inject Instance<EditingCell> editingCellInstance;

    public static class EditingCell<S> extends AbstractEditingCell<S,Date> {

        private @Inject  @CDILogger  Logger logger;
        @Inject @CDISimpleDateFormatter(value = "default.yyyyMMdd")
        SimpleDateFormat sdf;   //TODO von aussen setzen

        //        @Inject Instance<DatePicker> datePickerInstance;
//        private  @Inject DatePicker datePicker;
        private DatePicker datePicker = new DatePicker();

        public EditingCell() {
        }

        @PostConstruct
        public void init() {
            if (getItem() == null) {

            } else {
                setText(sdf.format(getItem()));
            }
        }

        @Override
        public void commitEdit(Date date) {
            super.commitEdit(date);
            if (logger.isDebugEnabled()) {
                logger.debug("commitEdit-> date = " + date);
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            final Date item = getItem();
            final String format = sdf.format(item);
            if (logger.isDebugEnabled()) {
                logger.debug("cancelEdit->format = " + format);
            }
            setText(format);
            setGraphic(null);
        }

        public void createValueField() {
//            if (logger.isDebugEnabled()) {
//                logger.debug("createValueField");
//            }
//            //datePicker = datePickerInstance.get();
//            datePicker.setDateFormat(sdf);
//            datePicker.getTextField().setText(getString());
//            datePicker.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
//            datePicker.getTextField().setEditable(false);
//            final ObjectProperty<Date> dateObjectProperty = datePicker.selectedDateProperty();
//            dateObjectProperty.addListener(new ChangeListener<Date>() {
//                @Override
//                public void changed(ObservableValue<? extends Date> observableValue, Date date, Date date2) {
//                    if (logger.isDebugEnabled()) {
//                        logger.debug("changed");
//                    }
//                    commitEdit(datePicker.getSelectedDate());
//                    setItem(datePicker.getSelectedDate());
//                }
//            });
        }


        @Override
        public void updateItemIsEditing() {
//            if (datePicker != null) {
//                datePicker.getTextField().setText(getString());
//            }
        }

        @Override
        public String getStringIfItemNotNull() {
            return sdf.format(getItem());
        }

        @Override
        public void startEditIsNotEmptyLastActions() {
            // nothing to do here
        }

        @Override
        public Node getGraphicNode() {
            return datePicker;
        }
    }
}

