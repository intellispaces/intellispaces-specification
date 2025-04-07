package tech.intellispaces.specification.space.traverse;

import tech.intellispaces.specification.space.reference.SpaceReference;

import java.util.List;
import java.util.Objects;

record TraversePathSpecificationImpl(
  SpaceReference sourceDomain,
  List<TraverseTransitionSpecification> transitions
) implements TraversePathSpecification {

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof TraversePathSpecification that)) {
      return false;
    }
    return Objects.equals(sourceDomain, that.sourceDomain()) && Objects.equals(transitions, that.transitions());
  }

  @Override
  public int hashCode() {
    return Objects.hash(sourceDomain, transitions);
  }
}
