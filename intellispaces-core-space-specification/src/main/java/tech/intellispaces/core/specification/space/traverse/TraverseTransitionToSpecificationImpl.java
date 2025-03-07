package tech.intellispaces.core.specification.space.traverse;

import tech.intellispaces.commons.exception.UnexpectedExceptions;
import tech.intellispaces.core.specification.space.reference.SpaceReference;

import java.util.Objects;

record TraverseTransitionToSpecificationImpl(
  SpaceReference domain,
  boolean isSuperDomain
) implements TraverseTransitionToSpecification {

  @Override
  public boolean isToTransition() {
    return true;
  }

  @Override
  public boolean isThruTransition() {
    return false;
  }

  @Override
  public TraverseTransitionToSpecification asToTransition() {
    return this;
  }

  @Override
  public TraverseTransitionThruSpecification asThruTransition() {
    throw UnexpectedExceptions.withMessage("It is traverse transition TO");
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof TraverseTransitionToSpecification that)) {
      return false;
    }
    return isSuperDomain == that.isSuperDomain() && Objects.equals(domain, that.domain());
  }

  @Override
  public int hashCode() {
    return Objects.hash(domain, isSuperDomain);
  }
}
