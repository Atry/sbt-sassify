name := "sassify"
organization := "org.irundaia"

fork in Test := true

javaOptions += "-Djna.nosys=true"

libraryDependencies ++= Seq(
  "net.java.dev.jna" % "jna" % "4.2.2",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)
