package tech.intellispaces.specification.space;

import tech.intellispaces.core.id.IdentifierFunctions;

import java.util.List;

public class ChannelSpecificationBuilder {
  private byte[] id;
  private String name;
  private String alias;
  private String description;
  private ChannelSideSpecification source;
  private ChannelSideSpecification target;
  private List<ChannelSpecification> qualifiers = List.of();
  private List<AllowedTraverseType> allowedTraverses = List.of(AllowedTraverseTypes.Mapping);

  public ChannelSpecificationBuilder id(byte[] id) {
    this.id = id;
    return this;
  }

  public ChannelSpecificationBuilder id(String  id) {
    this.id = IdentifierFunctions.parseHexString(id);
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
        id,
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
