package tech.intellispaces.core.specification;

import java.util.List;

record DomainSpecificationImpl(
    String name,
    String did,
    String description,
    List<SuperDomainSpecification> superDomains,
    List<ChannelSpecification> channels
) implements DomainSpecification {
}
