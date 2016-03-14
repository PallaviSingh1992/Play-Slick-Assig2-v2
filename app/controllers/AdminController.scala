package controllers

import com.google.inject.Inject
import play.api.mvc.{Action, Controller}
import services.{AssignmentServiceApi, AdminServiceApi}
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.i18n.Messages.Implicits._
import play.api.Play.current


class AdminController @Inject()(admin:AdminServiceApi,service:AssignmentServiceApi) extends Controller{

  val list=admin.getAllRecords


  def listAll=Action.async { implicit request =>
    val id=request.session.get("id").get.toInt
    service.getAssignmentById(id).map{ list =>
      Ok(views.html.assignmentdis(list.toList))
    }
  }

  def details=Action.async { implicit request =>
    val id = request.session.get("id").get.toInt
    admin.getAllRecords.map(res=> Ok(views.html.admindetails(res.toList)))
    }
  }

