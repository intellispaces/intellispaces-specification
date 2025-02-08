package tech.intellispaces.core.specification.instance;

import tech.intellispaces.core.specification.reference.SpaceReference;

class InstanceSpecificationImpl implements InstanceSpecification {
  private InstanceType type;
  private String string;
  private SpaceReference reference;
  private CustomInstanceSpecification customInstance;

  public InstanceSpecificationImpl(String string) {
    this.string = string;
    this.type = InstanceTypes.String;
  }

  public InstanceSpecificationImpl(SpaceReference reference) {
    this.reference = reference;
    this.type = InstanceTypes.Reference;
  }

  public InstanceSpecificationImpl(CustomInstanceSpecification customInstance) {
    this.customInstance = customInstance;
    this.type = InstanceTypes.Custom;
  }

  @Override
  public InstanceType type() {
    return type;
  }

  @Override
  public boolean isString() {
    return InstanceTypes.String.is(type);
  }

  @Override
  public boolean isReference() {
    return InstanceTypes.Reference.is(type);
  }

  @Override
  public boolean isCustomInstance() {
    return InstanceTypes.Custom.is(type);
  }

  @Override
  public String asString() {
    return string;
  }

  @Override
  public SpaceReference asReference() {
    return reference;
  }

  @Override
  public CustomInstanceSpecification asCustomInstance() {
    return customInstance;
  }
}
