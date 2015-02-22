package angulate.uirouter

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

// Some code derived from scalajs-angular
// Copyright greencatsoft.com under an Apache2 license

// Some code derived from borisyankov/DefinitelyTyped
// MIT license

/**
 * Matches URLs against patterns and extracts named parameters from the path or the search part of the URL.
 * A URL pattern consists of a path pattern, optionally followed by '?' and a list of search parameters.
 * Multiple search parameter names are separated by '&'. Search parameters do not influence whether or not a URL is matched,
 * but their values are passed through into the matched parameters returned by exec.
 *
 * Path parameter placeholders can be specified using simple colon/catch-all syntax or curly brace syntax, which optionally
 * allows a regular expression for the parameter to be specified:
 *
 * - ':' name - colon placeholder
 * - '*' name - catch-all placeholder
 * - '{' name '}' - curly placeholder
 * - '{' name ':' regexp|type '}' - curly placeholder with regexp or type name. Should the regexp itself contain curly braces,
 *                                  they must be in matched pairs or escaped with a backslash.
 *
 * Parameter names may contain only word characters (latin letters, digits, and underscore) and must be unique within the pattern
 * (across both path and search parameters). For colon placeholders or curly placeholders without an explicit regexp, a path
 * parameter matches any number of characters other than '/'. For catch-all placeholders the path parameter matches any number of characters.
 */
trait UrlMatcher extends js.Object {

  /**
   * A static prefix of this pattern. The matcher guarantees that any URL matching this matcher (i.e. any string for which
   * exec() returns non-null) will start with this prefix.
   */
  val prefix: String = js.native

  /** The pattern that was passed into the constructor */
  val source: String = js.native

  /** The path portion of the source property */
  val sourcePath: String = js.native

  /** The search portion of the source property */
  val sourceSearch: String = js.native

  /** The constructed regex that will be used to match against the url when it is time to determine which url will match. */
  var regex: String = js.native

  def concat(pattern: String): UrlMatcher = js.native
  def exec(path: String, searchParams: js.Any): js.Any = js.native
  def parameters(): js.Array[String] = js.native
  def format(values: js.Any): String = js.native
}

/**
 * Configuration for UrlMatcher
 * @param caseInsensitive `true` if URL matching should be case insensitive, otherwise `false`, the default value (for backward compatibility) is `false`.
 * @param strict `false` if matching against a URL with a trailing slash should be treated as equivalent to a URL without a trailing slash, the default value is `true`.
 */
class UrlMatcherConfig(caseInsensitive: Boolean = false, strict: Boolean = true) extends js.Object

object UrlMatcher {

  def apply(pattern: String, config: UrlMatcherConfig = null, parentMatcher: UrlMatcher = null): UrlMatcher = {
    js.Dynamic.global.applyDynamic("UrlMatcher")(pattern, config, parentMatcher).asInstanceOf[UrlMatcher]
  }
}

/**
 * \$urlMatcherFactory: Factory for UrlMatcher instances.
 *
 * The factory is also available to providers under the name \$urlMatcherFactoryProvider.
 */
trait UrlMatcherFactory extends js.Object {

  /**
   * Defines whether URL matching should be case sensitive (the default behavior), or not.
   *
   * @param value false to match URL in a case sensitive manner; otherwise true
   * @return the current value of caseInsensitive
   */
  def caseInsensitive(value: Boolean): Boolean = js.native

  /**
   * Creates a UrlMatcher for the specified pattern.
   *
   * @param pattern The URL pattern
   * @param config
   */
  def compile(pattern: String, config: UrlMatcherConfig): UrlMatcher = js.native

  /**
   * Sets the default behavior when generating or matching URLs with default parameter values.
   *
   * @param value the default parameter URL squashing behavior.
   *              - nosquash: When generating an href with a default parameter value, do not squash the parameter value from the URL
   *              - slash: When generating an href with a default parameter value, squash (remove) the parameter value, and, if the parameter is surrounded by slashes, squash (remove) one slash from the URL any other string, e.g. "~": When generating an href with a default parameter value, squash (remove) the parameter value from the URL and replace it with this string.
   */
  def defaultSquashPolicy(value: String): Unit = js.native

  /**
   * Returns true if the specified object is a UrlMatcher, or false otherwise.
   *
   * @param any The object to perform the type check against.
   * @return Returns true if the object matches the UrlMatcher interface, by implementing all the same methods.
   */
  def isMatcher(any: js.Any): Boolean = js.native

  /**
   * Defines whether URLs should match trailing slashes, or not (the default behavior).
   *
   * @param value false to match trailing slashes in URLs, otherwise true.
   * @return the current value of strictMode
   */
  def strictMode(value: Boolean = false): Boolean = js.native

  /**
   * Registers a custom Type object that can be used to generate URLs with typed parameters.
   *
   * @param name The type name.
   * @param definition The type definition
   * @param definitionFn A function that is injected before the app runtime starts. The result of this function is merged into
   *                     the existing definition. See Type for information on the values accepted.
   * @return Returns \$urlMatcherFactoryProvider.
   */
  @JSName("type")
  def defineType(name: String, definition: Type, definitionFn: js.Function = null): UrlMatcherFactory = js.native

}

/**
 * Implements an interface to define custom parameter types that can be decoded from and encoded to
 * string parameters matched in a URL. Used by UrlMatcher
 * objects when matching or formatting URLs, or comparing or validating parameter values.
 *
 * See  UrlMatcherFactory.defineType for more information on registering custom types.
 */
trait Type extends js.Object {

  /**
   * Detects whether a value is of a particular type. Accepts a native (decoded) value
   * and determines whether it matches the current `Type` object.
   *
   * @param value  The value to check.
   * @param  key  Optional. If the type check is happening in the context of a specific
   *        `UrlMatcher` object, this is the name of the parameter in which `value` is stored.
   *        Can be used for meta-programming of `Type` objects.
   * @return Returns `true` if the value matches the type, otherwise `false`.
   */
  def is(value: js.Any, key: String = null): Boolean = js.native

  /**
   * Encodes a custom/native type value to a string that can be embedded in a URL. Note that the
   * return value does *not* need to be URL-safe (i.e. passed through `encodeURIComponent()`), it
   * only needs to be a representation of `val` that has been coerced to a string.
   *
   * @param value  The value to encode.
   * @param key  The name of the parameter in which `val` is stored. Can be used for
   *        meta-programming of `Type` objects.
   * @return Returns a string representation of `val` that can be encoded in a URL.
   */
  def encode(value: js.Any, key: String): String = js.native

  /**
   * Converts a parameter value (from URL string or transition param) to a custom/native value.
   *
   * @param value The URL parameter value to decode.
   * @param key  The name of the parameter in which `val` is stored. Can be used for
   *        meta-programming of `Type` objects.
   * @return Returns a custom representation of the URL parameter value.
   */
  def decode(value: js.Any, key: String): js.Any = js.native

  /**
   * Determines whether two decoded values are equivalent.
   *
   * @param a  A value to compare against.
   * @param b  A value to compare against.
   * @return Returns `true` if the values are equivalent/equal, otherwise `false`.
   */
  def equals(a: js.Any, b: js.Any): Boolean = js.native
}
