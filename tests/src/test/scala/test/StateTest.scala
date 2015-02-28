package test

import angulate.uirouter.{StateService, State, StateProvider}
import biz.enef.angulate._
import utest._

import scala.scalajs.js
import org.scalajs.dom
import scala.language.dynamics

object StateTest extends TestSuite {

  implicit val module = angular.createModule("test", Seq("ui.router"))

  module.config( ($stateProvider: StateProvider) => {
    $stateProvider.state("home", State(
      url = "/xx",
      template = "<div></div>"
    ))
  })

  dom.document.body.innerHTML = "<ui-view></ui-view>"
  angular.bootstrap(dom.document.body, Seq("test"))

  override val tests = TestSuite {
    import TestHelpers._

    'StateProvider-{
      'singleState-{

        val $state = angular.element(dom.document.body).injector().get[StateService]("$state")
        val url = $state.get("home").url
        assert(url == "/xx")
      }
    }
  }
}
