package tech.intellispaces.core.specification;

import tech.intellispaces.core.specification.constraint.ConstraintSpecification;
import tech.intellispaces.core.specification.instance.InstanceSpecification;
import tech.intellispaces.core.specification.reference.SpaceReference;

import java.util.List;

/**
 * The context channel specification.
 */
public interface ContextChannelSpecification {

  /**
   * The channel identifier.
   */
  String cid();

  /**
   * The channel context alias.
   */
  String alias();

  /**
   * The channel qualified name.
   */
  String name();

  /**
   * The channel description.
   */
  String description();

  List<ContextChannelSpecification> projections();

  /**
   * The channel target domain reference.
   */
  SpaceReference targetDomain();

  DomainBoundSpecification targetDomainBounds();

  /**
   * The channel target alias.
   */
  String targetAlias();

  /**
   * The target instance.
   */
  InstanceSpecification targetInstance();

  List<ConstraintSpecification> targetConstraints();

  List<String> allowedTraverse();
}
