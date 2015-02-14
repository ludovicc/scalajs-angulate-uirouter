package test

import angulate.uirouter.{StateService, State, StateProvider}
import biz.enef.angular.Angular
import utest._
import utest.framework.TestSuite
import utest.ExecutionContext.RunNow

import scala.scalajs.js
import org.scalajs.dom
import org.scalajs.dom.html.Div
import scalatags._
import scalatags.JsDom.all._
import scala.language.dynamics

object StateTest extends TestSuite {

  override val tests = TestSuite {
    import TestHelpers._
    implicit val module = Angular.module("test", Seq("ng", "ui.router"))

    // val body = dom.document.body
    // body.appendChild(div("ngApp".attr := "").render)
    // js.Dynamic.global.body = body

    // module.config(($provide: js.Dynamic) => {
    //   $provide.value("$rootElement", dom.document.body)
    // })

angular.element(document).ready(function() {
      angular.bootstrap(document, ['myApp']);
    });

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
