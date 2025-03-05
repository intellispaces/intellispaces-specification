package tech.intellispaces.core.specification.space.reference;

public interface SpaceReferences {

  static SpaceReference withName(String name) {
    return new SpaceReferenceImpl(name);
  }

  static SpaceReferenceBuilder build() {
    return new SpaceReferenceBuilder();
  }
}
