# CirceJodaDateTimeHelper

Scala helper class to allow implicit encoding/decode Joda DateTime using io.circe.

Without this, if your case class defines an element as org.joda.time.DateTime, you'll get compile errors such as:
Error:(x,y) could not find implicit value for evidence parameter of type io.circe.Decoder[...]

Error:(x,y) not enough arguments for method decode: (implicit evidence$1: io.circe.Decoder[...])...
Unspecified value parameter evidence$1.

Why is this needed?
io.circe doesn't seem to have encoder/decoder for joda time out of the box.
