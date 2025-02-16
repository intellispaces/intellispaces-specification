package tech.intellispaces.core.specification.space.instance;

import tech.intellispaces.commons.base.entity.Enumerable;
import tech.intellispaces.commons.base.entity.Enumeration;

public enum InstanceTypes implements InstanceType, Enumeration<InstanceType>, Enumerable<InstanceType> {

  String,

  Reference,

  Custom
}
