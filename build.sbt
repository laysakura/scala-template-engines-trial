lazy val commonSettings = Seq(
  organization := "com.github.laysakura",
  scalaVersion := "2.11.8",
  scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-Xlint")
)

lazy val versions = new {
  val scalate = "1.8.0"
  val freemarker = "2.3.25-incubating"
}

lazy val root = (project in file(".")).
  settings(commonSettings:_*).
  settings(
    libraryDependencies ++= Seq(
      "org.scalatra.scalate" %% "scalate-core" % versions.scalate,
      "org.freemarker" % "freemarker" % versions.freemarker
    )
  )
