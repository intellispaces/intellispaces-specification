package tech.intellispaces.core.specification.traverse;

public interface TraverseTransitionToSpecifications {

  static TraverseTransitionToSpecificationBuilder build() {
    return new TraverseTransitionToSpecificationBuilder();
  }
}
