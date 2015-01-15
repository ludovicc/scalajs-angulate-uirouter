package biz.enef.angular.ext

import scala.scalajs.js
import scala.scalajs.js.annotation.JSBracketAccess

// Some code derived from scalajs-angular
// Copyright greencatsoft.com under an Apache2 license

// Some code derived from borisyankov/DefinitelyTyped
// MIT license

/**
 * The $stateParams service is an object that will have one key per url parameter.
 * The $stateParams is a perfect way to provide your controllers or other services with
 * the individual parts of the navigated url.<br/>
 * If you had a url on your state of:
 *
 * url: '/users/:id/details/{type}/{repeat:[0-9]+}?from&to'
 *
 * Then you navigated your browser to:
 *
 * '/users/123/details//0'
 *
 * Your $stateParams object would be
 *
 * { id:'123', type:'', repeat:'0' }
 *
 * Then you navigated your browser to:
 *
 * '/users/123/details/default/0?from=there&to=here'
 *
 * Your $stateParams object would be
 *
 * { id:'123', type:'default', repeat:'0', from:'there', to:'here' }
 */
trait StateParams extends js.Object {
  /**
   * Get the parameter value from its name.<br/>
   * If you have a $stateParams object { id:'123', type:'default', repeat:'0', from:'there', to:'here' },
   * then to get the value of the "type" key, you should call $stateParams("type").
   */
  @JSBracketAccess
  def apply(key: String): js.Any = js.native
  @JSBracketAccess
  def update(key: String, v: js.Any): Unit = js.native
}

/**
 * The $stateProvider works similar to Angular's v1 router, but it focuses purely on state.
 *
 * A state corresponds to a "place" in the application in terms of the overall UI and navigation.
 * A state describes (via the controller / template / view properties) what the UI looks like and does at that place.
 * States often have things in common, and the primary way of factoring out these commonalities in this model is via the state hierarchy, i.e. parent/child states aka nested states.
 *
 *
 */
trait StateProvider extends js.Object {
  def state(name: String, config: State): StateProvider = js.native
  def state(config: State): StateProvider = js.native
  def decorator(name: String = js.native, decorator: js.Function2[State, js.Function, Any] = js.native): js.Dynamic = js.native
}

trait State extends js.Object {
  var name: String = js.native
  var template: String = js.native
  var templateUrl: String = js.native // string || () => string
  var templateProvider: js.Any = js.native // () => string || IPromise<string>
  var controller: js.Any = js.native
  var controllerAs: String = js.native
  var controllerProvider: js.Any = js.native
  //var resolve: js.Dictionary[X] = js.native
  var url: String = js.native
  var params: js.Array[js.Any] = js.native
  var views: js.Dictionary[View] = js.native
  var `abstract`: Boolean = js.native
  var onEnter: js.Function = js.native
  var onExit: js.Function = js.native
  var data: js.Any = js.native
  var reloadOnSearch: Boolean = js.native
}

trait TypedState[T] extends State {
  override var data: T = js.native
}

object State {
  def apply(url: String, isAbstract: Boolean = false, templateUrl: String = "", views: Map[String, View] = Map.empty): State = {
    val out = new js.Object().asInstanceOf[State]
    out.url = url
    out.`abstract` = isAbstract
    if (!templateUrl.isEmpty()) out.templateUrl = templateUrl
    if (!views.isEmpty) out.views = views.toJSDictionary
    out
  }
}

/*
interface IStateOptions {
        location?: any;
        inherit?: boolean;
        relative?: IState;
        notify?: boolean;
        reload?: boolean;
    }

interface IStateService {
        go(to: string, params?: {}, options?: IStateOptions): IPromise<any>;
        transitionTo(state: string, params?: {}, updateLocation?: boolean): void;
        transitionTo(state: string, params?: {}, options?: IStateOptions): void;
        includes(state: string, params?: {}): boolean;
        is(state:string, params?: {}): boolean;
        is(state: IState, params?: {}): boolean;
        href(state: IState, params?: {}, options?: IHrefOptions): string;
        href(state: string, params?: {}, options?: IHrefOptions): string;
        get(state: string): IState;
        get(): IState[];
        current: IState;
        params: any;
        reload(): void;
    }

    interface IStateParamsService {
        [key: string]: any;
    }

    interface IStateParams {
        [key: string]: any;
    }
*/