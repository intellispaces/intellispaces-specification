package tech.intellispaces.core.specification.space;

public interface SpecificationItem {

  SpecificationItemType type();

  DomainSpecification asDomainSpecification();

  ChannelSpecification asChannelSpecification();
}
