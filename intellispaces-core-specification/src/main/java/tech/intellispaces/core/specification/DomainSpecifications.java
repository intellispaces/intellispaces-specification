package tech.intellispaces.core.specification;

public interface DomainSpecifications {

  static DomainSpecificationBuilder build() {
    return new DomainSpecificationBuilder();
  }
}
