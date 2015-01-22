import com.banno.license.Plugin.LicenseKeys._
import com.banno.license.Licenses._

licenseSettings

license := apache2("Copyright 2015 the original author or authors.")

name := "lift-ng-workshop"

version := "0.0.1"

organization := "org.github"

scalaVersion := "2.11.4"

resolvers ++= Seq("snapshots"     at "https://oss.sonatype.org/content/repositories/snapshots",
                  "staging"       at "https://oss.sonatype.org/content/repositories/staging",
                  "releases"      at "https://oss.sonatype.org/content/repositories/releases")

seq(webSettings :_*)

scalacOptions ++= Seq("-deprecation", "-unchecked")

libraryDependencies ++= {
  val liftVersion = "3.0-M2"
  val liftEdition = liftVersion.substring(0,3)
  val jettyVersion = "8.1.16.v20140903"
  Seq(
    "net.liftweb"       %% "lift-webkit"        % liftVersion        % "compile",
    "net.liftweb"       %% "lift-mapper"        % liftVersion        % "compile",
    "net.liftmodules"   %% "lift-jquery-module_2.6" % "2.8",
    "net.liftmodules"   %% s"ng_$liftEdition" % "0.6.2",
    "net.liftmodules"   %% s"ng-js_$liftEdition" % "0.2_1.3.8",
    "org.eclipse.jetty" % "jetty-webapp"        % jettyVersion  % "container",
    "org.eclipse.jetty" % "jetty-plus"          % jettyVersion  % "container", // For Jetty Config
    "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container" artifacts Artifact("javax.servlet", "jar", "jar"),
    "ch.qos.logback"    % "logback-classic"     % "1.0.6"
  )
}

