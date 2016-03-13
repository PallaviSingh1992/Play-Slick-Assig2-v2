package controllers

import models.User
import org.mockito.Mockito._
import org.specs2.mock.Mockito
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._
import services.UserServiceApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import scala.concurrent.Future

@RunWith(classOf[JUnitRunner])
class UserControllerSpec extends PlaySpecification with Mockito {

  "User Controller " should{

    val service=mock[UserServiceApi]
    val controller=new UserController(service)

    "list users" in new WithApplication() {

      when(service.getUser).thenReturn(Future(List(User(1,"himani","himani@knoldus.in","98765432","himani"))))

      val res=call(controller.list,FakeRequest(GET,"/list"))

      status(res) must equalTo(OK)
    }

    "add users" in new WithApplication() {

      when(service.insertUser(1,"himani","himani@knoldus.in","22510498","himani")).thenReturn(Future(1))
      val res=call(controller.add,FakeRequest(GET,"/list"))
      status(res) must equalTo(OK)
    }

    "delete users" in new WithApplication() {
      when(service.deleteUser(1)).thenReturn(Future(1))
      val res=call(controller.delete(1),FakeRequest(GET,"/list"))
      status(res) must equalTo(SEE_OTHER)
    }

    "update users" in new WithApplication() {
      when(service.updateUser(1,"himani","himani@knol.in","22598609","himani")).thenReturn(Future(1))
      val res=call(controller.update,FakeRequest(GET,"/list"))
      status(res) must equalTo(OK)
    }

  }
}