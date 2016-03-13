package services

import com.google.inject.{ImplementedBy,Inject}
import models.Language
import repository.LanguageRepo

import scala.concurrent.Future

@ImplementedBy(classOf[LanguageService])
trait LanguageServiceApi {

  def insertLanguage(id: Int, name: String, fluency: String): Future[Int]
  def updateLanguage(id: Int, name: String, fluency: String): Future[Int]
  def deleteLanguage(name: String): Future[Int]
  def getLanguage(): Future[List[Language]]
  def getLanguageById(id:Int):Future[Seq[Language]]
}

class LanguageService @Inject()(language: LanguageRepo) extends LanguageServiceApi{

  def insertLanguage(id: Int, name: String, fluency: String): Future[Int] = {
    language.insert(id, name, fluency)
  }

  def updateLanguage(id: Int, name: String, fluency: String): Future[Int] = {
    language.update(id, name, fluency)
  }

  def deleteLanguage(name:String): Future[Int] = {
    language.delete(name)
  }

  def getLanguage(): Future[List[Language]] = {
    language.getAll()
  }

  def getLanguageById(id:Int):Future[Seq[Language]]={
    language.getLanguage(id)
  }
}
