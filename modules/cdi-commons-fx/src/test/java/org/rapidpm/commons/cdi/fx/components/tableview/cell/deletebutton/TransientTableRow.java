package org.rapidpm.commons.cdi.fx.components.tableview.cell.deletebutton;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

/**
 * Created by Sven Ruppert on 03.03.14.
 */
public class TransientTableRow implements Serializable {

  private StringProperty kunde;
  private StringProperty kostenstelle;
  private StringProperty einmalzahlung;
  private StringProperty rate;
  private BooleanProperty delete;

}
