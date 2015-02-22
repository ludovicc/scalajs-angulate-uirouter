package angulate.uirouter

import scala.scalajs.js

/**
 * Configuration for a template.
 *
 * The following properties are search in the specified order, and the first one
 * that is defined is used to create the template:
 */
trait Template extends js.Object {

  /** string template or function to load via [[TemplateFactory.fromString()]] */
  var template: js.Any = js.native

  /** url to load or a function returning the url to load via [[TemplateFactory.fromUrl()]] */
  var templateUrl: js.Any = js.native

  /** function to invoke via [[TemplateFactory.fromProvider()]] */
  var templateProvider: js.Function = js.native
}

object Template {
  import js.Dynamic.literal

  def apply(template: String = "", templateFn: js.Function = null,
            templateUrl: String = "", templateUrlFn: js.Function = null,
            templateProvider: js.Function = null): Template = {

    val out = literal(template = template, templateFn = templateFn, templateProvider = templateProvider).asInstanceOf[Template]

    if (templateFn != null) out.template = templateFn
    else if (templateUrlFn != null) out.templateUrl = templateUrlFn

    out
  }
}

/**
 * \$templateFactory Service. Manages loading of templates.
 */
trait TemplateFactory extends js.Object {

  /**
   * Creates a template from a configuration object.
   *
   * @param config Configuration object for which to load a template.
   * @param params  Parameters to pass to the template function.
   * @param locals Locals to pass to `invoke` if the template is loaded
   * via a `templateProvider`. Defaults to `{ params: params }`.
   *
   * @return {String|QPromise}  The template html as a string, or a promise for that string, or `null` if no template is configured.
   */
  def fromConfig(config: Template, params: js.Dictionary[js.Any] = js.Dictionary(), locals: js.Dictionary[js.Any] = js.Dictionary()): js.Any = js.native

  /**
   * Creates a template from a string or a function returning a string.
   *
   * @param template html template as a string
   * @param params Parameters to pass to the template function.
   *
   * @return {string|object} The template html as a string, or a promise for that string.
   */
  def fromString(template: String, params: js.Dictionary[js.Any] = js.Dictionary()): js.Any = js.native

  /**
   * Creates a template from a string or a function returning a string.
   *
   * @param template function that returns an html template as a string.
   * @param params Parameters to pass to the template function.
   *
   * @return {string|object} The template html as a string, or a promise for that string.
   */
  def fromString(template: js.Function, params: js.Dictionary[js.Any]): js.Any = js.native

  /**
   * Loads a template from the a URL via `\$http` and `\$templateCache`.
   *
   * @param url url of the template to load.
   * @param params Parameters to pass to the url function.
   * @return {string|Promise.<string>} The template html as a string, or a promise for that string.
   */
  def fromUrl(url: String, params: js.Dictionary[js.Any] = js.Dictionary()): js.Any = js.native

  /**
   * Loads a template from the a URL via `\$http` and `\$templateCache`.
   *
   * @param url a function that returns a url.
   * @param params Parameters to pass to the url function.
   * @return {string|Promise.<string>} The template html as a string, or a promise for that string.
   */
  def fromUrl(url: js.Function, params: js.Dictionary[js.Any]): js.Any = js.native

  /**
   * Creates a template by invoking an injectable provider function.
   *
   * @param provider Function to invoke via `\$injector.invoke`
   * @param params Parameters for the template.
   * @param locals Locals to pass to `invoke`. Defaults to `{ params: params }`.
   * @return {string|QPromise[string]} The template html as a string, or a promise for that string.
   */
  def fromProvider(provider: js.Function, params: js.Dictionary[js.Any] = js.Dictionary(), locals: js.Dictionary[js.Any] = js.Dictionary()): js.Any = js.native
}
