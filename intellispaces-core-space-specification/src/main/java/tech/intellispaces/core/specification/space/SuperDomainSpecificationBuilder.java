package tech.intellispaces.core.specification.space;

import tech.intellispaces.core.specification.space.constraint.ConstraintSpecification;
import tech.intellispaces.core.specification.space.reference.SpaceReference;

import java.util.List;

public class SuperDomainSpecificationBuilder {
  private SpaceReference reference;
  private List<ConstraintSpecification> constraints = List.of();

  public SuperDomainSpecificationBuilder reference(SpaceReference reference) {
    this.reference = reference;
    return this;
  }

  public SuperDomainSpecificationBuilder constraints(List<ConstraintSpecification> constraints) {
    this.constraints = constraints;
    return this;
  }

  public SuperDomainSpecification build() {
    return new SuperDomainSpecificationImpl(
        reference,
        constraints
    );
  }
}
