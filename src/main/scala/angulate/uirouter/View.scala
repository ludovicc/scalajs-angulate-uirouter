package angulate.uirouter

import scala.scalajs.js

// Some code derived from scalajs-angular
// Copyright greencatsoft.com under an Apache2 license

// Some code derived from borisyankov/DefinitelyTyped
// MIT license

trait View extends js.Object {
  var templateUrl: String = js.native
  var controller: String = js.native
}

object View {
  def apply(templateUrl: String, controller: String): View = {
    val out = new js.Object().asInstanceOf[View]
    out.templateUrl = templateUrl
    out.controller = controller
    out
  }
}

/**
 * $view service
 */
trait ViewService extends js.Object {

  /**
   * $view.load('full.viewName', { template: ..., controller: ..., resolve: ..., async: false, params: ... })
   */
  def load(name: String, options: js.Dictionary[js.Any]): Unit = js.native

}
