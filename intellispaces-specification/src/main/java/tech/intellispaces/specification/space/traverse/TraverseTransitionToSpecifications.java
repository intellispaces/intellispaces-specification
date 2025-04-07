package tech.intellispaces.specification.space.traverse;

public interface TraverseTransitionToSpecifications {

  static TraverseTransitionToSpecificationBuilder build() {
    return new TraverseTransitionToSpecificationBuilder();
  }
}
