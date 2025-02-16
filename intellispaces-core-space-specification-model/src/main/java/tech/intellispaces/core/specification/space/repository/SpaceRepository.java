package tech.intellispaces.core.specification.space.repository;

import tech.intellispaces.core.specification.space.DomainSpecification;
import tech.intellispaces.core.specification.space.exception.SpecificationException;

/**
 * The space repository.
 */
public interface SpaceRepository {

  DomainSpecification findDomain(String domainName) throws SpecificationException;

  DomainSpecification findDomainNullable(String domainName);
}
