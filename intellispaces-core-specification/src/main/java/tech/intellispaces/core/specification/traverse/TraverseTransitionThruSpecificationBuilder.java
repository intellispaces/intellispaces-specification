package tech.intellispaces.core.specification.traverse;

import tech.intellispaces.core.specification.reference.SpaceReference;
import tech.intellispaces.core.specification.reference.SpaceReferences;

public class TraverseTransitionThruSpecificationBuilder {
  private SpaceReference channel;

  public TraverseTransitionThruSpecificationBuilder channel(String name) {
    this.channel = SpaceReferences.build()
        .name(name)
        .build();
    return this;
  }

  public TraverseTransitionThruSpecification build() {
    return new TraverseTransitionThruSpecificationImpl(channel);
  }
}
