package tech.intellispaces.core.specification.space;

import tech.intellispaces.commons.base.collection.ArraysFunctions;
import tech.intellispaces.commons.base.collection.CollectionFunctions;
import tech.intellispaces.commons.base.data.Dictionaries;
import tech.intellispaces.commons.base.data.Dictionary;
import tech.intellispaces.commons.base.exception.NotImplementedExceptions;
import tech.intellispaces.commons.base.exception.UnexpectedExceptions;
import tech.intellispaces.commons.base.function.ThrowingFunction;
import tech.intellispaces.commons.base.text.StringFunctions;
import tech.intellispaces.core.specification.space.constraint.ConstraintSpecification;
import tech.intellispaces.core.specification.space.constraint.EquivalenceConstraintSpecification;
import tech.intellispaces.core.specification.space.constraint.EquivalenceConstraintSpecifications;
import tech.intellispaces.core.specification.space.exception.SpecificationException;
import tech.intellispaces.core.specification.space.exception.SpecificationExceptions;
import tech.intellispaces.core.specification.space.instance.CustomInstanceSpecifications;
import tech.intellispaces.core.specification.space.instance.InstanceSpecification;
import tech.intellispaces.core.specification.space.instance.InstanceSpecifications;
import tech.intellispaces.core.specification.space.reference.SpaceReference;
import tech.intellispaces.core.specification.space.reference.SpaceReferenceBuilder;
import tech.intellispaces.core.specification.space.reference.SpaceReferences;
import tech.intellispaces.core.specification.space.traverse.TraversePathParseFunctions;
import tech.intellispaces.core.specification.space.traverse.TraversePathSpecification;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SpecificationParseFunctions {

  public static Specification parseSpecification(
      Path specPath,
      ThrowingFunction<InputStream, Dictionary, Exception> rawParser
  ) throws SpecificationException {
    var specs = new LinkedHashMap<String, Specification>();
    parseSpecifications(specPath, rawParser, specs);
    return joinSpecification(specPath, specs.values());
  }

  static void parseSpecifications(
      Path specPath,
      ThrowingFunction<InputStream, Dictionary, Exception> rawParser,
      Map<String, Specification> specs
  ) throws SpecificationException {
    if (!specs.containsKey(specPath.toString())) {
      Dictionary specDictionary = readSpecification(specPath, rawParser);

      SpecificationVersion specVersion = parseVersion(specDictionary);
      if (!SpecificationVersions.V0p1.is(specVersion)) {
        throw NotImplementedExceptions.withCode("FT4tQA");
      }

      Specification spec = parseSpecification(specPath, specDictionary);
      specs.put(specPath.toString(), spec);

      List<Path> importSpecPaths = getImportedSpecifications(specPath, specDictionary);
      CollectionFunctions.forEach(importSpecPaths,
          importSpecPath -> parseSpecifications(importSpecPath, rawParser, specs));
    }
  }

  static Dictionary readSpecification(
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

  static List<Path> getImportedSpecifications(Path baseSpecPath, Dictionary specDictionary) {
    if (!specDictionary.hasProperty("imports")) {
      return List.of();
    }
    List<String> importSpecNames = specDictionary.stringListValue("imports");
    return getImportedSpecifications(baseSpecPath, importSpecNames);
  }

  static List<Path> getImportedSpecifications(Path baseSpecPath, List<String> importSpecNames) {
    var files = new ArrayList<Path>();
    CollectionFunctions.forEach(importSpecNames, importSpecName -> getImportedSpecifications(
        baseSpecPath, importSpecName, files)
    );
    return files;
  }

  static void getImportedSpecifications(Path baseSpecPath, String importSpecName, List<Path> files) {
    files.add(Paths.get(baseSpecPath.getParent().toString(), importSpecName));
  }

  static Specification parseSpecification(Path specPath, Dictionary specDictionary) throws SpecificationException {
    return Specifications.build(specPath)
        .ontology(parseOntology(specDictionary))
        .build();
  }

  static OntologySpecification parseOntology(Dictionary specDictionary) throws SpecificationException {
    Dictionary ontologyDictionary = specDictionary.dictionaryValue("ontology");
    return OntologySpecifications.build()
        .domains(parseDomains(ontologyDictionary))
        .channels(parseChannels(ontologyDictionary))
        .build();
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
        .build();
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
        .build();
  }

  static List<ChannelSpecification> parseDomainChannels(
      Dictionary domainDictionary
  ) throws SpecificationException {
    if (!domainDictionary.hasProperty("channels")) {
      return List.of();
    }
    List<Dictionary> channelDictionaries = domainDictionary.dictionaryListValue("channels");
    return CollectionFunctions.mapEach(channelDictionaries, SpecificationParseFunctions::parseDomainChannel);
  }

  static List<ChannelSpecification> parseChannels(Dictionary ontologyDictionary) throws SpecificationException {
    if (!ontologyDictionary.hasProperty("channels")) {
      return List.of();
    }
    List<Dictionary> channelDictionaries = ontologyDictionary.dictionaryListValue("channels");
    return CollectionFunctions.mapEach(channelDictionaries, SpecificationParseFunctions::parseChannel);
  }

  static ChannelSpecification parseDomainChannel(Dictionary channelDictionary) throws SpecificationException {
    return parseChannel(
        channelDictionary,
        traverseToString(channelDictionary, "name"),
        parseDictionaryAlias(channelDictionary)
    );
  }

  static ChannelSpecification parseChannel(Dictionary channelDictionary) throws SpecificationException {
    return parseChannel(
        channelDictionary,
        channelDictionary.name(),
        null
    );
  }

  static ChannelSpecification parseChannelQualifier(Dictionary qualifierDictionary) throws SpecificationException {
    return parseChannel(
        qualifierDictionary,
        traverseToString(qualifierDictionary, "name"),
        parseDictionaryAlias(qualifierDictionary)
    );
  }

  static ChannelSpecification parseChannel(
      Dictionary channelDictionary, String name, String alias
  ) throws SpecificationException {
    return ChannelSpecifications.build()
        .cid(traverseToString(channelDictionary, "cid"))
        .name(name)
        .alias(alias)
        .description(channelDictionary.stringValueNullable("description"))
        .source(ChannelSideSpecifications.build()
            .alias(traverseToString(channelDictionary, "source", "alias"))
            .domain(parseDomainReference(channelDictionary, "source", "domain"))
            .domainBounds(parseDomainBounds(channelDictionary, "source", "domain", "bounds"))
            .constraints(parseContextConstraints(channelDictionary, "source"))
            .instance(parseInstance(channelDictionary, "source", "instance"))
            .immobilityType(parseImmobilityType(channelDictionary, "source"))
            .build()
        )
        .target(ChannelSideSpecifications.build()
            .alias(traverseToString(channelDictionary, "target", "alias"))
            .domain(parseDomainReference(channelDictionary, "target", "domain"))
            .domainBounds(parseDomainBounds(channelDictionary, "target", "domain", "bounds"))
            .constraints(parseContextConstraints(channelDictionary, "target"))
            .instance(parseInstance(channelDictionary, "target", "instance"))
            .immobilityType(parseImmobilityType(channelDictionary, "target"))
            .build()
        )
        .qualifiers(parseChannelQualifiers(channelDictionary))
        .allowedTraverses(parseAllowedTraverses(channelDictionary))
        .build();
  }

  static List<ChannelSpecification> parseChannelQualifiers(
      Dictionary channelDictionary
  ) throws SpecificationException {
    List<Dictionary> qualifierDictionaries = traverseToDictionaryList(channelDictionary, "qualifiers");
    if (qualifierDictionaries == null) {
      return List.of();
    }
    return CollectionFunctions.mapEach(qualifierDictionaries, SpecificationParseFunctions::parseChannelQualifier);
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
    } else if (constrainDictionary.hasProperty("equivalence")) {
      return readEquivalenceContextConstraint(constrainDictionary);
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

    InstanceSpecification instance = null;
    if (constrainDictionary.hasProperty("instance")) {
      instance = parseInstance(constrainDictionary, "instance");
    }

    return EquivalenceConstraintSpecifications.get(pathSpecs, instance);
  }

  static InstanceSpecification parseInstance(
      Dictionary dictionary, String... propertyPath
  ) throws SpecificationException {
    if (!existProperty(dictionary, propertyPath)) {
      return null;
    }

    Object value = traverse(dictionary, propertyPath);
    if (value instanceof String stringValue) {
      return InstanceSpecifications.get(stringValue);
    } else if (value instanceof Dictionary dictionaryValue){
      return parseCustomInstance(dictionaryValue);
    }
    throw NotImplementedExceptions.withCode("RG/Gb1Pl");
  }

  static InstanceSpecification parseCustomInstance(
      Dictionary instasnceDictionary
  ) throws SpecificationException {
    SpaceReference domain = SpaceReferences.build().name(instasnceDictionary.stringValue("domain")).build();
    List<ConstraintSpecification> constraints = parseContextConstraints(instasnceDictionary);

    Map<String, InstanceSpecification> projections = new HashMap<>();
    for (String propertyName : instasnceDictionary.propertyNames()) {
      if (!"domain".equals(propertyName) && !"constraints".equals(propertyName)) {
        InstanceSpecification targetInstance = parseInstance(instasnceDictionary,propertyName);
        projections.put(propertyName, targetInstance);
      }
    }
    return InstanceSpecifications.get(CustomInstanceSpecifications.get(domain, projections, constraints));
  }

  static ImmobilityType parseImmobilityType(Dictionary dictionary, String... propertyPath) {
    String alias = traverseToString(dictionary, propertyPath, "immobilityType");
    if (alias != null) {
      return ImmobilityTypes.fromAlias(alias);
    }
    return ImmobilityTypes.Undefined;
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

  static List<AllowedTraverseType> parseAllowedTraverses(Dictionary channelDictionary) {
    List<String> values = channelDictionary.stringListValueNullable("allowedTraverses");
    if (values == null) {
      return List.of(AllowedTraverseTypes.Mapping);
    }
    return values.stream()
        .map(AllowedTraverseTypes::valueOf)
        .map(t -> (AllowedTraverseType) t)
        .toList();
  }

  static Specification joinSpecification(Path specPath, Collection<Specification> specs) {
    return Specifications.build(specPath)
        .ontology(OntologySpecifications.build()
            .domains(specs.stream()
                .map(Specification::ontology)
                .map(OntologySpecification::domains)
                .flatMap(List::stream)
                .toList())
            .channels(specs.stream()
                .map(Specification::ontology)
                .map(OntologySpecification::channels)
                .flatMap(List::stream)
                .toList())
            .build())
        .build();
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
    if (!dictionary.hasValue(firstProperty)) {
      return firstProperty;
    }
    return null;
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

  static String traverseToString(
      Dictionary dictionary, String[] initialPath, String... propertyPath
  ) {
    return traverseToString(dictionary, ArraysFunctions.join(initialPath, propertyPath));
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
