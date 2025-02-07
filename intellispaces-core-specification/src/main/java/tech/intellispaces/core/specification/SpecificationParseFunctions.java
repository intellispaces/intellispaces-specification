package tech.intellispaces.core.specification;

import tech.intellispaces.commons.base.collection.ArraysFunctions;
import tech.intellispaces.commons.base.collection.CollectionFunctions;
import tech.intellispaces.commons.base.data.Dictionaries;
import tech.intellispaces.commons.base.data.Dictionary;
import tech.intellispaces.commons.base.exception.NotImplementedExceptions;
import tech.intellispaces.commons.base.exception.UnexpectedExceptions;
import tech.intellispaces.commons.base.function.ThrowingFunction;
import tech.intellispaces.commons.base.text.StringFunctions;
import tech.intellispaces.core.specification.constraint.ConstraintSpecification;
import tech.intellispaces.core.specification.constraint.EquivalenceConstraintSpecification;
import tech.intellispaces.core.specification.constraint.EquivalenceConstraintSpecifications;
import tech.intellispaces.core.specification.exception.SpecificationException;
import tech.intellispaces.core.specification.exception.SpecificationExceptions;
import tech.intellispaces.core.specification.instance.InstanceSpecification;
import tech.intellispaces.core.specification.instance.InstanceSpecifications;
import tech.intellispaces.core.specification.reference.SpaceReference;
import tech.intellispaces.core.specification.reference.SpaceReferenceBuilder;
import tech.intellispaces.core.specification.reference.SpaceReferences;
import tech.intellispaces.core.specification.traverse.TraversePathParseFunctions;
import tech.intellispaces.core.specification.traverse.TraversePathSpecification;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SpecificationParseFunctions {

  public static Specification parseSpecification(
      Path specPath,
      Path baseDirectory,
      ThrowingFunction<InputStream, Dictionary, Exception> rawParser
  ) throws SpecificationException {
    var specs = new LinkedHashMap<String, Specification>();
    parseSpecifications(specPath, baseDirectory, rawParser, specs);
    return joinSpecification(specPath, specs.values());
  }

  static void parseSpecifications(
      Path specPath,
      Path baseDirectory,
      ThrowingFunction<InputStream, Dictionary, Exception> rawParser,
      Map<String, Specification> specs
  ) throws SpecificationException {
    if (!specs.containsKey(specPath.toString())) {
      Dictionary specDictionary = parseSpecification(specPath, rawParser);

      SpecificationVersion specVersion = parseVersion(specDictionary);
      if (!SpecificationVersions.V0p1.is(specVersion)) {
        throw NotImplementedExceptions.withCode("FT4tQA");
      }

      Specification spec = parseSpecification(specPath, specDictionary);
      specs.put(specPath.toString(), spec);

      List<Path> importSpecPaths = getImportedSpecifications(specDictionary, baseDirectory);
      CollectionFunctions.forEach(importSpecPaths,
          importSpecPath -> parseSpecifications(importSpecPath, baseDirectory, rawParser, specs));
    }
  }

  static Dictionary parseSpecification(
      Path specPath, ThrowingFunction<InputStream, Dictionary, Exception> rawParser
  ) throws SpecificationException {
    try {
      return rawParser.applyThrows(new FileInputStream(specPath.toFile()));
    } catch (Exception e){
      throw SpecificationExceptions.withCauseAndMessage(e, "Unable to parse specification");
    }
  }

  static SpecificationVersion parseVersion(Dictionary specDictionary) throws SpecificationException {
    String version = specDictionary.stringValue("intellispaces");
    return SpecificationVersions.from(version);
  }

  static List<Path> getImportedSpecifications(Dictionary specDictionary, Path baseDirectory) {
    if (!specDictionary.hasProperty("imports")) {
      return List.of();
    }
    List<String> importPathPatterns = specDictionary.stringListValue("imports");
    return getImportedSpecifications(baseDirectory, importPathPatterns);
  }

  static List<Path> getImportedSpecifications(Path projectPath, List<String> importPathPatterns) {
    var files = new ArrayList<Path>();
    CollectionFunctions.forEach(importPathPatterns, importMask -> getImportedSpecifications(
        projectPath, importMask, files)
    );
    return files;
  }

  static void getImportedSpecifications(Path projectPath, String importPathPattern, List<Path> files) {
    files.add(Paths.get(projectPath.toString(), importPathPattern));
  }

  static Specification parseSpecification(Path specPath, Dictionary specDictionary) throws SpecificationException {
    return Specifications.build(specPath)
        .ontology(parseOntology(specDictionary))
        .get();
  }

  static OntologySpecification parseOntology(Dictionary specDictionary) throws SpecificationException {
    Dictionary ontologyDictionary = specDictionary.dictionaryValue("ontology");
    return OntologySpecifications.build()
        .domains(parseDomains(ontologyDictionary))
        .get();
  }

  static List<DomainSpecification> parseDomains(Dictionary ontologyDictionary) throws SpecificationException {
    if (!ontologyDictionary.hasProperty("domains")) {
      return List.of();
    }
    List<Dictionary> domainDictionaries = ontologyDictionary.dictionaryListValue("domains");
    return CollectionFunctions.mapEach(domainDictionaries, SpecificationParseFunctions::parseDomain);
  }

  static DomainSpecification parseDomain(Dictionary domainDictionary) throws SpecificationException {
    return DomainSpecifications.build()
        .name(domainDictionary.name())
        .did(domainDictionary.stringValue("did"))
        .description(domainDictionary.stringValueNullable("description"))
        .superDomains(parseSuperDomains(domainDictionary))
        .channels(parseDomainChannels(domainDictionary))
        .get();
  }

  static List<SuperDomainSpecification> parseSuperDomains(Dictionary domainDictionary) throws SpecificationException {
    if (!domainDictionary.hasProperty("superDomains")) {
      return List.of();
    }
    List<Dictionary> dictionaries = domainDictionary.dictionaryListValue("superDomains");
    return CollectionFunctions.mapEach(dictionaries, SpecificationParseFunctions::parseSuperDomain);
  }

  static SuperDomainSpecification parseSuperDomain(Dictionary superDomainDictionary) throws SpecificationException {
    return SuperDomainSpecifications.build()
        .reference(parseDomainReference(superDomainDictionary))
        .constraints(parseContextConstraints(superDomainDictionary))
        .get();
  }

  static List<ContextChannelSpecification> parseDomainChannels(
      Dictionary domainDictionary
  ) throws SpecificationException {
    if (!domainDictionary.hasProperty("channels")) {
      return List.of();
    }
    List<Dictionary> channelDictionaries = domainDictionary.dictionaryListValue("channels");
    return CollectionFunctions.mapEach(channelDictionaries, SpecificationParseFunctions::parseDomainChannel);
  }

  static ContextChannelSpecification parseDomainChannel(
      Dictionary channelDictionary
  ) throws SpecificationException {
    return ContextChannelSpecifications.build()
        .alias(parseDictionaryAlias(channelDictionary))
        .cid(channelDictionary.stringValue("cid"))
        .name(channelDictionary.stringValueNullable("name"))
        .description(channelDictionary.stringValueNullable("description"))
        .projections(parseChannelProjections(channelDictionary))
        .targetDomain(parseDomainReference(channelDictionary, "target", "domain"))
        .targetDomainBounds(parseDomainBounds(channelDictionary, "target", "domain", "bounds"))
        .targetConstraints(parseContextConstraints(channelDictionary, "target"))
        .targetAlias(traverseToString(channelDictionary, "target", "alias"))
        .targetInstance(parseInstance(channelDictionary, "target", "instance"))
        .allowedTraverses(parseAllowedTraverses(channelDictionary))
        .get();
  }

  static List<ContextChannelSpecification> parseChannelProjections(
      Dictionary channelDictionary
  ) throws SpecificationException {
    List<Dictionary> projectionDictionaries = traverseToDictionaryList(channelDictionary, "projections");
    if (projectionDictionaries == null) {
      return List.of();
    }
    return CollectionFunctions.mapEach(projectionDictionaries, SpecificationParseFunctions::parseChannelProjection);
  }

  static ContextChannelSpecification parseChannelProjection(
      Dictionary projectionDictionary
  ) throws SpecificationException {
    return ContextChannelSpecifications.build()
        .targetAlias(parseDictionaryAlias(projectionDictionary))
        .targetDomain(parseDomainReference(projectionDictionary, "target", "domain"))
        .targetConstraints(parseContextConstraints(projectionDictionary, "target"))
        .get();
  }

  static List<ConstraintSpecification> parseContextConstraints(
      Dictionary dictionary, String... propertyPath
  ) throws SpecificationException {
    List<Dictionary> constraintDictionaries = traverseToDictionaryList(dictionary, propertyPath, "constraints");
    if (constraintDictionaries == null) {
      return List.of();
    }
    return CollectionFunctions.mapEach(constraintDictionaries, SpecificationParseFunctions::readContextConstraint);
  }

  static ConstraintSpecification readContextConstraint(Dictionary constrainDictionary) throws SpecificationException {
    Dictionary equivalenceDictionary = traverseToDictionary(constrainDictionary, "equivalence");
    if (equivalenceDictionary != null) {
      return readEquivalenceContextConstraint(equivalenceDictionary);
    } else {
      throw NotImplementedExceptions.withCode("fBAcjQ");
    }
  }

  @SuppressWarnings("unchecked")
  static EquivalenceConstraintSpecification readEquivalenceContextConstraint(
      Dictionary constrainDictionary
  ) throws SpecificationException {
    var paths = (List<String>) traverseToList(constrainDictionary, "paths");
    if (paths == null) {
      throw NotImplementedExceptions.withCode("Pa6fhA");
    }
    List<TraversePathSpecification> pathSpecs = CollectionFunctions.mapEach(paths, TraversePathParseFunctions::parse);
    return EquivalenceConstraintSpecifications.get(pathSpecs);
  }

  static InstanceSpecification parseInstance(Dictionary dictionary, String... propertyPath) {
    if (!existProperty(dictionary, propertyPath)) {
      return null;
    }

    String stringValue = traverseToString(dictionary, propertyPath);
    if (stringValue != null) {
      return InstanceSpecifications.get(stringValue);
    }
    throw NotImplementedExceptions.withCode("RG/Gb1Pl");
  }

  @SuppressWarnings("unchecked")
  static DomainBoundSpecification parseDomainBounds(Dictionary dictionary, String... propertyPath) {
    if (!existProperty(dictionary, propertyPath)) {
      return null;
    }
    Dictionary boundsDictionary = traverseToDictionary(dictionary, propertyPath);

    var superDomains = (List<String>) traverseToList(boundsDictionary, "super");
    if (superDomains != null) {
      return DomainBoundSpecifications.get(superDomains.stream()
          .map(string -> SpaceReferences.build().name(string).build())
          .toList());
    }

    return null;
  }

  static SpaceReference parseDomainReference(Dictionary dictionary, String... propertyPath) {
    if (!existProperty(dictionary, propertyPath)) {
      return null;
    }

    SpaceReferenceBuilder builder = SpaceReferences.build();
    builder.name(parseDomainName(dictionary, propertyPath));
    return builder.build();
  }

  static String parseDomainName(Dictionary dictionary, String... propertyPath) {
    if (isStringValue(dictionary, propertyPath)) {
      return traverseToString(dictionary, propertyPath);
    } else {
      Dictionary targetDict = traverseToDictionary(dictionary, propertyPath);
      if (targetDict != null) {
        if (!targetDict.propertyNames().isEmpty()) {
          String name = traverseToString(targetDict, "name");
          if (name != null) {
            return name;
          }

          String firstProperty = targetDict.propertyNames().get(0);
          if (!targetDict.hasValue(firstProperty)) {
            return firstProperty;
          }
        } else {
          return targetDict.name();
        }
      }
      return null;
    }
  }

  static List<String> parseAllowedTraverses(Dictionary channelDictionary) {
    return channelDictionary.stringListValueNullable("allowedTraverses");
  }

  static Specification joinSpecification(Path specPath, Collection<Specification> specs) {
    return Specifications.build(specPath)
        .ontology(OntologySpecifications.build()
            .domains(specs.stream()
                .map(Specification::ontology)
                .map(OntologySpecification::domains)
                .flatMap(List::stream)
                .toList())
            .get())
        .get();
  }

  static String parseDictionaryAlias(Dictionary dictionary) throws SpecificationException {
    String alias = dictionary.stringValueNullable("alias");
    if (alias != null) {
      return alias;
    }
    List<String> properties = dictionary.propertyNames();
    if (properties.isEmpty()) {
      throw SpecificationExceptions.withMessage("Invalid description: {0}", dictionary.path());
    }
    String firstProperty = properties.get(0);
    if (dictionary.hasValue(firstProperty)) {
      throw SpecificationExceptions.withMessage("Invalid alias: {0}", dictionary.path());
    }
    return firstProperty;
  }

  static boolean existProperty(Dictionary dictionary, String... propertyPath) {
    Object value = traverse(dictionary, propertyPath);
    return (value != null);
  }

  static boolean isStringValue(Dictionary dictionary, String... propertyPath) {
    Object value = traverse(dictionary, propertyPath);
    if (value == null) {
      return false;
    }
    return (value instanceof String);
  }

  static String traverseToString(Dictionary dictionary, String... propertyPath) {
    Object value = traverse(dictionary, propertyPath);
    if (value == null) {
      return null;
    } else if (value instanceof String string) {
      return string;
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' is not string",
        propertyPathString(dictionary, propertyPath));
  }

  static Dictionary traverseToDictionary(Dictionary dictionary, String... propertyPath) {
    Object value = traverse(dictionary, propertyPath);
    if (value == null) {
      return null;
    } else if (value instanceof Dictionary dict) {
      return dict;
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' is not dictionary",
        propertyPathString(dictionary, propertyPath));
  }

  static List<?> traverseToList(Dictionary dictionary, String... propertyPath) {
    Object value = traverse(dictionary, propertyPath);
    if (value == null) {
      return null;
    } else if (value instanceof List<?> list) {
      return list;
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' is not list", propertyPathString(dictionary, propertyPath));
  }

  static List<?> traverseToList(Dictionary dictionary, String[] initialPath, String... propertyPath) {
    return traverseToList(dictionary, ArraysFunctions.join(initialPath, propertyPath));
  }

  @SuppressWarnings("unchecked")
  static List<Dictionary> traverseToDictionaryList(Dictionary dictionary, String... propertyPath) {
    List<?> list = traverseToList(dictionary, propertyPath);
    if (list == null) {
      return null;
    }

    Object element = list.get(0);
    if (element instanceof Dictionary) {
      return (List<Dictionary>) list;
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' is not dictionary list",
        propertyPathString(dictionary, propertyPath));
  }

  static List<Dictionary> traverseToDictionaryList(
      Dictionary dictionary, String[] initialPath, String... propertyPath
  ) {
    return traverseToDictionaryList(dictionary, ArraysFunctions.join(initialPath, propertyPath));
  }

  static Object traverse(Dictionary dictionary, String... propertyPath) {
    if (ArraysFunctions.isNullOrEmpty(propertyPath)) {
      return dictionary;
    }

    List<String> splitPropertyPath = Arrays.stream(propertyPath)
        .filter(Objects::nonNull)
        .flatMap(p -> StringFunctions.splitAndTrim(p, ".").stream())
        .toList();

    Dictionary curDictionary = dictionary;
    String joinPropertyName = "";
    for (int ind = 0; ind < splitPropertyPath.size(); ind++) {
      String part = splitPropertyPath.get(ind);
      joinPropertyName = joinPropertyName + (joinPropertyName.isEmpty() ? "" : ".") + part;
      if (curDictionary.hasProperty(joinPropertyName)) {
        Object value = curDictionary.valueNullable(joinPropertyName);
        if (ind == splitPropertyPath.size() - 1) {
          return value;
        }
        if (value instanceof Dictionary) {
          curDictionary = (Dictionary) value;
          joinPropertyName = "";
        } else if (value instanceof List<?>) {
          throw NotImplementedExceptions.withCode("vnyVN");
        } else {
          return null;
        }
      }
    }

    for (String propertyName : curDictionary.propertyNames()) {
      if (propertyName.startsWith(joinPropertyName + ".")) {
        String nextPropertyName = propertyName.substring(joinPropertyName.length() + 1);
        return Dictionaries.get(
            Arrays.stream(propertyPath).toList(),
            Map.of(nextPropertyName, curDictionary.valueNullable(propertyName))
        );
      }
    }
    return null;
  }

  static String propertyPathString(String... path) {
    if (ArraysFunctions.isNullOrEmpty(path)) {
      return "";
    }
    return String.join("\\", path);
  }

  static String propertyPathString(Dictionary dictionary, String... path) {
    int dictionaryPathLength = dictionary.path() != null ? dictionary.path().size() : 0;
    int pathLength = path != null ? path.length : 0;

    String[] fullPath = new String[dictionaryPathLength + pathLength];
    int ind = 0;
    for (int i = 0; i < dictionaryPathLength; i++) {
      fullPath[ind++] = dictionary.path().get(i);
    }
    for (int i = 0; i < pathLength; i++) {
      fullPath[ind++] = path[i];
    }
    return propertyPathString(fullPath);
  }

  private SpecificationParseFunctions() {}
}
