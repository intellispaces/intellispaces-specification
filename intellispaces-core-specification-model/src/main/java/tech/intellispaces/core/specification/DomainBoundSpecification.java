package tech.intellispaces.core.specification;

import tech.intellispaces.core.specification.reference.SpaceReference;

import java.util.List;

public interface DomainBoundSpecification {

  List<SpaceReference> superDomains();
}
