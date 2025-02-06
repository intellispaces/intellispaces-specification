package tech.intellispaces.core.specification;

import java.util.List;

record OntologySpecificationImpl(
    List<DomainSpecification> domains
) implements OntologySpecification {
}
