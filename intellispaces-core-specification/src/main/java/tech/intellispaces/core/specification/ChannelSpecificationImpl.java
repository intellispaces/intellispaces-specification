package tech.intellispaces.core.specification;

import java.util.List;

record ChannelSpecificationImpl(
  String cid,
  String name,
  String alias,
  String description,
  ChannelSideSpecification source,
  ChannelSideSpecification target,
  List<ChannelSpecification> qualifiers,
  List<AllowedTraverseType> allowedTraverses
) implements ChannelSpecification {
}
