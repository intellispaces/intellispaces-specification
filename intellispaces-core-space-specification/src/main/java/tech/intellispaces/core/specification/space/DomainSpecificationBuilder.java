package tech.intellispaces.core.specification.space;

import java.util.List;

public class DomainSpecificationBuilder {
  private String name;
  private String did;
  private String description;
  private List<SuperDomainSpecification> superDomains = List.of();
  private List<ChannelSpecification> channels;

  public DomainSpecificationBuilder name(String name) {
    this.name = name;
    return this;
  }

  public DomainSpecificationBuilder did(String did) {
    this.did = did;
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
        did,
        description,
        superDomains,
        channels
    );
  }
}
