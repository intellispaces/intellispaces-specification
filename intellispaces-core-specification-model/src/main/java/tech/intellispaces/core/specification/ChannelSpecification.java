package tech.intellispaces.core.specification;

import java.util.List;

/**
 * The channel specification.
 */
public interface ChannelSpecification {

  /**
   * The channel identifier.
   */
  String cid();

  /**
   * The channel qualified name.
   */
  String name();

  /**
   * The channel context alias.
   */
  String alias();

  /**
   * The domain description.
   */
  String description();

  /**
   * The source specification.
   */
  ChannelSideSpecification source();

  /**
   * The target specification.
   */
  ChannelSideSpecification target();

  /**
   * Channel qualifier specifications.
   */
  List<ChannelSpecification> qualifiers();

  /**
   * Allowed traverse types.
   */
  List<AllowedTraverseType> allowedTraverses();
}
