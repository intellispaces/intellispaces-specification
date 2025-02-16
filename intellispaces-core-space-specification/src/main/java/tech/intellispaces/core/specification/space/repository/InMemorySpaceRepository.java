package tech.intellispaces.core.specification.space.repository;

import tech.intellispaces.core.specification.space.DomainSpecification;
import tech.intellispaces.core.specification.space.OntologySpecification;
import tech.intellispaces.core.specification.space.exception.SpecificationException;
import tech.intellispaces.core.specification.space.exception.SpecificationExceptions;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InMemorySpaceRepository implements SpaceRepository {
  private Map<String, DomainSpecification> nameToDomainSpecification;

  public InMemorySpaceRepository(OntologySpecification spec) {
    loadSpecification(spec);
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
    return nameToDomainSpecification.get(domainName);
  }

  void loadSpecification(OntologySpecification spec) {
    nameToDomainSpecification = spec.domains().stream()
        .collect(Collectors.toMap(DomainSpecification::name, Function.identity()));
  }
}
