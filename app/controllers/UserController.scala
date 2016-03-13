package controllers

import com.google.inject.Inject
import models.User
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}
import services.UserServiceApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future

class UserController @Inject()(service:UserServiceApi) extends Controller {

  val userForm = Form(
    mapping(
      "id" -> number,
      "name" -> nonEmptyText,
      "email" -> nonEmptyText,
      "mobile" -> nonEmptyText,
      "password" -> nonEmptyText
    )(User.apply)(User.unapply)
  )

  def list = Action.async { implicit request =>
    val list = service.getUser
    list.map {
      list => Ok("" + list)
    }
  }
  /*def listById(id:Int)=Action.async{implicit request=>
    service.getUser.map {
      list => Ok("" + list.filter(_.id==id))
    }
  }*/


  def add = Action.async { implicit request =>
    userForm.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => Future.successful(Ok("success")),
      data => {
        service.insertUser(data.id, data.name, data.email, data.mobile, data.password).map(res =>
          Redirect(routes.UserController.list())

        )
      })
  }

  def delete(id: Int) = Action.async { implicit request =>
    service.deleteUser(id) map { res =>
      Redirect(routes.UserController.list())
    }
  }

  def update = Action.async { implicit request =>
    userForm.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => Future.successful(Ok("success")),
      data => {
        service.updateUser(data.id, data.name, data.email, data.mobile, data.password).map(res =>
          Redirect(routes.UserController.list())

        )
      })
  }
}