name := "ChessVAP"

version := "1"

scalaVersion := "2.12.8"

sbtVersion:= "1.2.8"

resolvers+= "jitpack.io" at "https://jitpack.io"

// https://mvnrepository.com/artifact/log4j/log4j
libraryDependencies += "log4j" % "log4j" % "1.2.17"

// https://mvnrepository.com/artifact/junit/junit
libraryDependencies += "junit" % "junit" % "4.12" % Test

// https://mvnrepository.com/artifact/org.hamcrest/hamcrest-core
libraryDependencies += "org.hamcrest" % "hamcrest-core" % "2.1" % Test

// https://mvnrepository.com/artifact/org.scalatest/scalatest
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test

// https://mvnrepository.com/artifact/org.scalamock/scalamock
libraryDependencies += "org.scalamock" %% "scalamock" % "4.1.0" % Test

// https://mvnrepository.com/artifact/com.typesafe/config
libraryDependencies += "com.typesafe" % "config" % "1.2.1"

// https://mvnrepository.com/artifact/org.scala-lang.modules/scala-xml
libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.1.1"

// https://mvnrepository.com/artifact/org.jdesktop/swing-worker
libraryDependencies += "org.jdesktop" % "swing-worker" % "1.1"

// https://mvnrepository.com/artifact/org.jdesktop/appframework
libraryDependencies += "org.jdesktop" % "appframework" % "1.0.3"

// https://mvnrepository.com/artifact/commons-io/commons-io
libraryDependencies += "commons-io" % "commons-io" % "2.6"

// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-configuration-processor
libraryDependencies += "org.springframework.boot" % "spring-boot-configuration-processor" % "2.1.3.RELEASE"

// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web
libraryDependencies += "org.springframework.boot" % "spring-boot-starter-web" % "2.1.3.RELEASE"

// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-actuator
libraryDependencies += "org.springframework.boot" % "spring-boot-starter-actuator" % "2.1.3.RELEASE"

// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test
libraryDependencies += "org.springframework.boot" % "spring-boot-starter-test" % "2.1.3.RELEASE" % Test

// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test
libraryDependencies += "org.springframework.boot" % "spring-boot-starter-test" % "2.1.3.RELEASE" % Test

// https://mvnrepository.com/artifact/org.codehaus.plexus/plexus-utils
libraryDependencies += "org.codehaus.plexus" % "plexus-utils" % "3.0.22"

Compile / unmanagedJars := (baseDirectory.value ** "*.jar").classpath

enablePlugins(JavaAppPackaging, AshScriptPlugin, DockerPlugin)

//mainClass in (Compile, run) := Some("pl.art.lach.mateusz.javaopenchess.JChessApp")
mainClass in (Compile, run) := Some("CustomApplication")

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}


assemblyJarName in assembly := "chess-spring.jar"

test in assembly := {}

// the Docker image to base on (alpine is smaller than the debian based one (120 vs 650 MB)
dockerBaseImage := "openjdk:8-jre-alpine"

// creates tag 'latest' as well when publishing
dockerUpdateLatest := true
dockerExposedPorts ++= Seq(8080)

scalacOptions += "-target:jvm-1.8"
javacOptions in (Compile, compile) ++= Seq("-source", "1.8", "-target", "1.8", "-g:lines")