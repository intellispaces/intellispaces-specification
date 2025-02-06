package tech.intellispaces.core.specification;

public interface SuperDomainSpecifications {

  static SuperDomainSpecificationBuilder build() {
    return new SuperDomainSpecificationBuilder();
  }
}
