package tech.intellispaces.specification.space;

import tech.intellispaces.commons.exception.UnexpectedExceptions;

import java.util.List;

record ChannelSpecificationImpl(
  byte[] id,
  String name,
  String alias,
  String description,
  ChannelSideSpecification source,
  ChannelSideSpecification target,
  List<ChannelSpecification> qualifiers,
  List<AllowedTraverseType> allowedTraverses
) implements ChannelSpecification {

  @Override
  public SpecificationItemType type() {
    return SpecificationItemTypes.Channel;
  }

  @Override
  public DomainSpecification asDomainSpecification() {
    throw UnexpectedExceptions.withMessage("Attempt to cast channel specification to domain specification");
  }

  @Override
  public ChannelSpecification asChannelSpecification() {
    return this;
  }
}
