package test

import angulate.uirouter.{StateService, State, StateProvider}
import biz.enef.angular.Angular
import utest._

import scala.scalajs.js

object StateTest extends UIRouterTestSuite {

  override val tests = TestSuite {
    implicit val module = Angular.module("test", Seq("ui.router"))

    module.config( ($stateProvider: StateProvider) => {
      $stateProvider.state("home", State(
        url = "/"
      ))
    })

    module.run( ($state: StateService) =>
      assert(
        $state.get("home").url == '/'
      )
    )
  }

}
