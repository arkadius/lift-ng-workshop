package code.api

import code.domain.{GetNumbers, NextNumbers, NumbersActor}
import net.liftweb.common.Empty
import net.liftweb.http.rest.RestHelper

class Routes extends RestHelper {
 serveJxa {
   case JsonGet("api" :: "numbers" :: Nil, _) =>
     NumbersActor !? GetNumbers
   case Get("api" :: "numbers" :: "next" :: Nil, _) =>
     NumbersActor ! NextNumbers
     Empty
 }
}
