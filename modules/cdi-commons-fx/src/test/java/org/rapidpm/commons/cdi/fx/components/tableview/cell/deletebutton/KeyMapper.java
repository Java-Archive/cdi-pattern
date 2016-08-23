package org.rapidpm.commons.cdi.fx.components.tableview.cell.deletebutton;

import org.rapidpm.commons.cdi.registry.property.CDIPropertyRegistryService;
import org.rapidpm.commons.cdi.registry.property.PropertyRegistryService;

import javax.inject.Inject;

/**
 * Created by ts40 on 03.03.14.
 */
public class KeyMapper {

  private @Inject
  @CDIPropertyRegistryService
  PropertyRegistryService propertyRegistryService;


  public String map(String delete) {
    return null;
  }
}
