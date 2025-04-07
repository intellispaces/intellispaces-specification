package tech.intellispaces.specification.space;

import tech.intellispaces.commons.entity.Enumeration;
import tech.intellispaces.specification.space.exception.SpecificationException;
import tech.intellispaces.specification.space.exception.SpecificationExceptions;

public enum SpecificationVersions implements SpecificationVersion, Enumeration<SpecificationVersion> {

  V0p1("0.1");

  private final String naming;

  SpecificationVersions(String naming) {
    this.naming = naming;
  }

  public String wording() {
    return naming;
  }

  public static SpecificationVersions from(String version) throws SpecificationException {
    for (SpecificationVersions option : values()) {
      if (option.naming.equals(version)) {
        return option;
      }
    }
    throw SpecificationExceptions.withMessage("Unsupported specification version: " + version);
  }

  public static SpecificationVersions from(SpecificationVersion version) {
    return values()[version.ordinal()];
  }
}
