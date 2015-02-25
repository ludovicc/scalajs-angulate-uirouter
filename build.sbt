
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
      "biz.enef" %%% "scalajs-angulate" % "0.2-SNAPSHOT"
    )
  )

lazy val tests = project.
  dependsOn(root).
  enablePlugins(ScalaJSPlugin).
  settings(commonSettings: _*).
  settings(
    publish := {},
    publishLocal := {},
    scalacOptions ++= angulateDebugFlags,
    scalaJSStage := FastOptStage,
    //jsEnv in Test := PhantomJSEnv("phantomjs" , Seq("--remote-debugger-port=9000","--remote-debugger-autorun=true")).value,
    //jsEnv in Test := PhantomJSEnv("phantomjs" , Seq("--debug=true")).value,
    jsEnv in Test := PhantomJSEnv().value,
    testFrameworks += new TestFramework("utest.runner.Framework"),
    libraryDependencies += "com.lihaoyi" %%% "utest" % "0.3.0" % "test",
    jsDependencies += RuntimeDOM,
    jsDependencies += "org.webjars" % "angularjs" % "1.3.8" / "angular.min.js" % "test",
    jsDependencies += ("org.webjars" % "angularjs" % "1.3.8" / "angular-locale_en.js"  dependsOn "angular.min.js") % "test",
    //jsDependencies += "org.webjars" % "angularjs" % "1.3.8" / "angular-mocks.js" dependsOn "angular.min.js",
    jsDependencies += ("org.webjars" % "angular-ui-router" % "0.2.13" / "angular-ui-router.min.js" dependsOn "angular.min.js") % "test"
  )
