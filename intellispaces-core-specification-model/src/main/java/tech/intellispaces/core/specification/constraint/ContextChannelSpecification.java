package tech.intellispaces.core.specification.constraint;

import tech.intellispaces.core.specification.instance.InstanceSpecification;
import tech.intellispaces.core.specification.reference.Reference;

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
   * The channel target domain.
   */
  Reference targetDomain();

  /**
   * The channel target alias.
   */
  String targetAlias();

  /**
   * The target value.
   */
  InstanceSpecification targetValue();

  List<String> allowedTraverse();

  List<ConstraintSpecification> constraints();
}
