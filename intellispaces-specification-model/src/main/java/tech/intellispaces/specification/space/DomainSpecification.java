package tech.intellispaces.specification.space;

import java.util.List;

/**
 * The domain specification.
 */
public interface DomainSpecification extends SpecificationItem {

  /**
   * The domain identifier.
   */
  byte[] id();

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
  List<ChannelSpecification> channels();
}
