package services

import models.User
import org.mockito.Mockito._
import org.specs2.mock.Mockito
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import repository.UserRepo
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

@RunWith(classOf[JUnitRunner])
class UserServiceTest extends PlaySpecification with Mockito{

  "User Service" should {
    val service=mock[UserRepo]
    val controller=new UserService(service)

    "insert user" in new WithApplication{

      when(service.insert(1,"himani","himani@knoldus.in","98764532","himani")).thenReturn(Future(1))
      val res=controller.insertUser(1,"himani","himani@knoldus.in","98764532","himani")
      val response=Await.result(res,Duration.Inf)
      response===1
    }

    "update user" in new WithApplication() {
      when(service.update(1,"himani","himani@knoldus.in","98764532","himani")).thenReturn(Future(1))
      val res=controller.updateUser(1,"himani","himani@knoldus.in","98764532","himani")
      val response=Await.result(res,Duration.Inf)
      response===1
    }

    "delete user" in new WithApplication() {

      when(service.delete(1)).thenReturn(Future(1))
      val res=controller.deleteUser(1)
      val response=Await.result(res,Duration.Inf)
      response===1
    }

    "get user" in new WithApplication() {

      when(service.getUser("himani@knoldus.in")).thenReturn(Future(Option(User(1,"himani","himani@knoldus.in","22510269","himani"))))
      val res=controller.getUserByEmail("himani@knoldus.in")
      val response=Await.result(res,Duration.Inf)
      response===Some(User(1,"himani","himani@knoldus.in","22510269","himani"))

    }

    "get all users" in new WithApplication() {

      when(service.getAll()).thenReturn(Future(List(User(1,"himani","himani@knol.in","22468597","himani"))))
      val res=controller.getUser
      val response=Await.result(res,Duration.Inf)
      response===List(User(1,"himani","himani@knol.in","22468597","himani"))
    }
  }

}
