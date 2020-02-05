import sbt._
import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport._
import play.sbt.PlayImport

/**
 * Application settings. Configure the build for your application here.
 * You normally don't have to touch the actual build definition after this.
 */
object Settings {

  /** The name of your application */
  val name = "scalajs-spa"

  /** The version of your application */
  val version = "1.1.5"

  /** Options for the scala compiler */
  val scalacOptions = Seq(
    "-Xlint",
    "-unchecked",
    "-deprecation",
    "-feature"
  )

  /** Declare global dependency versions here to avoid mismatches in multi part dependencies */
  object versions {
    val autowire = "0.2.6"
    val booPickle = "1.3.0"
    val bootstrap = "3.3.6"
    val chartjs = "2.1.3"
    val diode = "1.1.6"
    val diodeReact = "1.1.6.142"
    val exposeLoader = "0.7.5"
    val importsLoader = "0.8.0"
    val jQuery = "1.11.1"
    val log4js = "1.4.15"
    val react = "16.7.0"
    val reactBootstrap = "0.33.1"
    val scala = "2.12.10"
    val scalaCSS = "0.5.3"
    val scalaDom = "0.9.6"
    val scalajsReact = "1.4.2"
    val scalajsScripts = "1.1.4"
    val uTest = "0.4.7"
    val webpackMerge = "4.1.2"
  }

  /**
   * These dependencies are shared between JS and JVM projects
   * the special %%% function selects the correct version for each project
   */
  val sharedDependencies = Def.setting(Seq(
    "com.lihaoyi" %%% "autowire" % versions.autowire,
    "io.suzaku" %%% "boopickle" % versions.booPickle
  ))

  /** Dependencies only used by the JVM project */
  val jvmDependencies = Def.setting(Seq(
    PlayImport.guice,
    "com.vmunier" %% "scalajs-scripts" % versions.scalajsScripts,
    "org.webjars" % "font-awesome" % "4.3.0-1" % Provided,
    "org.webjars" % "bootstrap" % versions.bootstrap % Provided,
    "com.lihaoyi" %% "utest" % versions.uTest % Test
  ))

  /** Dependencies only used by the JS project (note the use of %%% instead of %%) */
  val scalajsDependencies = Def.setting(Seq(
    "com.github.japgolly.scalajs-react" %%% "core" % versions.scalajsReact,
    "com.github.japgolly.scalajs-react" %%% "extra" % versions.scalajsReact,
    "com.github.japgolly.scalacss" %%% "ext-react" % versions.scalaCSS,
    "io.suzaku" %%% "diode" % versions.diode,
    "io.suzaku" %%% "diode-react" % versions.diodeReact,
    "org.scala-js" %%% "scalajs-dom" % versions.scalaDom,
    "com.lihaoyi" %%% "utest" % versions.uTest % Test
  ))

  val npmDevDependencies = Def.setting(Seq(
    "webpack-merge" -> Settings.versions.webpackMerge,
    "imports-loader" -> Settings.versions.importsLoader,
    "expose-loader" -> Settings.versions.exposeLoader
  ))

  val npmDependencies = Def.setting(Seq(
    "react" -> versions.react,
    "react-dom" -> versions.react,
    "react-bootstrap" -> versions.reactBootstrap,
    "jquery" -> versions.jQuery,
    "bootstrap" -> versions.bootstrap,
    "chart.js" -> versions.chartjs,
    "log4javascript" -> versions.log4js,
  ))
}
