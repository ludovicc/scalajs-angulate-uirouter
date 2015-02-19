package test

import angulate.uirouter.{StateService, State, StateProvider}
import biz.enef.angulate._
import utest._
import utest.ExecutionContext.RunNow

import scala.scalajs.js
import org.scalajs.dom
import scala.language.dynamics

object StateTest extends TestSuite {

  override val tests = TestSuite {
    import TestHelpers._
    implicit val module = angular.createModule("test", Seq("ui.router"))

    //angular.bootstrap(dom.document.body, Seq("test"))

    val $state = injection[StateService]("$state")

    'StateProvider-{
      'singleState-{
        module.config( ($stateProvider: StateProvider) => {
          println("Config single state")
          $stateProvider.state("home", State(
            url = "/"
          ))
        })

        val url = $state.get("home").url
        println("State: " + url)
        assert(url == "//xx")
      }
    }
  }
}
