package tech.intellispaces.core.specification;

import tech.intellispaces.core.specification.constraint.ContextChannelSpecification;

import java.util.List;

/**
 * The domain specification.
 */
public interface DomainSpecification {

  /**
   * The domain identifier.
   */
  String did();

  /**
   * The domain qualified name.
   */
  String name();

  /**
   * The domain description.
   */
  String description();

  /**
   * Super domain specifications.
   */
  List<SuperDomainSpecification> superDomains();

  /**
   * Domain channel specifications.
   */
  List<ContextChannelSpecification> channels();
}
