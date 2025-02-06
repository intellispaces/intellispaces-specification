package tech.intellispaces.core.specification.reference;

import java.util.Objects;

record SpaceReferenceImpl(
    String name
) implements SpaceReference {

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof SpaceReference that)) {
      return false;
    }
    return Objects.equals(name, that.name());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(name);
  }
}
