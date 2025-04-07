package tech.intellispaces.specification.space;

import java.util.List;

/**
 * The ontology specification.
 */
public interface OntologySpecification {

  List<DomainSpecification> domains();

  List<ChannelSpecification> channels();
}
