package tech.intellispaces.core.specification.space;

import tech.intellispaces.core.specification.space.reference.SpaceReference;

import java.util.List;

record DomainBoundSpecificationImpl(
    List<SpaceReference> superDomains
) implements DomainBoundSpecification {
}
