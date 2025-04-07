package tech.intellispaces.specification.space.repository;

import tech.intellispaces.specification.space.DomainSpecification;
import tech.intellispaces.specification.space.exception.SpecificationException;

/**
 * The space repository.
 */
public interface SpaceRepository {

  DomainSpecification findDomain(String domainName) throws SpecificationException;

  DomainSpecification findDomainNullable(String domainName);
}
