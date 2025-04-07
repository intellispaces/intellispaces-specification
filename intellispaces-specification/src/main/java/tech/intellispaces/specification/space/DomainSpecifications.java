package tech.intellispaces.specification.space;

public interface DomainSpecifications {

  static DomainSpecificationBuilder build() {
    return new DomainSpecificationBuilder();
  }
}
