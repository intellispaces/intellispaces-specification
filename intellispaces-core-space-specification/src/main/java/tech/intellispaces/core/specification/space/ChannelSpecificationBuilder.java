package tech.intellispaces.core.specification.space;

import java.util.List;

public class ChannelSpecificationBuilder {
  private String cid;
  private String name;
  private String alias;
  private String description;
  private ChannelSideSpecification source;
  private ChannelSideSpecification target;
  private List<ChannelSpecification> qualifiers = List.of();
  private List<AllowedTraverseType> allowedTraverses = List.of(AllowedTraverseTypes.Mapping);

  public ChannelSpecificationBuilder cid(String cid) {
    this.cid = cid;
    return this;
  }

  public ChannelSpecificationBuilder name(String name) {
    this.name = name;
    return this;
  }

  public ChannelSpecificationBuilder alias(String alias) {
    this.alias = alias;
    return this;
  }

  public ChannelSpecificationBuilder description(String description) {
    this.description = description;
    return this;
  }

  public ChannelSpecificationBuilder source(ChannelSideSpecification source) {
    this.source = source;
    return this;
  }

  public ChannelSpecificationBuilder target(ChannelSideSpecification target) {
    this.target = target;
    return this;
  }

  public ChannelSpecificationBuilder qualifiers(List<ChannelSpecification> qualifiers) {
    this.qualifiers = qualifiers;
    return this;
  }

  public ChannelSpecificationBuilder allowedTraverses(List<AllowedTraverseType> allowedTraverses) {
    this.allowedTraverses = allowedTraverses;
    return this;
  }

  public ChannelSpecification build() {
    return new ChannelSpecificationImpl(
        cid,
        name,
        alias,
        description,
        source,
        target,
        qualifiers,
        allowedTraverses
    );
  }
}
