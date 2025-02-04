package tech.intellispaces.core.specification.repository;

import tech.intellispaces.core.specification.DomainSpecification;

/**
 * The ontology repository.
 */
public interface OntologyRepository {

  DomainSpecification findDomain(String domainName);
}
