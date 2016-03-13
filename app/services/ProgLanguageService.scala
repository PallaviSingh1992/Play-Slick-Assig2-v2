package services

import com.google.inject.{ImplementedBy, Inject}
import models.ProgLanguage
import repository.ProgLanguageRepo

import scala.concurrent.Future

@ImplementedBy(classOf[ProgLanguageService])
trait ProgLanguageApi{
  def insertProg(id:Int,name:String): Future[Int]
  def updateProg(id:Int,name:String): Future[Int]
  def deleteProg(name:String):Future[Int]
  def getProg():Future[List[ProgLanguage]]
  def getProgId(id:Int):Future[Seq[ProgLanguage]]
}

class ProgLanguageService @Inject()(progLanguage:ProgLanguageRepo) extends ProgLanguageApi {

  def insertProg(id:Int,name:String): Future[Int]={
    progLanguage.insert(id,name)
  }

  def updateProg(id:Int,name:String): Future[Int]={
    progLanguage.update(id,name)
  }

  def deleteProg(name:String):Future[Int]={
    progLanguage.delete(name)
  }

  def getProg():Future[List[ProgLanguage]]={
    progLanguage.getAll()
  }

  def getProgId(id:Int):Future[Seq[ProgLanguage]]={
    progLanguage.getProgLanguage(id)
  }

}
