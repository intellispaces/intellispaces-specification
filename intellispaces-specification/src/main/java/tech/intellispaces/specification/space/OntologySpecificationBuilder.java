package tech.intellispaces.specification.space;

import java.util.List;

public class OntologySpecificationBuilder {
  private List<DomainSpecification> domains = List.of();
  private List<ChannelSpecification> channels = List.of();

  public OntologySpecificationBuilder domains(List<DomainSpecification> domains) {
    this.domains = domains;
    return this;
  }

  public OntologySpecificationBuilder channels(List<ChannelSpecification> channels) {
    this.channels = channels;
    return this;
  }

  public OntologySpecification build() {
    return new OntologySpecificationImpl(
        domains, channels
    );
  }
}
