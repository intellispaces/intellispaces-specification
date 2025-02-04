package tech.intellispaces.core.specification.traverse;

import java.util.Arrays;
import java.util.List;

public interface TraversePathParseFunctions {

  static TraversePathSpecification parse(String path) {
    List<String> tokens = Arrays.stream(path.split(" "))
        .map(String::trim)
        .toList();


    throw new RuntimeException("Not implemented");
  }
}
