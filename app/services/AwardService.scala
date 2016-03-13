package services


import com.google.inject.{ImplementedBy, Inject}
import models.Award
import repository.AwardRepo

import scala.concurrent.Future

@ImplementedBy(classOf[AwardService])
trait AwardServiceApi{
  def insertAward(id:Int,name:String,details:String):Future[Int]
  def updateAward(id:Int,name:String,details:String):Future[Int]
  def deleteAward(name:String):Future[Int]
  def getAward:Future[List[Award]]
  def getAwardById(id:Int):Future[Seq[Award]]

}

class AwardService @Inject()(award:AwardRepo) extends AwardServiceApi{

  def insertAward(id:Int,name:String,details:String):Future[Int]={
    award.insert(id,name,details)
  }

  def updateAward(id:Int,name:String,details:String):Future[Int]={
    award.update(id,name,details)
  }

  def deleteAward(name:String):Future[Int]={
    award.delete(name)
  }

  def getAward:Future[List[Award]]={
    award.getAll()
  }

  def getAwardById(id:Int):Future[Seq[Award]]={
    award.getAward(id)
  }

}
