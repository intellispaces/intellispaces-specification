package tech.intellispaces.specification.space;

import tech.intellispaces.specification.space.constraint.ConstraintSpecification;
import tech.intellispaces.specification.space.instance.InstanceSpecification;
import tech.intellispaces.specification.space.reference.SpaceReference;

import java.util.List;

/**
 * The channel source/target side specification.
 */
public interface ChannelSideSpecification {

  /**
   * The side alias.
   */
  String alias();

  /**
   * The side domain reference.
   */
  SpaceReference domain();

  /**
   * The side domain bounds.
   */
  DomainBoundSpecification domainBounds();

  /**
   * The side constraints.
   */
  List<ConstraintSpecification> constraints();

  /**
   * The side instance.
   */
  InstanceSpecification instance();

  /**
   * The side immobility type.
   */
  ImmobilityType immobilityType();
}
