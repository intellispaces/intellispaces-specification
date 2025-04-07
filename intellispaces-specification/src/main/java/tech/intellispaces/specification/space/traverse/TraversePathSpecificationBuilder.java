package tech.intellispaces.specification.space.traverse;

import tech.intellispaces.specification.space.reference.SpaceReference;
import tech.intellispaces.specification.space.reference.SpaceReferences;

import java.util.List;

public class TraversePathSpecificationBuilder {
  private SpaceReference sourceDomain;
  private List<TraverseTransitionSpecification> transitions;

  public TraversePathSpecificationBuilder sourceDomain(String name) {
    this.sourceDomain = SpaceReferences.build()
        .name(name)
        .build();
    return this;
  }

  public TraversePathSpecificationBuilder transitions(List<TraverseTransitionSpecification> transitions) {
    this.transitions = transitions;
    return this;
  }

  public TraversePathSpecification build() {
    return new TraversePathSpecificationImpl(
        sourceDomain,
        transitions != null ? List.copyOf(transitions) : List.of()
    );
  }
}
