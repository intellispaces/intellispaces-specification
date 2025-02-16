package tech.intellispaces.core.specification.space.repository;

import tech.intellispaces.core.specification.space.DomainSpecification;
import tech.intellispaces.core.specification.space.exception.SpecificationException;
import tech.intellispaces.core.specification.space.exception.SpecificationExceptions;

import java.util.ArrayList;
import java.util.List;

public class UnitedSpaceRepository implements SpaceRepository {
  private final List<SpaceRepository> repositories = new ArrayList<>();

  public void addRepository(SpaceRepository repository) {
    this.repositories.add(repository);
  }

  @Override
  public DomainSpecification findDomain(String domainName) throws SpecificationException {
    DomainSpecification domain = findDomainNullable(domainName);
    if (domain == null) {
      throw SpecificationExceptions.withMessage("Could not to find the specification of the domain '{0}'", domainName);
    }
    return domain;
  }

  @Override
  public DomainSpecification findDomainNullable(String domainName) {
    for (SpaceRepository rep : repositories) {
      DomainSpecification domain = rep.findDomainNullable(domainName);
      if (domain != null) {
        return domain;
      }
    }
    return null;
  }
}
