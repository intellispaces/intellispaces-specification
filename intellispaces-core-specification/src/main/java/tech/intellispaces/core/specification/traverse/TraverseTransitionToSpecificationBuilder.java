package tech.intellispaces.core.specification.traverse;

import tech.intellispaces.core.specification.reference.SpaceReference;
import tech.intellispaces.core.specification.reference.SpaceReferences;

public class TraverseTransitionToSpecificationBuilder {
  private SpaceReference domain;
  private boolean isSuperDomain;

  public TraverseTransitionToSpecificationBuilder domain(String name) {
    this.domain = SpaceReferences.build()
        .name(name)
        .build();
    return this;
  }

  public TraverseTransitionToSpecificationBuilder isSuperDomain(boolean superDomain) {
    isSuperDomain = superDomain;
    return this;
  }

  public TraverseTransitionToSpecification build() {
    return new TraverseTransitionToSpecificationImpl(domain, isSuperDomain);
  }
}
