
name := "scalajs-angulate-uirouter"

val commonSettings = Seq(
  organization := "angulate",
  version := "0.1-SNAPSHOT",
  scalaVersion := "2.11.5",
  scalacOptions ++= Seq("-deprecation","-feature","-Xlint"),
  resolvers += "karchedon-repo" at "http://maven.karchedon.de/"
)

val angulateDebugFlags = Seq(
  "runtimeLogging",
  "ModuleMacros.debug",
  "ControllerMacros.debug"
  //"DirectiveMacros.debug"
  //"ServiceMacros.debug"
  //"HttpPromiseMacros.debug"
).map( f => s"-Xmacro-settings:biz.enef.angular.$f" )

lazy val root = project.in(file(".")).
  enablePlugins(ScalaJSPlugin).
  settings(commonSettings: _*).
  //settings(publishingSettings: _*).
  //settings(sonatypeSettings: _*).
  settings(
    name := "scalajs-angulate-uirouter",
    normalizedName := "scalajs-angulate-uirouter",
    scalacOptions ++= angulateDebugFlags,
    libraryDependencies ++= Seq(
      "biz.enef" %%% "scalajs-angulate" % "0.1"
    )
  )

lazy val tests = project.
  dependsOn(root).
  enablePlugins(ScalaJSPlugin).
  settings(commonSettings: _*).
  settings(utest.jsrunner.Plugin.utestJsSettings: _*).
  settings(
    publish := {},
    scalacOptions ++= angulateDebugFlags,
    scalaJSStage := FastOptStage,
    jsDependencies += RuntimeDOM,
    jsDependencies += "org.webjars" % "angularjs" % "1.3.8" / "angular.min.js" % "test",
    jsDependencies += "org.webjars" % "angularjs" % "1.3.8" / "angular-mocks.js" % "test",
    jsDependencies += "org.webjars" % "angular-ui-router" % "0.2.13" / "angular-ui-router.min.js" % "test"
  )
