/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package code
package snippet

import code.domain.{NextNumbers, Numbers, GetNumbers, NumbersActor}
import net.liftweb.http._
import net.liftweb.util.Helpers._

import scala.xml.NodeSeq

class ServerSideTemplating extends StatefulSnippet with RenderFuncDispatch {

  private val ajaxEnabled = S.param("ajax").flatMap(str => tryo(str.toBoolean)).openOr(false)

  def render: NodeSeq => NodeSeq = SHtml.idMemoize { memoize =>
    ".row *" #> numbers &
      renderSubmit(memoize)
  }

  def renderSubmit(memoize: IdMemoizeTransform) = {
    val renderedHtml =
      if (ajaxEnabled) {
        SHtml.ajaxButton("Ajax", { () =>
          nextNumbers()
          memoize.setHtml()
        })
      } else {
        SHtml.submit("Post", nextNumbers)
      }
    "type=submit" #> renderedHtml
  }

  private def numbers: Seq[Int] = {
    (NumbersActor !? GetNumbers).asInstanceOf[Numbers].values
  }

  private def nextNumbers(): Unit = {
    NumbersActor !? NextNumbers
  }

}