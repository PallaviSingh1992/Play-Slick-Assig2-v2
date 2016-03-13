package controllers

import com.google.inject.Inject
import models.Assignment
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}
import services.AssignmentServiceApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import scala.concurrent.Future
import play.api.i18n.Messages.Implicits._
import play.api.Play.current

class AssignmentController @Inject()(service:AssignmentServiceApi) extends Controller {
  val assigForm = Form(
    mapping(
      "id" -> number,
      "name" ->nonEmptyText,
      "marks"->number(),
      "remarks"->nonEmptyText
    )(Assignment.apply)(Assignment.unapply)
  )

  def list = Action.async { implicit request =>
    val list = service.getAssignment

    list.map {
      list => Ok("" + list)
    }
  }

  def listById=Action.async { implicit request =>
    val id=request.session.get("id").get.toInt
    service.getAssignmentById(id).map{ list => Ok(views.html.assignment(list.toList, assigForm))
    }
  }


}
