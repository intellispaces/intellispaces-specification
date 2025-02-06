package tech.intellispaces.core.specification;

import tech.intellispaces.core.specification.reference.SpaceReference;

import java.util.List;

record DomainBoundSpecificationImpl(
    List<SpaceReference> superDomains
) implements DomainBoundSpecification {
}
