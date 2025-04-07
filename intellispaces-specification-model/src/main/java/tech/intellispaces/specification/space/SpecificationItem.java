package tech.intellispaces.specification.space;

public interface SpecificationItem {

  SpecificationItemType type();

  DomainSpecification asDomainSpecification();

  ChannelSpecification asChannelSpecification();
}
