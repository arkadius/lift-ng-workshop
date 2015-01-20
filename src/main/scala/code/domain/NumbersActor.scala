package code.domain

import java.util.UUID
import java.util.concurrent.ScheduledFuture

import net.liftweb.actor.LiftActor
import net.liftweb.util._
import Helpers._

import scala.util.Random

object NumbersActor extends LiftActor {
  private var numbers: Numbers = randomNumbers

  private var scheduledFuture = Option.empty[ScheduledFuture[Unit]]

  private def randomNumbers: Numbers = {
    val values = (1 to 1 + Random.nextInt(9)).map { _ =>
      Random.nextInt(100)
    }.toList
    Numbers(values, UUID.randomUUID().toString)
  }

  override protected def messageHandler = {
    case GetNumbers =>
      reply(numbers)
    case ScheduleNext =>
      scheduledFuture.map(_.cancel(true))
      scheduledFuture = Some(Schedule.schedule(this, NextNumbers, 5.seconds))
    case NextNumbers =>
      numbers = randomNumbers
      this ! ScheduleNext
  }

  this ! ScheduleNext
}

case object GetNumbers

case class Numbers(values: List[Int], uuid: String)

case object ScheduleNext

case object NextNumbers
