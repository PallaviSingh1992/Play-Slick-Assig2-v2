package controllers

import com.google.inject.Inject
import models.Language
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Controller, Action}
import services.LanguageServiceApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import scala.concurrent.Future
import play.api.i18n.Messages.Implicits._
import play.api.Play.current

class LanguageController @Inject()(service:LanguageServiceApi) extends Controller {

  val langForm = Form(
    mapping(
      "id" -> number,
      "name" ->nonEmptyText,
      "fluency"->nonEmptyText
    )(Language.apply)(Language.unapply)
  )

  def list = Action.async { implicit request =>
    val list = service.getLanguage()
    list.map {
      list => Ok(views.html.language(list,langForm))
    }
  }

  def listById=Action.async { implicit request =>
    val id=request.session.get("id").get.toInt
    service.getLanguageById(id).map{ list => Ok(views.html.language(list.toList, langForm))
    }
  }

  def add=Action.async{implicit request =>
    langForm.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => Future.successful(Ok("success")),
      data => {
        val id:Int=request.session.get("id").get.toInt
        service.insertLanguage(id,data.name,data.fluency).map { res =>
          Redirect(routes.LanguageController.listById)
        }
      })
  }


  def delete(name:String) = Action.async { implicit request =>
    val id:Int=request.session.get("id").get.toInt
    service.deleteLanguage(name) map { res =>
      Redirect(routes.LanguageController.listById)
    }
  }

  def update=Action.async{implicit request =>
    langForm.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => Future.successful(Ok("success")),
      data => {
        val id:Int=request.session.get("id").get.toInt
        service.updateLanguage(data.id,data.name,data.fluency).map(res =>
          Redirect(routes.LanguageController.listById)
        )
      })

  }



}
