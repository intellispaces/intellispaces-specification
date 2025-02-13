package tech.intellispaces.core.specification;

public interface SpecificationItem {

  SpecificationItemType type();

  DomainSpecification asDomainSpecification();

  ChannelSpecification asChannelSpecification();
}
