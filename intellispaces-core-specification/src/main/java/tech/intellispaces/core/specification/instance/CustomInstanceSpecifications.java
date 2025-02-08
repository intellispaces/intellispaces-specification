package tech.intellispaces.core.specification.instance;

import tech.intellispaces.core.specification.constraint.ConstraintSpecification;
import tech.intellispaces.core.specification.reference.SpaceReference;

import java.util.List;
import java.util.Map;

public interface CustomInstanceSpecifications {

  static CustomInstanceSpecification get(
      SpaceReference domain,
      Map<String, InstanceSpecification> projections,
      List<ConstraintSpecification> constraints
  ) {
    return new CustomInstanceSpecificationImpl(domain, projections, constraints);
  }
}
