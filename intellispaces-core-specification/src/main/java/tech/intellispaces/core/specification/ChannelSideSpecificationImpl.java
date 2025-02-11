package tech.intellispaces.core.specification;

import tech.intellispaces.core.specification.constraint.ConstraintSpecification;
import tech.intellispaces.core.specification.instance.InstanceSpecification;
import tech.intellispaces.core.specification.reference.SpaceReference;

import java.util.List;

record ChannelSideSpecificationImpl(
  String alias,
  SpaceReference domain,
  DomainBoundSpecification domainBounds,
  List<ConstraintSpecification> constraints,
  InstanceSpecification instance,
  ImmobilityType immobilityType
) implements ChannelSideSpecification {
}
