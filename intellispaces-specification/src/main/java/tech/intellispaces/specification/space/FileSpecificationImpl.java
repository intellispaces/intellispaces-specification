package tech.intellispaces.specification.space;

import java.nio.file.Path;

record FileSpecificationImpl(
    Path specPath,
    OntologySpecification ontology
) implements FileSpecification {
}
