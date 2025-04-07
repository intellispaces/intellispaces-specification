package tech.intellispaces.specification.space;

import java.nio.file.Path;

record SpecificationImpl(
    Path specPath,
    OntologySpecification ontology
) implements Specification {
}
