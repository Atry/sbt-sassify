import sbt._

name := "sbt-sassify"
organization := "org.irundaia.sbt"
sbtPlugin := true

fork in Test := true

addSbtPlugin("com.typesafe.sbt" % "sbt-web" % "1.4.0")


ScriptedPlugin.scriptedSettings ++ Seq(
  ScriptedPlugin.scriptedBufferLog := false,
  ScriptedPlugin.scriptedLaunchOpts <+= version { "-Dplugin.version=" + _ }
)