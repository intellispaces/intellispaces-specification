package tech.intellispaces.core.specification;

import tech.intellispaces.core.specification.constraint.ConstraintSpecification;
import tech.intellispaces.core.specification.reference.SpaceReference;

import java.util.List;

public class SuperDomainSpecificationBuilder {
  private SpaceReference domain;
  private List<ConstraintSpecification> constraints = List.of();

  public SuperDomainSpecificationBuilder domain(SpaceReference domain) {
    this.domain = domain;
    return this;
  }

  public SuperDomainSpecificationBuilder constraints(List<ConstraintSpecification> constraints) {
    this.constraints = constraints;
    return this;
  }

  public SuperDomainSpecification get() {
    return new SuperDomainSpecificationImpl(
        domain,
        constraints
    );
  }
}
