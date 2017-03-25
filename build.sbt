import java.time.LocalDate

import de.heikoseeberger.sbtheader.license.Apache2_0

sbtPlugin := true

val compilerSettings = Seq (
  scalaVersion := "2.10.6",
  crossScalaVersions := Seq("2.10.6", "2.11.8", "2.12.1"),
  sourcesInBase := false,
  crossPaths := false,
  scalacOptions ++= Seq(
    "-unchecked",
    "-Xlint",
    "-deprecation",
    "-Xfatal-warnings",
    "-feature",
    "-encoding",
    "UTF-8"
  )
)

val bintraySettings = Seq(
  bintrayOrganization in bintray := None,
  bintrayPackageLabels := Seq("sbt", "sbt-plugin", "sbt-sassify"),
  bintrayRepository := "sbt-plugins",
  bintrayReleaseOnPublish in ThisBuild := false,
  publishMavenStyle := false,
  licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html")),
  publish := publish dependsOn (test in Test)
)

val copyrightSettings =
  headers := Map(
    "scala" -> Apache2_0(LocalDate.now().getYear.toString, "Han van Venrooij"),
    "java" -> Apache2_0(LocalDate.now().getYear.toString, "Han van Venrooij")
  )

lazy val testScalastyle = taskKey[Unit]("testScalastyle")
val scalaStyleSettings = Seq(
  testScalastyle := org.scalastyle.sbt.ScalastylePlugin.scalastyle.in(Compile).toTask("").value,
  org.scalastyle.sbt.ScalastylePlugin.scalastyleFailOnError := true,
  test := test in Test dependsOn testScalastyle
)

lazy val root = project.in(file(".")).aggregate(plugin, sassify)

lazy val plugin = project
    .in(file("sbt-plugin"))
    .dependsOn(sassify)
    .enablePlugins(AutomateHeaderPlugin)
    .enablePlugins(GitVersioning)
    .settings(compilerSettings)
    .settings(bintraySettings)
    .settings(copyrightSettings)
    .settings(scalaStyleSettings)

lazy val sassify = project.in(file("sassify"))
    .enablePlugins(AutomateHeaderPlugin)
    .settings(compilerSettings)
    .settings(copyrightSettings)
    .settings(scalaStyleSettings)
    .settings(git.gitUncommittedChanges := false)