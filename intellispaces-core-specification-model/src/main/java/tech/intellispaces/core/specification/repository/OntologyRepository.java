package tech.intellispaces.core.specification.repository;

import tech.intellispaces.core.specification.DomainSpecification;
import tech.intellispaces.core.specification.exception.SpecificationException;

/**
 * The ontology repository.
 */
public interface OntologyRepository {

  DomainSpecification findDomain(String domainName) throws SpecificationException;

  DomainSpecification findDomainNullable(String domainName);
}
