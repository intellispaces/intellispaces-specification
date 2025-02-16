package tech.intellispaces.core.specification.space;

public interface SuperDomainSpecifications {

  static SuperDomainSpecificationBuilder build() {
    return new SuperDomainSpecificationBuilder();
  }
}
