package biz.enef.angular.ext

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

trait UiViewScrollProvider extends js.Object {


  /*
   * Reverts back to using the core $anchorScroll service for scrolling
   * based on the url anchor.
   */
  def useAnchorScroll(): Unit = js.native

}
