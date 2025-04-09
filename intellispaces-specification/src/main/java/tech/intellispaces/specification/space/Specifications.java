package tech.intellispaces.specification.space;

import java.nio.file.Path;

public interface Specifications {

  static FileSpecificationBuilder build(Path path) {
    return new FileSpecificationBuilder(path);
  }
}
