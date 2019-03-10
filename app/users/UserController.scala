package users

import scala.concurrent.ExecutionContext.Implicits.global

import io.circe.generic.extras.Configuration
import io.circe.generic.extras.auto._
import play.api.libs.circe.Circe
import play.api.mvc._
import users.service.UserCrud

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */

class UserController(cc: ControllerComponents, userCrud: UserCrud) extends AbstractController(cc) with Circe {
  implicit val customConfig: Configuration = Configuration.default.withSnakeCaseMemberNames.withDefaults.withDiscriminator("type")

  def create: Action[User] = Action.async(circe.tolerantJson[User])({
    request =>
      userCrud.addUser(request.body).map(
        str => Results.Created(str)
      )
  }
  )

  def healthCheck: Action[AnyContent] = Action {
    _ => Ok("good")
  }

}
