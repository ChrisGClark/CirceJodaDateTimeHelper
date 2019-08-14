package org.circeJodaHelper

import io.circe.Decoder.Result
import io.circe._
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

import scala.util.{Failure, Success, Try}

trait CirceJodaHelper {
    def datePattern: String = "yyyy-MM-dd"
    
    implicit val dateFormat: Encoder[Option[DateTime]] with Decoder[Option[DateTime]] =
      new Encoder[Option[DateTime]] with Decoder[Option[DateTime]] {
        override def apply(a: Option[DateTime]): Json =
          Encoder.encodeString(DateTimeFormat.forPattern(datePattern).print(a.get))

        def decodeDate(s: String): Option[DateTime] =
          Try(DateTime.parse(s, DateTimeFormat.forPattern(datePattern))) match {
            case Failure(_) => None
            case Success(s) => Some(s)
          }
        override def apply(c: HCursor): Result[Option[DateTime]] =
          Decoder.decodeString.map(s => decodeDate(s)).apply(c)
      }
}
