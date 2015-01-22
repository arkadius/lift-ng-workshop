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
package code.domain

import java.util.UUID
import java.util.concurrent.ScheduledFuture

import net.liftmodules.ng.Angular.NgModel
import net.liftweb.actor.LiftActor
import net.liftweb.http.ListenerManager
import net.liftweb.util._
import Helpers._

import scala.util.Random

object NumbersActor extends LiftActor with ListenerManager {
  private var numbers: Numbers = randomNumbers

  private var scheduledFuture = Option.empty[ScheduledFuture[Unit]]

  private def randomNumbers: Numbers = {
    val values = (1 to 1 + Random.nextInt(9)).map { _ =>
      Random.nextInt(100)
    }.toList
    Numbers(values, UUID.randomUUID().toString)
  }

  override protected def lowPriority: PartialFunction[Any, Unit] = {
    case GetNumbers =>
      reply(numbers)
    case ScheduleNext =>
      scheduledFuture.map(_.cancel(true))
      scheduledFuture = Some(Schedule.schedule(this, NextNumbers, 5.seconds))
    case NextNumbers =>
      numbers = randomNumbers
      updateListeners()
      this ! ScheduleNext
      reply(Unit)
  }

  this ! ScheduleNext

  override protected def createUpdate: Any = {
    numbers
  }
}

case object GetNumbers

case class Numbers(values: List[Int], uuid: String) extends NgModel

object Numbers {
  def zero: Numbers = Numbers(Nil, "")
}

case object ScheduleNext

case object NextNumbers
