package tech.intellispaces.specification.space;

import tech.intellispaces.specification.space.reference.SpaceReference;

import java.util.List;

record DomainBoundSpecificationImpl(
    List<SpaceReference> superDomains
) implements DomainBoundSpecification {
}
