package biz.enef.angular.ext

import scala.scalajs.js
import scala.scalajs.js.RegExp

// Some code derived from scalajs-angular
// Copyright greencatsoft.com under an Apache2 license

// Some code derived from borisyankov/DefinitelyTyped
// MIT license

/**
 * $urlRouterProvider has the responsibility of watching $location.
 * When $location changes it runs through a list of rules one by one until a match is found.
 * $urlRouterProvider is used behind the scenes anytime you specify a url in a state configuration.
 * All urls are compiled into a UrlMatcher object.
 */
trait UrlRouterProvider extends js.Object {
  /**
   * Disables (or enables) deferring location change interception.
   *
   * If you wish to customize the behavior of syncing the URL (for example, if you wish to defer a transition but maintain
   * the current URL), call this method at configuration time. Then, at run time, call $urlRouter.listen() after you have
   * configured your own $locationChangeSuccess event handler.
   *
   * @param defer Indicates whether to defer location change interception. Passing no parameter is equivalent to true.
   */
  def deferIntercept(defer: Boolean): UrlRouterProvider = js.native

  /**
   * Registers a handler for a given url matching.
   * If the handler is a function, it is injectable. It gets invoked if $location matches. You have the option of inject the match object as $match.
   * The handler can return
   * - falsy to indicate that the rule didn't match after all, then $urlRouter will continue trying to find another one that matches.
   * - string which is treated as a redirect and passed to $location.url()
   * - void or any truthy value tells $urlRouter that the url was handled.
   * @param whenPath The incoming path that you want to redirect.
   * @param handler The handler function
   */
  def when(whenPath: RegExp, handler: js.Function): UrlRouterProvider = js.native
  /**
   * Registers a handler for a given url matching.
   * @param whenPath The incoming path that you want to redirect.
   * @param toPath a string treated as a redirect, and interpolated according to the syntax of match (i.e. like String.replace() for RegExp, or like a UrlMatcher pattern otherwise)
   */
  def when(whenPath: RegExp, toPath: String): UrlRouterProvider = js.native
  /**
   * Registers a handler for a given url matching.
   * If the handler is a function, it is injectable. It gets invoked if $location matches. You have the option of inject the match object as $match.
   * The handler can return
   * - falsy to indicate that the rule didn't match after all, then $urlRouter will continue trying to find another one that matches.
   * - string which is treated as a redirect and passed to $location.url()
   * - void or any truthy value tells $urlRouter that the url was handled.
   * @param whenPath The incoming path that you want to redirect.
   * @param handler The handler function
   */
  def when(whenPath: UrlMatcher, handler: js.Function): UrlRouterProvider = js.native
  /**
   * Registers a handler for a given url matching.
   * @param whenPath The incoming path that you want to redirect.
   * @param toPath a string treated as a redirect, and interpolated according to the syntax of match (i.e. like String.replace() for RegExp, or like a UrlMatcher pattern otherwise)
   */
  def when(whenPath: UrlMatcher, toPath: String): UrlRouterProvider = js.native
  /**
   * Registers a handler for a given url matching.
   * If the handler is a function, it is injectable. It gets invoked if $location matches. You have the option of inject the match object as $match.
   * The handler can return
   * - falsy to indicate that the rule didn't match after all, then $urlRouter will continue trying to find another one that matches.
   * - string which is treated as a redirect and passed to $location.url()
   * - void or any truthy value tells $urlRouter that the url was handled.
   * @param whenPath The incoming path that you want to redirect.
   * @param handler The handler function
   */
  def when(whenPath: String, handler: js.Function): UrlRouterProvider = js.native
  /**
   * Registers a handler for a given url matching.
   * @param whenPath The incoming path that you want to redirect.
   * @param toPath a string treated as a redirect, and interpolated according to the syntax of match (i.e. like String.replace() for RegExp, or like a UrlMatcher pattern otherwise)
   */
  def when(whenPath: String, toPath: String): UrlRouterProvider = js.native
  /**
   * Defines a path that is used when an invalid route is requested.
   * @param handler The function rule that returns the url path. The function is passed
   *                two params: $injector and $location services, and must return a url string.
   */
  def otherwise(handler: js.Function): UrlRouterProvider = js.native
  /**
   * Defines a path that is used when an invalid route is requested.
   * @param path The url path you want to redirect to
   */
  def otherwise(path: String): UrlRouterProvider = js.native

  /**
   * Defines rules that are used by $urlRouterProvider to find matches for specific URLs.
   * @param handler Handler function that takes $injector and $location services as arguments.
   *                You can use them to return a valid path as a string.
   */
  def rule(handler: js.Function): UrlRouterProvider = js.native

}

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

/*

    interface IUrlMatcherFactory {
        compile(pattern: string): IUrlMatcher;
        isMatcher(o: any): boolean;
    }

    interface IHrefOptions {
        lossy?: boolean;
        inherit?: boolean;
        relative?: IState;
        absolute?: boolean;
    }

interface IUrlRouterService {
        /*
         * Triggers an update; the same update that happens when the address bar
         * url changes, aka $locationChangeSuccess.
         *
         * This method is useful when you need to use preventDefault() on the
         * $locationChangeSuccess event, perform some custom logic (route protection,
         * auth, config, redirection, etc) and then finally proceed with the transition
         * by calling $urlRouter.sync().
         *
         */
        sync(): void;
    }
 */