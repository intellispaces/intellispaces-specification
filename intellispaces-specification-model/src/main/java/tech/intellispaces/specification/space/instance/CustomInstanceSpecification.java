package tech.intellispaces.specification.space.instance;

import tech.intellispaces.specification.space.constraint.ConstraintSpecification;
import tech.intellispaces.specification.space.reference.SpaceReference;

import java.util.List;
import java.util.Map;

public interface CustomInstanceSpecification {

  /**
   * The instance domain reference.
   */
  SpaceReference domain();

  /**
   * The map of projection alias to projection target instance.
   */
  Map<String, InstanceSpecification> projections();

  /**
   * The instance constraints.
   */
  List<ConstraintSpecification> constraints();
}
