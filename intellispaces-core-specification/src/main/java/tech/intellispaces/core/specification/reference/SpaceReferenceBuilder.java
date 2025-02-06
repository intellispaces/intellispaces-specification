package tech.intellispaces.core.specification.reference;

public class SpaceReferenceBuilder {
  private String name;

  public SpaceReferenceBuilder name(String name) {
    this.name = name;
    return this;
  }

  public SpaceReference build() {
    return new SpaceReferenceImpl(
        name
    );
  }
}
