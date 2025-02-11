package tech.intellispaces.core.specification;

import tech.intellispaces.core.specification.constraint.ConstraintSpecification;
import tech.intellispaces.core.specification.instance.InstanceSpecification;
import tech.intellispaces.core.specification.reference.SpaceReference;

import java.util.List;

public class ChannelSideSpecificationBuilder {
  private String alias;
  private SpaceReference domain;
  private DomainBoundSpecification domainBounds;
  private List<ConstraintSpecification> constraints = List.of();
  private InstanceSpecification instance;
  private ImmobilityType immobilityType;

  public ChannelSideSpecificationBuilder alias(String alias) {
    this.alias = alias;
    return this;
  }

  public ChannelSideSpecificationBuilder domain(SpaceReference domain) {
    this.domain = domain;
    return this;
  }

  public ChannelSideSpecificationBuilder domainBounds(DomainBoundSpecification domainBounds) {
    this.domainBounds = domainBounds;
    return this;
  }

  public ChannelSideSpecificationBuilder constraints(List<ConstraintSpecification> constraints) {
    this.constraints = constraints;
    return this;
  }

  public ChannelSideSpecificationBuilder instance(InstanceSpecification instance) {
    this.instance = instance;
    return this;
  }

  public ChannelSideSpecificationBuilder immobilityType(ImmobilityType immobilityType) {
    this.immobilityType = immobilityType;
    return this;
  }

  public ChannelSideSpecification build() {
    return new ChannelSideSpecificationImpl(
        alias,
        domain,
        domainBounds,
        constraints,
        instance,
        immobilityType
    );
  }
}
