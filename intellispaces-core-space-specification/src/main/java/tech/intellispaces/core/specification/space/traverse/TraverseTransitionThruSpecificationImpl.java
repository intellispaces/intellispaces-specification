package tech.intellispaces.core.specification.space.traverse;

import tech.intellispaces.commons.base.exception.UnexpectedExceptions;
import tech.intellispaces.core.specification.space.reference.SpaceReference;

import java.util.Objects;

record TraverseTransitionThruSpecificationImpl(
    SpaceReference channel
) implements TraverseTransitionThruSpecification {

  @Override
  public boolean isToTransition() {
    return false;
  }

  @Override
  public boolean isThruTransition() {
    return true;
  }

  @Override
  public TraverseTransitionToSpecification asToTransition() {
    throw UnexpectedExceptions.withMessage("It is traverse transition THRU");
  }

  @Override
  public TraverseTransitionThruSpecification asThruTransition() {
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof TraverseTransitionThruSpecification that)) {
      return false;
    }
    return Objects.equals(channel, that.channel());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(channel);
  }
}
