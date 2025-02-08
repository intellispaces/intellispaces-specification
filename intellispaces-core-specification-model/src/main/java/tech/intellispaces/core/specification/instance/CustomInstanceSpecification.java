package tech.intellispaces.core.specification.instance;

import tech.intellispaces.core.specification.constraint.ConstraintSpecification;
import tech.intellispaces.core.specification.reference.SpaceReference;

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
