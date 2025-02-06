package tech.intellispaces.core.specification.repository;

import tech.intellispaces.core.specification.DomainSpecification;
import tech.intellispaces.core.specification.exception.SpecificationException;
import tech.intellispaces.core.specification.exception.SpecificationExceptions;

import java.util.ArrayList;
import java.util.List;

public class UnitedOntologyRepository implements OntologyRepository {
  private final List<OntologyRepository> repositories = new ArrayList<>();

  public void addRepository(OntologyRepository repository) {
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
    for (OntologyRepository rep : repositories) {
      DomainSpecification domain = rep.findDomainNullable(domainName);
      if (domain != null) {
        return domain;
      }
    }
    return null;
  }
}
