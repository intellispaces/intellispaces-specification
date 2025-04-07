package tech.intellispaces.specification.space.instance;

import tech.intellispaces.commons.entity.Enumerable;
import tech.intellispaces.commons.entity.Enumeration;

public enum InstanceTypes implements InstanceType, Enumeration<InstanceType>, Enumerable<InstanceType> {

  String,

  Reference,

  Custom
}
