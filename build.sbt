organization := "cayley"
version := "0.1"
name := "cayley java driver"

javacOptions in (Compile, compile) ++= Seq("-source", "1.8", "-target", "1.8", "-g:lines")

crossPaths := false // drop off Scala suffix from artifact names.
autoScalaLibrary := false // exclude scala-library from dependencies

libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.1"
libraryDependencies += "ch.qos.logback" % "logback-core" % "1.1.2"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.2"

libraryDependencies += "com.squareup.okhttp3" % "okhttp" % "3.5.0"

libraryDependencies += "junit" % "junit" % "4.12" % "test"

