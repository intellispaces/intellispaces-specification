package tech.intellispaces.specification.space;

import java.nio.file.Path;

public class FileSpecificationBuilder {
  private final Path specPath;
  private OntologySpecification ontology;

  public FileSpecificationBuilder(Path specPath) {
    this.specPath = specPath;
  }

  public FileSpecificationBuilder ontology(OntologySpecification ontology) {
    this.ontology = ontology;
    return this;
  }

  public FileSpecification build() {
    return new FileSpecificationImpl(
        specPath,
        ontology
    );
  }
}
