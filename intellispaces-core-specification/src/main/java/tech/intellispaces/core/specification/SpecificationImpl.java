package tech.intellispaces.core.specification;

import java.nio.file.Path;

record SpecificationImpl(
    Path specPath,
    OntologySpecification ontology
) implements Specification {
}
