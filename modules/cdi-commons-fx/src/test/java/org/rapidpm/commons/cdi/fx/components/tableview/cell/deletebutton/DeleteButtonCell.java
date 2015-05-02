package org.rapidpm.commons.cdi.fx.components.tableview.cell.deletebutton;


import org.rapidpm.commons.cdi.fx.components.tableview.cell.CDIButtonCell;
import org.rapidpm.commons.cdi.logger.CDILogger;
import org.rapidpm.commons.cdi.logger.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * User: Sven Ruppert
 * Date: 02.10.13
 * Time: 14:06
 */
public class DeleteButtonCell extends CDIButtonCell<TransientTableRow> {

    private @Inject @CDILogger Logger logger;
    private @Inject KeyMapper mapper;
    private @Inject DeleteButtonLogic logic;

    @Override
    public String getButtonLabelText() {
        return mapper.map("delete");
    }

    public DeleteButtonCell() {

    }

    @PostConstruct
    public void init(){
        super.init();
        if (logger.isDebugEnabled()) {
            logger.debug("DeleteButtonCell->init");
        }
      addCellAction(System.out::println);

    }

}
