package angulate.uirouter

import scala.scalajs.js
import scala.scalajs.js.RegExp

// Some code derived from scalajs-angular
// Copyright greencatsoft.com under an Apache2 license

// Some code derived from borisyankov/DefinitelyTyped
// MIT license

/**
 * \$urlRouterProvider has the responsibility of watching \$location.
 * When \$location changes it runs through a list of rules one by one until a match is found.
 * \$urlRouterProvider is used behind the scenes anytime you specify a url in a state configuration.
 * All urls are compiled into a UrlMatcher object.
 */
trait UrlRouterProvider extends js.Object {
  /**
   * Disables (or enables) deferring location change interception.
   *
   * If you wish to customize the behavior of syncing the URL (for example, if you wish to defer a transition but maintain
   * the current URL), call this method at configuration time. Then, at run time, call \$urlRouter.listen() after you have
   * configured your own \$locationChangeSuccess event handler.
   *
   * @param defer Indicates whether to defer location change interception. Passing no parameter is equivalent to true.
   */
  def deferIntercept(defer: Boolean): UrlRouterProvider = js.native

  /**
   * Registers a handler for a given url matching.
   * If the handler is a function, it is injectable. It gets invoked if \$location matches. You have the option of inject the match object as \$match.
   * The handler can return
   * - falsy to indicate that the rule didn't match after all, then \$urlRouter will continue trying to find another one that matches.
   * - string which is treated as a redirect and passed to \$location.url()
   * - void or any truthy value tells \$urlRouter that the url was handled.
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
   * If the handler is a function, it is injectable. It gets invoked if \$location matches. You have the option of inject the match object as \$match.
   * The handler can return
   * - falsy to indicate that the rule didn't match after all, then \$urlRouter will continue trying to find another one that matches.
   * - string which is treated as a redirect and passed to \$location.url()
   * - void or any truthy value tells \$urlRouter that the url was handled.
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
   * If the handler is a function, it is injectable. It gets invoked if \$location matches. You have the option of inject the match object as \$match.
   * The handler can return
   * - falsy to indicate that the rule didn't match after all, then \$urlRouter will continue trying to find another one that matches.
   * - string which is treated as a redirect and passed to \$location.url()
   * - void or any truthy value tells \$urlRouter that the url was handled.
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
   *                two params: \$injector and \$location services, and must return a url string.
   */
  def otherwise(handler: js.Function): UrlRouterProvider = js.native
  /**
   * Defines a path that is used when an invalid route is requested.
   * @param path The url path you want to redirect to
   */
  def otherwise(path: String): UrlRouterProvider = js.native

  /**
   * Defines rules that are used by \$urlRouterProvider to find matches for specific URLs.
   * @param handler Handler function that takes \$injector and \$location services as arguments.
   *                You can use them to return a valid path as a string.
   */
  def rule(handler: js.Function): UrlRouterProvider = js.native

}

/**
 * \$urlRouter service
 */
trait UrlRouterService extends js.Object{
  /**
   * Triggers an update; the same update that happens when the address bar
   * url changes, aka \$locationChangeSuccess.
   *
   * This method is useful when you need to use preventDefault() on the
   * \$locationChangeSuccess event, perform some custom logic (route protection,
   * auth, config, redirection, etc) and then finally proceed with the transition
   * by calling \$urlRouter.sync().
   */
  def sync(): Unit = js.native

  /**
   * A URL generation method that returns the compiled URL for a given
   * `UrlMatcher`, populated with the provided parameters.
   *
   * @param urlMatcher The `UrlMatcher` object which is used as the template of the URL to generate.
   * @param params An object of parameter values to fill the matcher's required parameters.
   * @param options Options for href()
   * @return Returns the fully compiled URL, or `null` if `params` fail validation against `urlMatcher`
   */
  def href(urlMatcher: UrlMatcher, params: UrlMatcherConfig, options: HrefOptions): String = js.native
}

/**
 * Options for the href methods
 *
 * @param lossy Default: true - If true, and if there is no url associated with the state provided in the
  *    first parameter, then the constructed href url will be built from the first navigable ancestor (aka
  *    ancestor with a valid url).
 * @param inherit Default: true, If `true` will inherit url parameters from current url.
 * @param relative Default: \$state.\$current, When transitioning with relative path (e.g '{{{^}}}'),
  *    defines which state to be relative from.
 * @param absolute If true will generate an absolute url, e.g. "http://www.example.com/fullurl".
 */
class HrefOptions(lossy: Boolean = true, inherit: Boolean = true, relative: State = js.native, absolute: Boolean = false) extends js.Object {}
