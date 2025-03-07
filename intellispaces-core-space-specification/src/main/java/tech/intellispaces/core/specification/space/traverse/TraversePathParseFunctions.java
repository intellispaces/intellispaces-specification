package tech.intellispaces.core.specification.space.traverse;

import tech.intellispaces.commons.text.StringFunctions;
import tech.intellispaces.core.specification.space.exception.TraversePathSpecificationException;
import tech.intellispaces.core.specification.space.exception.TraversePathSpecificationExceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TraversePathParseFunctions {

  public static TraversePathSpecification parse(String path) throws TraversePathSpecificationException {
    List<String> tokens = Arrays.stream(path.split(" "))
        .map(String::trim)
        .filter(StringFunctions::isNotEmpty)
        .toList();

    if (tokens.isEmpty()) {
      throw TraversePathSpecificationExceptions.withMessage("Traverse path source is not defined: '{0}'", path);
    }

    String sourceToken = tokens.get(0);
    if (isKeyToken(sourceToken)) {
      throw TraversePathSpecificationExceptions.withMessage("Traverse path source is not defined: '{0}'", path);
    }

    int ind = 1;
    boolean invalid = false;
    var transitions = new ArrayList<TraverseTransitionSpecification>();
    while (ind < tokens.size()) {
      String keyToken = tokens.get(ind);
      if (isToToken(keyToken)) {
        if (ind + 1 < tokens.size()) {
          String nextToken = tokens.get(ind + 1);
          if (isSuperToken(nextToken)) {
            if (ind + 2 < tokens.size()) {
              String nameToken = tokens.get(ind + 2);
              if (isKeyToken(nameToken)) {
                invalid = true;
                break;
              }
              transitions.add(TraverseTransitionToSpecifications.build()
                  .domain(nameToken)
                  .isSuperDomain(true)
                  .build()
              );
              ind += 3;
            } else {
              invalid = true;
              break;
            }
          } else {
            if (isKeyToken(nextToken)) {
              invalid = true;
              break;
            }
            transitions.add(TraverseTransitionToSpecifications.build()
                .domain(nextToken)
                .isSuperDomain(false)
                .build()
            );
            ind += 2;
          }
        } else {
          invalid = true;
          break;
        }
      } else if (isThruToken(keyToken)) {
        if (ind + 1 < tokens.size()) {
          String nameToken = tokens.get(ind + 1);
          if (isKeyToken(nameToken)) {
            invalid = true;
            break;
          }
          transitions.add(TraverseTransitionThruSpecifications.build()
              .channel(nameToken)
              .build()
          );
          ind += 2;
        } else {
          invalid = true;
          break;
        }
      } else {
        invalid = true;
        break;
      }
    }
    if (invalid) {
      throw TraversePathSpecificationExceptions.withMessage("Invalid traverse path: '{0}'", path);
    }

    return TraversePathSpecifications.build()
        .sourceDomain(sourceToken)
        .transitions(transitions)
        .build();
  }

  static boolean isToToken(String token) {
    return "to".equals(token);
  }

  static boolean isThruToken(String token) {
    return "thru".equals(token);
  }

  static boolean isSuperToken(String token) {
    return "super".equals(token);
  }

  static boolean isKeyToken(String token) {
    return isToToken(token) || isThruToken(token) || isSuperToken(token);
  }

  private TraversePathParseFunctions() {}
}
