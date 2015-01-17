package code 
package snippet

import net.liftweb.http._
import net.liftweb.util.Helpers._

import scala.xml.NodeSeq

class HelloWorld extends StatefulSnippet with RenderFuncDispatch {

  private val ajaxEnabled = S.param("ajax").flatMap(str => tryo(str.toBoolean)).openOr(false)

  private var start = 1

  private def numbers: Seq[String] = {
    (start to start+4).map(_.toString)
  }

  private def nextNumbers(): Unit = {
    start += 1
  }

  def render: NodeSeq => NodeSeq = SHtml.idMemoize { memoize =>
    ".row *" #> numbers.map(".number *" #> _) &
      renderSubmit(memoize)
  }

  def renderSubmit(memoize: IdMemoizeTransform) = {
    val renderedHtml =
      if (ajaxEnabled) {
        SHtml.ajaxButton("Ajax", {() =>
          nextNumbers()
          memoize.setHtml()
        })
      } else {
        SHtml.submit("Post", nextNumbers)
      }
    "type=submit" #> renderedHtml
  }
}