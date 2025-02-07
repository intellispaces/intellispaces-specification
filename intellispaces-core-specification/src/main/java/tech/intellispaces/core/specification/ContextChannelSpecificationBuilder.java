package tech.intellispaces.core.specification;

import tech.intellispaces.core.specification.constraint.ConstraintSpecification;
import tech.intellispaces.core.specification.instance.InstanceSpecification;
import tech.intellispaces.core.specification.reference.SpaceReference;

import java.util.List;

public class ContextChannelSpecificationBuilder {
  private String alias;
  private String cid;
  private String name;
  private String description;
  private List<ContextChannelSpecification> projections = List.of();
  private SpaceReference targetDomain;
  private DomainBoundSpecification targetDomainBounds;
  private String targetAlias;
  private InstanceSpecification targetInstance;
  private List<ConstraintSpecification> targetConstraints = List.of();
  private ImmobilityType targetImmobilityType = ImmobilityTypes.General;
  private List<String> allowedTraverses;

  public ContextChannelSpecificationBuilder alias(String alias) {
    this.alias = alias;
    return this;
  }

  public ContextChannelSpecificationBuilder cid(String cid) {
    this.cid = cid;
    return this;
  }

  public ContextChannelSpecificationBuilder name(String name) {
    this.name = name;
    return this;
  }

  public ContextChannelSpecificationBuilder description(String description) {
    this.description = description;
    return this;
  }

  public ContextChannelSpecificationBuilder projections(List<ContextChannelSpecification> projections) {
    this.projections = projections;
    return this;
  }

  public ContextChannelSpecificationBuilder targetDomain(SpaceReference targetDomain) {
    this.targetDomain = targetDomain;
    return this;
  }

  public ContextChannelSpecificationBuilder targetDomainBounds(DomainBoundSpecification targetDomainBounds) {
    this.targetDomainBounds = targetDomainBounds;
    return this;
  }

  public ContextChannelSpecificationBuilder targetConstraints(List<ConstraintSpecification> constraints) {
    this.targetConstraints = constraints;
    return this;
  }

  public ContextChannelSpecificationBuilder targetImmobilityType(ImmobilityType targetImmobilityType) {
    this.targetImmobilityType = targetImmobilityType;
    return this;
  }

  public ContextChannelSpecificationBuilder targetAlias(String alias) {
    this.targetAlias = alias;
    return this;
  }

  public ContextChannelSpecificationBuilder targetInstance(InstanceSpecification instance) {
    this.targetInstance = instance;
    return this;
  }

  public ContextChannelSpecificationBuilder allowedTraverses(List<String> allowedTraverses) {
    this.allowedTraverses = allowedTraverses;
    return this;
  }

  public ContextChannelSpecification get() {
    return new ContextChannelSpecificationImpl(
        alias,
        cid,
        name,
        description,
        projections,
        targetDomain,
        targetDomainBounds,
        targetAlias,
        targetInstance,
        targetConstraints,
        targetImmobilityType,
        allowedTraverses
    );
  }
}
