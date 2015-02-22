package angulate.uirouter

import scala.scalajs.js

/**
 * \$uiViewScrollProvider
 */
trait UiViewScrollProvider extends js.Object {

  /**
   * Reverts back to using the core \$anchorScroll service for scrolling
   * based on the url anchor.
   */
  def useAnchorScroll(): Unit = js.native

  /**
   * When called with a jqLite element, it scrolls the element into view (after a
   * `\$timeout` so the DOM has time to refresh).
   *
   * If you prefer to rely on `\$anchorScroll` to scroll the view to the anchor,
   * this can be enabled by calling [[useAnchorScroll()]].
   */
  def $get: js.Object = js.native

}
