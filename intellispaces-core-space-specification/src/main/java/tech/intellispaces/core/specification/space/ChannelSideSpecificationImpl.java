package tech.intellispaces.core.specification.space;

import tech.intellispaces.core.specification.space.constraint.ConstraintSpecification;
import tech.intellispaces.core.specification.space.instance.InstanceSpecification;
import tech.intellispaces.core.specification.space.reference.SpaceReference;

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
