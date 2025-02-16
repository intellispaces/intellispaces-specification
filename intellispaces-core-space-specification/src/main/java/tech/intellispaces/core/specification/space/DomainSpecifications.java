package tech.intellispaces.core.specification.space;

public interface DomainSpecifications {

  static DomainSpecificationBuilder build() {
    return new DomainSpecificationBuilder();
  }
}
