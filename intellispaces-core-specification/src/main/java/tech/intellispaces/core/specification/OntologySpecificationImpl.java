package tech.intellispaces.core.specification;

import java.util.List;

record OntologySpecificationImpl(
    List<DomainSpecification> domains,
    List<ChannelSpecification> channels
) implements OntologySpecification {
}
