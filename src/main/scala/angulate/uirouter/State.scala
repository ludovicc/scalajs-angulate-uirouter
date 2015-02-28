package angulate.uirouter

import biz.enef.angulate.AnnotatedFunction
import biz.enef.angulate.core.{ProvidedService, QPromise}

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSName, JSBracketAccess}

// Some code derived from scalajs-angular
// Copyright greencatsoft.com under an Apache2 license

// Some code derived from borisyankov/DefinitelyTyped
// MIT license

/**
 * The \$stateParams service is an object that will have one key per url parameter.
 * The \$stateParams is a perfect way to provide your controllers or other services with
 * the individual parts of the navigated url.
 *
 * If you had a url on your state of:
 *
 * url: '/users/:id/details/{type}/{repeat:[0-9]+}?from&to'
 *
 * Then you navigated your browser to:
 *
 * '/users/123/details//0'
 *
 * Your \$stateParams object would be
 *
 * { id:'123', type:"", repeat:'0' }
 *
 * Then you navigated your browser to:
 *
 * '/users/123/details/default/0?from=there&to=here'
 *
 * Your \$stateParams object would be
 *
 * { id:'123', type:'default', repeat:'0', from:'there', to:'here' }
 */
trait StateParams extends js.Object {
  /**
   * Get the parameter value from its name.<br/>
   * If you have a \$stateParams object { id:'123', type:'default', repeat:'0', from:'there', to:'here' },
   * then to get the value of the "type" key, you should call \$stateParams("type").
   */
  @JSBracketAccess
  def apply(key: String): js.Any = js.native
  @JSBracketAccess
  def update(key: String, v: js.Any): Unit = js.native
}

/**
 * The \$stateProvider works similar to Angular's v1 router, but it focuses purely on state.
 *
 * A state corresponds to a "place" in the application in terms of the overall UI and navigation.
 * A state describes (via the controller / template / view properties) what the UI looks like and does at that place.
 * States often have things in common, and the primary way of factoring out these commonalities in this model is via the state hierarchy, i.e. parent/child states aka nested states.
 *
 * The `\$stateProvider` provides interfaces to declare these states for your app.
 */
trait StateProvider extends js.Object {

  /**
   * Registers a state configuration under a given state name.
   *
   * @param name A unique state name, e.g. "home", "about", "contacts".
   * To create a parent/child state use a dot, e.g. "about.sales", "home.newest".
   * @param config State configuration object.
   */
  def state(name: String, config: State): StateProvider = js.native

  /**
   * Registers a state configuration under a given state name.
   *
   * @param config State configuration object.
   */
  def state(config: State): StateProvider = js.native

  /**
   * Allows you to extend (carefully) or override (at your own peril) the
   * `stateBuilder` object used internally by `\$stateProvider`. This can be used
   * to add custom functionality to ui-router, for example inferring templateUrl
   * based on the state name.
   *
   * When passing only a name, it returns the current (original or decorated) builder
   * function that matches `name`.
   *
   * The builder functions that can be decorated are listed below. Though not all
   * necessarily have a good use case for decoration, that is up to you to decide.
   *
   * In addition, users can attach custom decorators, which will generate new
   * properties within the state's internal definition. There is currently no clear
   * use-case for this beyond accessing internal states (i.e. \$state.\$current),
   * however, expect this to become increasingly relevant as we introduce additional
   * meta-programming features.
   *
   * **Warning**: Decorators should not be interdependent because the order of
   * execution of the builder functions in non-deterministic. Builder functions
   * should only be dependent on the state definition object and super function.
   *
   *
   * Existing builder functions and current return values:
   *
   * - **parent** `{object}` - returns the parent state object.
   * - **data** `{object}` - returns state data, including any inherited data that is not
   *   overridden by own values (if any).
   * - **url** `{object}` - returns a UrlMatcher
   *   or `null`.
   * - **navigable** `{object}` - returns closest ancestor state that has a URL (aka is
   *   navigable).
   * - **params** `{object}` - returns an array of state params that are ensured to
   *   be a super-set of parent's params.
   * - **views** `{object}` - returns a views object where each key is an absolute view
   *   name (i.e. "viewName@stateName") and each value is the config object
   *   (template, controller) for the view. Even when you don't use the views object
   *   explicitly on a state config, one is still created for you internally.
   *   So by decorating this builder function you have access to decorating template
   *   and controller properties.
   * - **ownParams** `{object}` - returns an array of params that belong to the state,
   *   not including any params defined by ancestor states.
   * - **path** `{string}` - returns the full path from the root down to this state.
   *   Needed for state activation.
   * - **includes** `{object}` - returns an object that includes every state that
   *   would pass a `\$state.includes()` test.
   *
   * @param name The name of the builder function to decorate.
   * @param decorator A function that is responsible for decorating the original
   * builder function. The function receives two parameters:
   *
   *   - `{object}` - state - The state config object.
   *   - `{object}` - super - The original builder function.
   *
   * @return \$stateProvider instance
   */
  def decorator(name: String, decorator: js.Function2[State, js.Function, Any] = js.native): StateProvider = js.native
}

/**
 * State configuration
 */
trait TypedState[T <: js.Object] extends js.Object {
  /**
   * A unique state name, e.g. "home", "about", "contacts". To create a parent/child state use a dot, e.g. "about.sales", "home.newest".
   */
  var name: String = js.native

  /**
   * html template as a string or a function that returns an html template as a string which should be used
   * by the uiView directives. This property takes precedence over templateUrl.
   *
   * If `template` is a function, it will be called with the following parameters:
   *
   *   - params: state parameters extracted from the current \$location.path() by
   *     applying the current state
   */
  var template: js.Object = js.native // string || (params: js.Object) => string

  /**
   * path or function that returns a path to an html template that should be used by uiView.
   *
   * If `templateUrl` is a function, it will be called with the following parameters:
   *
   *   - params: state parameters extracted from the current \$location.path() by
   *     applying the current state
   *
   */
  var templateUrl: String = js.native // string || (params: js.Object) => string

  /**
   * Provider function that returns HTML content string.
   */
  var templateProvider: js.Object = js.native // () => string || Promise<string>

  /**
   * Controller fn that should be associated with newly
   * related scope or the name of a registered controller if passed as a string.
   * Optionally, the ControllerAs may be declared here.
   *
   *
   * <pre>
   *  controller: "MyRegisteredController"
   *  controller: "MyRegisteredController as fooCtrl"
   *  controller: function(\$scope, MyService) {
   *     \$scope.data = MyService.getData(); }
   * </pre>
   */
  var controller: js.Any = js.native

  /**
   * A controller alias name. If present the controller will be published to scope under the controllerAs name.
   */
  var controllerAs: String = js.native

  /**
   * Injectable provider function that returns the actual controller or string.
   *
   * <pre>
   * controllerProvider:
   *   function(MyResolveData) {
   *     if (MyResolveData.foo)
   *       return "FooCtrl"
   *     else if (MyResolveData.bar)
   *       return "BarCtrl";
   *     else return function(\$scope) {
   *       \$scope.baz = "Qux";
   *     }
   *   }
   * </pre>
   */
  var controllerProvider: js.Function = js.native

  /**
   * An optional map[string, function] of dependencies which
   * should be injected into the controller. If any of these dependencies are promises,
   * the router will wait for them all to be resolved before the controller is instantiated.
   * If all the promises are resolved successfully, the \$stateChangeSuccess event is fired
   * and the values of the resolved promises are injected into any controllers that reference them.
   * If any  of the promises are rejected the \$stateChangeError event is fired.
   *
   * The map object is:
   *
   * - key - {string}: name of dependency to be injected into controller
   * - factory - {string|function}: If string then it is alias for service. Otherwise if function,
   *   it is injected and return value it treated as dependency. If result is a promise, it is
   *   resolved before its value is injected into controller.
   *
   * resolve: {
   *     myResolve1:
   *       function(\$http, \$stateParams) {
   *         return \$http.get("/api/foos/"+stateParams.fooID);
   *       }
   *     }
   */
  var resolve: js.Dictionary[js.Any] = js.native

  /**
   * A url fragment with optional parameters. When a state is navigated or
   * transitioned to, the `\$stateParams` service will be populated with any
   * parameters that were passed.
   *
   * Examples:
   * url: "/home"
   * url: "/users/:userid"
   * url: "/books/{bookid:[a-zA-Z_-]}"
   * url: "/books/{categoryid:int}"
   * url: "/books/{publishername:string}/{categoryid:int}"
   * url: "/messages?before&after"
   * url: "/messages?{before:date}&{after:date}"
   * url: "/messages/:mailboxid?{before:date}&{after:date}"
   */
  var url: String = js.native

  /**
   * A map which optionally configures parameters declared in the `url`, or
   * defines additional non-url parameters.  For each parameter being
   * configured, add a configuration object keyed to the name of the parameter.
   *
   *   Each parameter configuration object may contain the following properties:
   *
   *   - ** value ** - {object|function=}: specifies the default value for this
   *     parameter.  This implicitly sets this parameter as optional.
   *
   *     When UI-Router routes to a state and no value is
   *     specified for this parameter in the URL or transition, the
   *     default value will be used instead.  If `value` is a function,
   *     it will be injected and invoked, and the return value used.
   *
   *     *Note*: `undefined` is treated as "no default value" while `null`
   *     is treated as "the default value is `null`".
   *
   *     *Shorthand*: If you only need to configure the default value of the
   *     parameter, you may use a shorthand syntax.   In the **`params`**
   *     map, instead mapping the param name to a full parameter configuration
   *     object, simply set map it to the default parameter value, e.g.:
   *
   * <pre>// define a parameter's default value
   * params: {
   *     param1: { value: "defaultValue" }
   * }
   * // shorthand default values
   * params: {
   *     param1: "defaultValue",
   *     param2: "param2Default"
   * }</pre>
   *
   *   - ** array ** - {boolean=}: *(default: false)* If true, the param value will be
   *     treated as an array of values.  If you specified a Type, the value will be
   *     treated as an array of the specified Type.  Note: query parameter values
   *     default to a special `"auto"` mode.
   *
   *     For query parameters in `"auto"` mode, if multiple  values for a single parameter
   *     are present in the URL (e.g.: `/foo?bar=1&bar=2&bar=3`) then the values
   *     are mapped to an array (e.g.: `{ foo: [ '1', '2', '3' ] }`).  However, if
   *     only one value is present (e.g.: `/foo?bar=1`) then the value is treated as single
   *     value (e.g.: `{ foo: '1' }`).
   *
   * <pre>params: {
   *     param1: { array: true }
   * }</pre>
   */
  var params: js.Dictionary[js.Any] = js.native

  /**
   * An optional map[string, object] which defined multiple views, or targets views
   * manually/explicitly.
   *
   * Examples:
   *
   * Targets three named `ui-view`s in the parent state's template
   * views: {
   *     header: {
   *       controller: "headerCtrl",
   *       templateUrl: "header.html"
   *     }, body: {
   *       controller: "bodyCtrl",
   *       templateUrl: "body.html"
   *     }, footer: {
   *       controller: "footCtrl",
   *       templateUrl: "footer.html"
   *     }
   *   }
   *
   * Targets named `ui-view="header"` from grandparent state top\'s template, and named `ui-view="body"` from parent state's template.
   * views: {
   *     'header@top': {
   *       controller: "msgHeaderCtrl",
   *       templateUrl: "msgHeader.html"
   *     }, 'body': {
   *       controller: "messagesCtrl",
   *       templateUrl: "messages.html"
   *     }
   *   }
   */
  var views: js.Dictionary[View] = js.native

  /**
   * An abstract state will never be directly activated,
   * but can provide inherited properties to its common children states.
   */
  var `abstract`: Boolean = js.native

  /**
   * Callback function for when a state is entered. Good way
   * to trigger an action or dispatch an event, such as opening a dialog.
   * If minifying your scripts, make sure to explicitly annotate this function,
   * because it won't be automatically annotated by your build tools.
   *
   * onEnter: function(MyService, \$stateParams) {
   *     MyService.foo(\$stateParams.myParam);
   * }
   *
   */
  var onEnter: js.Function = js.native

  /**
   * Callback function for when a state is exited. Good way to
   * trigger an action or dispatch an event, such as opening a dialog.
   * If minifying your scripts, make sure to explicitly annotate this function,
   * because it won't be automatically annotated by your build tools.
   *
   * onExit: function(MyService, \$stateParams) {
   *     MyService.cleanup(\$stateParams.myParam);
   * }
   */
  var onExit: js.Function = js.native

  /**
   * Arbitrary data object, useful for custom configuration.  The parent state's `data` is
   * prototypally inherited. In other words, adding a data property to a state adds it to
   * the entire subtree via prototypal inheritance.
   *
   * data: {
   *     requiredRole: 'foo'
   * }
   */
  var data: T = js.native

  /**
   * If `false`, will not re-trigger the same state
   * just because a search/query parameter has changed (via \$location.search() or \$location.hash()).
   * Useful for when you'd like to modify \$location.search() without triggering a reload.
   */
  var reloadOnSearch: Boolean = js.native

}

trait State extends TypedState[js.Object]

object State {

  import js.Dynamic.literal
  import js.JSConverters._

  private[this] val none = new AnnotatedFunction(js.Array())

  def apply(url: String, isAbstract: Boolean = false,
           template: String = null,
           templateFn: js.Function1[js.Object, String] = null,
           templateUrl: String = null,
           templateUrlFn: js.Function1[js.Object, String] = null,
           templateProviderFn: js.Function = null,
           templateProviderPromise: QPromise = null,
           controller: String = null,
           controllerFn: js.Function = null,
           controllerAs: String = null,
           controllerProvider: js.Function = null,
           resolve: js.Dictionary[js.Any] = null,
           params: js.Dictionary[js.Any] = null,
           views: Map[String, View] = Map.empty,
           onEnter: AnnotatedFunction = none,
           onExit: AnnotatedFunction = none,
           reloadOnSearch: Boolean = true,
           data: js.Object = null): State = {

    typed[js.Object](url, isAbstract, template, templateFn, templateUrl, templateUrlFn,
                     templateProviderFn, templateProviderPromise, controller, controllerFn, controllerAs, controllerProvider,
                     resolve, params,views, onEnter, onExit, reloadOnSearch, data).asInstanceOf[State]
  }

  def typed[T <: js.Object](url: String, isAbstract: Boolean = false,
           template: String = null,
           templateFn: js.Function1[js.Object, String] = null,
           templateUrl: String = null,
           templateUrlFn: js.Function1[js.Object, String] = null,
           templateProviderFn: js.Function = null,
           templateProviderPromise: QPromise = null,
           controller: String = null,
           controllerFn: js.Function = null,
           controllerAs: String = null,
           controllerProvider: js.Function = null,
           resolve: js.Dictionary[js.Any] = null,
           params: js.Dictionary[js.Any] = null,
           views: Map[String, View] = null,
           onEnter: AnnotatedFunction = none,
           onExit: AnnotatedFunction = none,
           reloadOnSearch: Boolean = true,
           data: T = null): TypedState[T] = {

    val out = literal(url = url)
    if (isAbstract) out.`abstract` = true

    if (template != null) out.template = template
    else if (templateFn != null) out.template = templateFn
    if (templateUrl != null) out.templateUrl = templateUrl
    else if (templateUrlFn != null) out.templateUrl = templateUrl
    if (templateProviderFn != null) out.templateProvider = templateProviderFn
    else if (templateProviderPromise != null) out.templateProvider = templateProviderPromise

    if (controller != null) out.controller = controller
    else if (controllerFn != null) out.controller = controllerFn
    if (controllerAs != null) out.controllerAs = controllerAs
    if (controllerProvider != null) out.controllerProvider = controllerProvider

    if (resolve != null) out.resolve = resolve
    if (params != null) out.params = params
    if (views != null) out.views = views.toJSDictionary
    if (onEnter != none) out.onEnter = onEnter.inlineArrayAnnotatedFn
    if (onExit != none) out.onExit = onExit.inlineArrayAnnotatedFn
    if (!reloadOnSearch) out.reloadOnSearch = reloadOnSearch
    if (data != null) out.data = data

    js.Dynamic.global.console.log(out)

    out.asInstanceOf[TypedState[T]]
  }

}

trait StateOptions extends js.Object {

  /**
   * {boolean=true|string=} - If `true` will update the url in the location bar, if `false`
   * will not. If string, must be `"replace"`, which will update url and also replace last history record.
   */
  var location: js.Any = js.native

  /**
   * {boolean=true}, If `true` will inherit url parameters from current url.
   */
  var inherit: Boolean = js.native

  /**
   * {object=\$state.\$current}, When transitioning with relative path (e.g '{{{^}}}'),
   *    defines which state to be relative from.
   */
  var relative: State = js.native

  /**
   * {boolean=true}, If `true` will broadcast \$stateChangeStart and \$stateChangeSuccess events.
   */
  @JSName("notify") var notifyStateChanges: Boolean = js.native

  /**
   * (v0.2.5) - {boolean=false}, If `true` will force transition even if the state or params
   * have not changed, aka a reload of the same state. It differs from reloadOnSearch because you'd
   * use this when you want to force a reload when *everything* is the same, including search params.
   */
  var reload: Boolean = js.native
}

object StateOptions {

  import js.Dynamic.literal

  def apply(location: Boolean = true, locationStr: String = "", inherit: Boolean = true,
             relative: State = null, notify : Boolean = true, reload : Boolean = false): StateOptions = {

    val out = literal(location = location, inherit = inherit, relative = relative, notify = notify, reload = reload).asInstanceOf[StateOptions]

    if (locationStr != "") out.location = locationStr
    out
  }
}

trait CheckStateOptions extends js.Object {

  /** If `state` is a relative state name and `options.relative` is set, it will test relative to `options.relative` state (or name). */
  var relative: js.Any = js.native

}

object CheckStateOptions {
  import js.Dynamic.literal

  def apply(relative: String): CheckStateOptions = {
    literal(relative = relative).asInstanceOf[CheckStateOptions]
  }

  def apply(relative: State): CheckStateOptions = {
    literal(relative = relative).asInstanceOf[CheckStateOptions]
  }

}

/**
 * Error values of the promise returned by one of the \$state [[StateService]] methods.
 */
object StateTransitionRejection {

  /** when a newer transition has been started after this one */
  val transitionSuperseded = "transition superseded"
  /** when `event.preventDefault()` has been called in a `\$stateChangeStart` listener */
  val transitionPrevented = "transition prevented"
  /** when `event.preventDefault()` has been called in a `\$stateNotFound` listener or when a `\$stateNotFound` `event.retry` promise errors. */
  val transitionAborted = "transition aborted"
  /** when a state has been unsuccessfully found after 2 tries. */
  val transitionFailed = "transition failed"
  /** when an error has occurred with a `resolve` */
  val resolveError = "resolve error"

}
/**
 * `\$state` service is responsible for representing states as well as transitioning
 * between them. It also provides interfaces to ask for current state or even states
 * you're coming from.
 */
trait StateService extends js.Object with ProvidedService {

  /**
   * Convenience method for transitioning to a new state. `\$state.go` calls
   * `\$state.transitionTo` internally but automatically sets options to
   * `{ location: true, inherit: true, relative: \$state.\$current, notify: true }`.
   * This allows you to easily use an absolute or relative to path and specify
   * only the parameters you'd like to update (while letting unspecified parameters
   * inherit from the currently active ancestor states).
   *
   * Example:
   * var app = angular.module('app', ['ui.router']);
   *
   * app.controller('ctrl', function (\$scope, \$state) {
   *   \$scope.changeState = function () {
   *     \$state.go('contact.detail');
   *   };
   * });
   *
   * @param to Absolute state name or relative state path. Some examples:
   *
   * - `\$state.go('contact.detail')` - will go to the `contact.detail` state
   * - `\$state.go('^')` - will go to a parent state
   * - `\$state.go('^.sibling')` - will go to a sibling state
   * - `\$state.go('.child.grandchild')` - will go to grandchild state
   *
   * @param params A map of the parameters that will be sent to the state,
   * will populate \$stateParams. Any parameters that are not specified will be inherited from currently
   * defined parameters. This allows, for example, going to a sibling state that shares parameters
   * specified in a parent state. Parameter inheritance only works between common ancestor states, I.e.
   * transitioning to a sibling will get you the parameters for all parents, transitioning to a child
   * will get you all current parameters, etc.
   * @param options State options object.
   *
   * @return A promise representing the state of the new transition.
   *
   * Possible success values:
   *
   * - \$state.current
   *
   * Possible rejection values:
   *
   * - 'transition superseded' - when a newer transition has been started after this one
   * - 'transition prevented' - when `event.preventDefault()` has been called in a `\$stateChangeStart` listener
   * - 'transition aborted' - when `event.preventDefault()` has been called in a `\$stateNotFound` listener or
   *   when a `\$stateNotFound` `event.retry` promise errors.
   * - 'transition failed' - when a state has been unsuccessfully found after 2 tries.
   * - *resolve error* - when an error has occurred with a `resolve`
   */
  def go(to: String, params: js.Dictionary[js.Any] = js.Dictionary(), options: StateOptions = js.native): QPromise = js.native

  /**
   * Low-level method for transitioning to a new state.
   * [[StateService.go()]] uses `transitionTo` internally. `\$state.go` is recommended in most situations.
   *
   * Example:
   * <pre>
   * var app = angular.module('app', ['ui.router']);
   *
   * app.controller('ctrl', function (\$scope, \$state) {
   *   \$scope.changeState = function () {
   *     \$state.transitionTo('contact.detail');
   *   };
   * });
   * </pre>
   *
   * @param to State name.
   * @param toParams A map of the parameters that will be sent to the state,
   * will populate \$stateParams.
   * @param options State options
   * @return A promise representing the state of the new transition. See [[StateService.go()]]
   */
  def transitionTo(to: String, toParams: js.Dictionary[js.Any] = js.Dictionary(), options: StateOptions = js.native): QPromise = js.native

  /**
   * A method to determine if the current active state is equal to or is the child of the
   * state stateName. If any params are passed then they will be tested for a match as well.
   * Not all the parameters need to be passed, just the ones you'd like to test for equality.
   *
   * Example:
   * Partial and relative names
   *
   * <pre>
   * \$state.\$current.name = 'contacts.details.item';
   *
   * // Using partial names
   * \$state.includes("contacts"); // returns true
   * \$state.includes("contacts.details"); // returns true
   * \$state.includes("contacts.details.item"); // returns true
   * \$state.includes("contacts.list"); // returns false
   * \$state.includes("about"); // returns false
   *
   * // Using relative names (. and {{{^}}}, typically from a template
   * // E.g. from the 'contacts.details' template
   * div ng-class="{highlighted: \$state.includes('.item')}" Item /div
   * </pre>
   *
   * Basic globbing patterns:
   * <pre>
   * \$state.\$current.name = 'contacts.details.item.url';
   *
   * \$state.includes("*.details.*.*"); // returns true
   * \$state.includes("*.details.**"); // returns true
   * \$state.includes("**.item.**"); // returns true
   * \$state.includes("*.details.item.url"); // returns true
   * \$state.includes("*.details.*.url"); // returns true
   * \$state.includes("*.details.*"); // returns false
   * \$state.includes("item.**"); // returns false
   * </pre>
   *
   * @param state A partial name, relative name, or glob pattern
   * to be searched for within the current state name.
   * @param params A param object, e.g. `{sectionId: section.id}`,
   * that you'd like to test against the current active state.
   * @param options An options object.  The options are:
   *
   * - **`relative`** - {string|object=} -  If `stateOrName` is a relative state reference and `options.relative` is set,
   * .includes will test relative to `options.relative` state (or name).
   *
   * @return Returns true if it does include the state
   */
  def includes(state: String, params: js.Dictionary[js.Any] = js.Dictionary(), options: CheckStateOptions = js.native): Boolean = js.native

  /**
   * A method to determine if the current active state is equal to or is the child of the
   * state stateName. If any params are passed then they will be tested for a match as well.
   * Not all the parameters need to be passed, just the ones you'd like to test for equality.
   *
   * Example:
   * Partial and relative names
   *
   * <pre>
   * \$state.\$current.name = 'contacts.details.item';
   *
   * // Using partial names
   * \$state.includes("contacts"); // returns true
   * \$state.includes("contacts.details"); // returns true
   * \$state.includes("contacts.details.item"); // returns true
   * \$state.includes("contacts.list"); // returns false
   * \$state.includes("about"); // returns false
   *
   * // Using relative names (. and {{{^}}}), typically from a template
   * // E.g. from the 'contacts.details' template
   * div ng-class="{highlighted: \$state.includes('.item')}" Item /div
   * </pre>
   *
   * Basic globbing patterns:
   * <pre>
   * \$state.\$current.name = 'contacts.details.item.url';
   *
   * \$state.includes("*.details.*.*"); // returns true
   * \$state.includes("*.details.**"); // returns true
   * \$state.includes("**.item.**"); // returns true
   * \$state.includes("*.details.item.url"); // returns true
   * \$state.includes("*.details.*.url"); // returns true
   * \$state.includes("*.details.*"); // returns false
   * \$state.includes("item.**"); // returns false
   * </pre>
   *
   * @param state A partial name, relative name, or glob pattern
   * to be searched for within the current state name.
   * @param params A param object, e.g. `{sectionId: section.id}`,
   * that you'd like to test against the current active state.
   * @param options An options object.  The options are:
   *
   * - **`relative`** - {string|object=} -  If `stateOrName` is a relative state reference and `options.relative` is set,
   * .includes will test relative to `options.relative` state (or name).
   *
   * @return Returns true if it does include the state
   */
  def includes(state: State, params: js.Dictionary[js.Any], options: CheckStateOptions): Boolean = js.native

  /**
   * Similar to [[StateService.includes()]] but only checks for the full state name. If params is supplied then it will be
   * tested for strict equality against the current active params object, so all params must match with none missing and no extras.
   *
   * Example:
   * \$state.\$current.name = 'contacts.details.item';
   *
   * // absolute name
   * \$state.is('contact.details.item'); // returns true
   * \$state.is(contactDetailItemStateObject); // returns true
   *
   * // relative name (. and {{{^}}}), typically from a template
   * // E.g. from the 'contacts.details' template
   * div ng-class="{highlighted: \$state.is('.item')}" Item /div
   *
   * @param state state name (absolute or relative) you'd like to check.
   * @param params A param object, e.g. `{sectionId: section.id}`, that you'd like
   * to test against the current active state.
   * @param options An options object.  The options are:
   *
   * - **`relative`** - {string|object} -  If `stateOrName` is a relative state name and `options.relative` is set, .is will
   * test relative to `options.relative` state (or name).
   *
   * @return Returns true if it is the state.
   */
  def is(state: String, params: js.Dictionary[js.Any] = js.Dictionary(), options: CheckStateOptions = js.native): Boolean = js.native

  /**
   * Similar to [[StateService.includes()]] but only checks for the full state name. If params is supplied then it will be
   * tested for strict equality against the current active params object, so all params must match with none missing and no extras.
   *
   * Example:
   * \$state.\$current.name = 'contacts.details.item';
   *
   * // absolute name
   * \$state.is('contact.details.item'); // returns true
   * \$state.is(contactDetailItemStateObject); // returns true
   *
   * // relative name (. and {{{^}}}), typically from a template
   * // E.g. from the 'contacts.details' template
   * div ng-class="{highlighted: \$state.is('.item')}" Item /div
   *
   * @param state state object you'd like to check.
   * @param params A param object, e.g. `{sectionId: section.id}`, that you'd like
   * to test against the current active state.
   * @param options An options object.  The options are:
   *
   * - **`relative`** - {string|object} -  If `stateOrName` is a relative state name and `options.relative` is set, .is will
   * test relative to `options.relative` state (or name).
   *
   * @return Returns true if it is the state.
   */
  def is(state: State, params: js.Dictionary[js.Any], options: CheckStateOptions): Boolean = js.native

  /**
   * A url generation method that returns the compiled url for the given state populated with the given params.
   *
   * Example:
   * expect(\$state.href("about.person", { person: "bob" })).toEqual("/about/bob");
   *
   * @param state The state name you'd like to generate a url from.
   * @param params An object of parameter values to fill the state's required parameters.
   * @param options The Href options
   * @return compiled state url
   */
  def href(state: State, params: js.Dictionary[js.Any], options: HrefOptions): String = js.native

  /**
   * A url generation method that returns the compiled url for the given state populated with the given params.
   *
   * Example:
   * expect(\$state.href("about.person", { person: "bob" })).toEqual("/about/bob");
   *
   * @param state The state object you'd like to generate a url from.
   * @param params An object of parameter values to fill the state's required parameters.
   * @param options The Href options
   * @return compiled state url
   */
  def href(state: String, params: js.Dictionary[js.Any] = js.Dictionary(), options: HrefOptions): String = js.native

  /**
   * Returns all states.
   *
   * @return
   */
  def get(): js.Array[State] = js.native

  /**
   * Returns the state configuration object for any specific state or all states.
   *
   * @param state (absolute or relative) If provided, will only get the config for
   * the requested state.
   * @return  State configuration object
   */
  def get(state: State): State = js.native

  /**
   * Returns the state configuration object for any specific state or all states.
   *
   * @param state (absolute or relative) get the config for the requested state.
   * @param context When state is a relative state reference, the state will be retrieved relative to context.
   * @return State configuration object
   */
  def get(state: String, context: String = js.native): State = js.native

  /**
   * Returns the state configuration object for any specific state or all states.
   *
   * @param state (absolute or relative) get the config for the requested state.
   * @param context When state is a relative state reference, the state will be retrieved relative to context.
   * @return State configuration object
   */
  def get(state: String, context: State): State = js.native

  /**
   * A reference to the state's config object. However you passed it in. Useful for accessing custom data.
   */
  def current: State = js.native

  /**
   * A param object, e.g. {sectionId: section.id}, that
   * you'd like to test against the current active state.
   */
  def params: js.Object = js.native

  /**
   * Currently pending transition. A promise that will resolve or reject
   */
  def transition: QPromise = js.native

  /**
   * A method that force reloads the current state. All resolves are re-resolved, events are not re-fired,
   * and controllers reinstantiated (bug with controllers reinstantiating right now, fixing soon).
   *
   * @return A promise representing the state of the new transition.
   */
  def reload(): QPromise = js.native
}
