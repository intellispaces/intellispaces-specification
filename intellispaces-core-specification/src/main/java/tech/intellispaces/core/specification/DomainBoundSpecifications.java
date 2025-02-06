package tech.intellispaces.core.specification;

import tech.intellispaces.core.specification.reference.SpaceReference;

import java.util.List;

public interface DomainBoundSpecifications {

  static DomainBoundSpecification get(List<SpaceReference> superDomains) {
    return new DomainBoundSpecificationImpl(superDomains);
  }
}
