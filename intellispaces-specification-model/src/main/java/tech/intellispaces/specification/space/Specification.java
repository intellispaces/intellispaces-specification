package tech.intellispaces.specification.space;

import java.nio.file.Path;

/**
 * The specification.
 */
public interface Specification {

  Path specPath();

  OntologySpecification ontology();
}
