import sbt.Keys._
import sbt.Project.projectToRef
import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

// a special crossProject for configuring a JS/JVM/shared structure
lazy val shared = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .settings(
    scalaVersion := Settings.versions.scala,
    libraryDependencies ++= Settings.sharedDependencies.value
  )
  // set up settings specific to the JS project
  .jsConfigure(_ enablePlugins ScalaJSWeb)

// use eliding to drop some debug code in the production build
lazy val elideOptions = settingKey[Seq[String]]("Set limit for elidable functions")

// instantiate the JS project for SBT with some additional settings
lazy val client: Project = (project in file("client"))
  .settings(
    name := "client",
    version := Settings.version,
    scalaVersion := Settings.versions.scala,
    scalacOptions ++= Settings.scalacOptions,
    libraryDependencies ++= Settings.scalajsDependencies.value,
    // by default we do development build, no eliding
    elideOptions := Seq(),
    scalacOptions ++= elideOptions.value,
    npmDevDependencies in Compile ++= Settings.npmDevDependencies.value,
    npmDependencies in Compile ++= Settings.npmDependencies.value,
    webpackResources := baseDirectory.value / "webpacks" * "*",
    webpackConfigFile in fastOptJS := Some(baseDirectory.value / "webpacks" / "dev.webpack.config.js"),
    // use Scala.js provided launcher code to start the client app
    scalaJSUseMainModuleInitializer := true,
    scalaJSUseMainModuleInitializer in Test := false,
    // use uTest framework for tests
    testFrameworks += new TestFramework("utest.runner.Framework")
  )
  .enablePlugins(ScalaJSPlugin, ScalaJSWeb, ScalaJSBundlerPlugin)
  .dependsOn(shared.js)

// Client projects (just one in this case)
lazy val clients = Seq(client)

// instantiate the JVM project for SBT with some additional settings
lazy val server = (project in file("server"))
  .settings(
    name := "server",
    version := Settings.version,
    scalaVersion := Settings.versions.scala,
    scalacOptions ++= Settings.scalacOptions,
    libraryDependencies ++= Settings.jvmDependencies.value,
    commands += ReleaseCmd,
    // triggers scalaJSPipeline when using compile or continuous compilation
    compile in Compile := ((compile in Compile) dependsOn scalaJSPipeline).value,
    // connect to the client project
    scalaJSProjects := clients,
    pipelineStages in Assets := Seq(scalaJSPipeline),
    pipelineStages := Seq(digest, gzip),
    // compress CSS
    LessKeys.compress in Assets := true
  )
  .enablePlugins(PlayScala, WebScalaJSBundlerPlugin)
  .disablePlugins(PlayLayoutPlugin) // use the standard directory layout instead of Play's custom
  .aggregate(clients.map(projectToRef): _*)
  .dependsOn(shared.jvm)

// Command for building a release
lazy val ReleaseCmd = Command.command("release") {
  state => "set elideOptions in client := Seq(\"-Xelide-below\", \"WARNING\")" ::
    "client/clean" ::
    "client/test" ::
    "server/clean" ::
    "server/test" ::
    "server/dist" ::
    "set elideOptions in client := Seq()" ::
    state
}

// lazy val root = (project in file(".")).aggregate(client, server)

// loads the Play server project at sbt startup
onLoad in Global := (Command.process("project server", _: State)) compose (onLoad in Global).value
