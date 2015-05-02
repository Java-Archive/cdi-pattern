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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import org.rapidpm.commons.cdi.se.CDIContainerSingleton;


/**
 * User: Sven Ruppert Date: 13.09.13 Time: 07:16
 */
public class EditingStringCellFactoryCallback<S> implements Callback<TableColumn<S, ? extends String>, TableCell<S, ? extends String >> {

    public EditingStringCellFactoryCallback() {
        CDIContainerSingleton.getInstance().activateCDI(this);
    }

    @Override
    public TableCell<S, ? extends String> call(TableColumn<S, ? extends String> tableColumn) {
        return new EditingCell();
    }

    public static class EditingCell<S> extends AbstractEditingCell<S,String> {

        private TextField textField;

        public EditingCell() {
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            setText(getItem());
            setGraphic(null);
        }

        public void createValueField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            textField.focusedProperty().addListener((arg0, arg1, arg2) -> {
                if (!arg2) {
                    commitEdit(textField.getText());
                }
            });
        }

        @Override
        public void updateItemIsEditing() {
            if (textField != null) {
                textField.setText(getString());
            }
        }

        @Override
        public String getStringIfItemNotNull() {
            return getItem();
        }

        @Override
        public void startEditIsNotEmptyLastActions() {
            textField.selectAll();
        }

        @Override
        public Node getGraphicNode() {
            return textField;
        }
    }


}
