package tech.intellispaces.core.specification.space;

import tech.intellispaces.commons.entity.Enumeration;

public enum ImmobilityTypes implements Enumeration<ImmobilityType>, ImmobilityType {

  Unmovable,

  Movable,

  Undefined;

  @Override
  public String alias() {
    return name();
  }

  public static ImmobilityType fromAlias(String alias) {
    return ImmobilityTypes.valueOf(alias);
  }
}
