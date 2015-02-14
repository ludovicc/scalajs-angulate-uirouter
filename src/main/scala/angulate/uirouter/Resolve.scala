package angulate.uirouter

import scalajs.js

/**
 * Manages resolution of (acyclic) graphs of promises.
 */
trait Resolve extends js.Object {

  /**
   * Studies a set of invocables that are likely to be used multiple times.
   * <pre>
   * \$resolve.study(invocables)(locals, parent, self)
   * </pre>
   * is equivalent to
   * <pre>
   * \$resolve.resolve(invocables, locals, parent, self)
   * </pre>
   * but the former is more efficient (in fact `resolve` just calls `study`
   * internally).
   *
   * @param invocables Invocable objects
   * @return a function to pass in locals, parent and self
   */
  def study(invocables: js.Object): js.Function = js.native

  /**
   * Resolves a set of invocables. An invocable is a function to be invoked via
   * `\$injector.invoke()`, and can have an arbitrary number of dependencies.
   * An invocable can either return a value directly,
   * or a `\$q` promise. If a promise is returned it will be resolved and the
   * resulting value will be used instead. Dependencies of invocables are resolved
   * (in this order of precedence)
   *
   * - from the specified `locals`
   * - from another invocable that is part of this `\$resolve` call
   * - from an invocable that is inherited from a `parent` call to `\$resolve`
   *   (or recursively
   * - from any ancestor `\$resolve` of that parent).
   *
   * The return value of `\$resolve` is a promise for an object that contains
   * (in this order of precedence)
   *
   * - any `locals` (if specified)
   * - the resolved return values of all injectables
   * - any values inherited from a `parent` call to `\$resolve` (if specified)
   *
   * The promise will resolve after the `parent` promise (if any) and all promises
   * returned by injectables have been resolved. If any invocable
   * (or `\$injector.invoke`) throws an exception, or if a promise returned by an
   * invocable is rejected, the `\$resolve` promise is immediately rejected with the
   * same error. A rejection of a `parent` promise (if specified) will likewise be
   * propagated immediately. Once the `\$resolve` promise has been rejected, no
   * further invocables will be called.
   *
   * Cyclic dependencies between invocables are not permitted and will causes `\$resolve`
   * to throw an error. As a special case, an injectable can depend on a parameter
   * with the same name as the injectable, which will be fulfilled from the `parent`
   * injectable of the same name. This allows inherited values to be decorated.
   * Note that in this case any other injectable in the same `\$resolve` with the same
   * dependency would see the decorated value, not the inherited value.
   *
   * Note that missing dependencies -- unlike cyclic dependencies -- will cause an
   * (asynchronous) rejection of the `\$resolve` promise rather than a (synchronous)
   * exception.
   *
   * Invocables are invoked eagerly as soon as all dependencies are available.
   * This is true even for dependencies inherited from a `parent` call to `\$resolve`.
   *
   * As a special case, an invocable can be a string, in which case it is taken to
   * be a service name to be passed to `\$injector.get()`. This is supported primarily
   * for backwards-compatibility with the `resolve` property of `\$routeProvider`
   * routes.
   *
   * @param invocables functions to invoke or
   * `\$injector` services to fetch.
   * @param locals  values to make available to the injectables
   * @param parent  a promise returned by another call to `\$resolve`.
   * @param self  the `this` for the invoked methods
   * @return Promise for an object that contains the resolved return value
   * of all invocables, as well as any inherited and local values.
   */
  def resolve(invocables: js.Object, locals: js.Object, parent: js.Object, self: js.Object): js.Object = js.native
}