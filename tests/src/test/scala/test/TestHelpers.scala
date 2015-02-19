package test

import biz.enef.angulate.{angular, Scope, Module}

import scala.scalajs.js
import scala.scalajs.js.UndefOr
import js.Dynamic.literal
import Module.RichModule

object TestHelpers {

  /**
   * Returns the dependency with the specified name from the (implicitly) specified module.
   *
   * @param name
   * @param module
   * @tparam T
   * @return
   */
  def injection[T](name: String)(implicit module: RichModule) : T =
    angular.injector(js.Array("ng","ui.router",module.name)).get(name).asInstanceOf[T]

  /**
   * Checks that the specified value is neither null nor undefined.
   *
   * @param value
   */
  def defined(value: Any) : Boolean = value != null && value.asInstanceOf[UndefOr[js.Any]].isDefined

  /**
   * Creates an instance specified controller and returns its scope object.
   *
   * @param name the name of the controller type
   * @param module
   * @tparam T the scope type
   */
  def controller[T](name: String)(implicit module: RichModule) : T = {
    val $controller = injection[js.Function2[String,js.Object,js.Any]]("$controller")
    val $rootScope = injection[Scope]("$rootScope")
    val scope = $rootScope.$new(false)
    val res = $controller(name, literal($scope = scope))
    scope.asInstanceOf[T]
  }

}
