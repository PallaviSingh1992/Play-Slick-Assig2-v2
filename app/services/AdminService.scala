package services

import com.google.inject.{ImplementedBy, Inject}
import models.User
import slick.lifted.Query

import scala.concurrent.Future

@ImplementedBy(classOf[AdminService])
trait AdminServiceApi{
  def getAllRecords:Future[List[User]]
  //def getAllRecords():Future[Seq[(Int,String,String,String,String,String,String)]]
}
class AdminService @Inject()(admin:AdminRepo) extends AdminServiceApi{
//  def getAllRecords():Future[Seq[(Int,String,String,String,String,String,String)]]={
//    admin.getAll().map(res=>res)
//  }
  def getAllRecords:Future[List[User]]={
   admin.getAll()
  }
}
