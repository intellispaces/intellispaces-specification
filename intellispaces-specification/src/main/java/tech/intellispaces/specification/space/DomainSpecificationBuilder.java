package tech.intellispaces.specification.space;

import tech.intellispaces.core.id.IdentifierFunctions;

import java.util.List;

public class DomainSpecificationBuilder {
  private String name;
  private byte[] id;
  private String description;
  private List<SuperDomainSpecification> superDomains = List.of();
  private List<ChannelSpecification> channels;

  public DomainSpecificationBuilder name(String name) {
    this.name = name;
    return this;
  }

  public DomainSpecificationBuilder id(byte[] id) {
    this.id = id;
    return this;
  }

  public DomainSpecificationBuilder id(String id) {
    this.id = IdentifierFunctions.parseHexString(id);
    return this;
  }

  public DomainSpecificationBuilder description(String description) {
    this.description = description;
    return this;
  }

  public DomainSpecificationBuilder superDomains(List<SuperDomainSpecification> domainReferences) {
    this.superDomains = domainReferences;
    return this;
  }

  public DomainSpecificationBuilder channels(List<ChannelSpecification> channels) {
    this.channels = channels;
    return this;
  }

  public DomainSpecification build() {
    return new DomainSpecificationImpl(
        name,
        id,
        description,
        superDomains,
        channels
    );
  }
}
