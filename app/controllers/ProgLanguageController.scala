package controllers

import com.google.inject.Inject
import models.ProgLanguage
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}
import services.ProgLanguageApi
import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.i18n.Messages.Implicits._
import play.api.Play.current

class ProgLanguageController @Inject()(service:ProgLanguageApi) extends Controller {
  val progLangForm = Form(
    mapping(
      "id" -> number,
      "name" ->nonEmptyText
    )(ProgLanguage.apply)(ProgLanguage.unapply)
  )

  def list = Action.async { implicit request =>
    val list = service.getProg()

    list.map {
      list => Ok("" + list)
    }
  }

  def listById=Action.async { implicit request =>
    val id=request.session.get("id").get.toInt
    service.getProgId(id).map{ list => Ok(views.html.proglanguage(list.toList,progLangForm))
    }
  }

  def add=Action.async{implicit request =>
    progLangForm.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => Future.successful(Ok("success")),
      data => {
        val id:Int=request.session.get("id").get.toInt
        service.insertProg(id,data.name).map { res =>
          Redirect(routes.ProgLanguageController.listById)
        }
      })
  }


  def delete(name:String) = Action.async { implicit request =>
    val id:Int=request.session.get("id").get.toInt
    service.deleteProg(name) map { res =>
      Redirect(routes.ProgLanguageController.listById)
    }
  }



  }
