package services


import com.google.inject.{ImplementedBy, Inject}
import models.User
import repository.UserRepo

import scala.concurrent.Future

@ImplementedBy(classOf[UserService])
trait UserServiceApi{
  def insertUser(id:Int,name:String,email:String,mobile:String,password:String):Future[Int]
  def updateUser(id:Int,name:String,email:String,mobile:String,password:String):Future[Int]
  def deleteUser(id:Int):Future[Int]
  def getUser:Future[List[User]]
  def getUserByEmail(email:String):Future[Option[User]]
}

class UserService @Inject()(user:UserRepo) extends UserServiceApi{

  def insertUser(id:Int,name:String,email:String,mobile:String,password:String):Future[Int]={
    user.insert(id,name,email,mobile,password)
  }

  def updateUser(id:Int,name:String,email:String,mobile:String,password:String):Future[Int]={
    user.update(id,name,email,mobile,password  )
  }

  def deleteUser(id:Int):Future[Int]={
    user.delete(id)
  }

  def getUser:Future[List[User]]={
    user.getAll()
  }

 def getUserByEmail(email:String):Future[Option[User]]={
   user.getUser(email)
 }
}