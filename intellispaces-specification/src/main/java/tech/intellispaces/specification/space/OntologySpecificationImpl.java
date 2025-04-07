package tech.intellispaces.specification.space;

import java.util.List;

record OntologySpecificationImpl(
    List<DomainSpecification> domains,
    List<ChannelSpecification> channels
) implements OntologySpecification {
}
