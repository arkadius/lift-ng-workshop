package code.api

import net.liftweb.http.rest.RestHelper
import net.liftweb.json.JsonDSL._
import net.liftweb.json._

class Routes extends RestHelper {
 serve {
   case "api" :: "numbers" :: Nil JsonGet req =>
     List(3, 4, 5): JValue
 }
}
