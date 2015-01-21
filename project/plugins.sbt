resolvers += Resolver.url(
  "bintray-sbt-plugin-releases",
  url("http://dl.bintray.com/banno/oss"))(
    Resolver.ivyStylePatterns)

//Enable the sbt web plugin
addSbtPlugin("com.earldouglas" % "xsbt-web-plugin" % "0.7.0")

//Enable the sbt idea plugin
addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.6.0")

//Enable the sbt eclipse plugin
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.4.0")

addSbtPlugin("com.banno" % "sbt-license-plugin" % "0.1.4")