package angulate.uirouter

import biz.enef.angulate.core.ProvidedService

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
  import js.Dynamic.literal

  def apply(templateUrl: String, controller: String): View = {
    literal(templateUrl = templateUrl, controller = controller).asInstanceOf[View]
  }
}

/**
 * \$view service
 */
trait ViewService extends js.Object with ProvidedService {

  /**
   * \$view.load('full.viewName', { template: ..., controller: ..., resolve: ..., async: false, params: ... })
   */
  def load(name: String, options: js.Dictionary[js.Any]): Unit = js.native

}
