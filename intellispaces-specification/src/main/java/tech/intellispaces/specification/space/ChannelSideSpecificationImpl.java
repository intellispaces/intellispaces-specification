package tech.intellispaces.specification.space;

import tech.intellispaces.specification.space.constraint.ConstraintSpecification;
import tech.intellispaces.specification.space.instance.InstanceSpecification;
import tech.intellispaces.specification.space.reference.SpaceReference;

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
