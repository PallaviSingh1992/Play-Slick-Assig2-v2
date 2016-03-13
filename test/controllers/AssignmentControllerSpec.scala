package controllers

import models.{Assignment, User}
import org.mockito.Mockito._
import org.specs2.mock.Mockito
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._
import services.AssignmentServiceApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import scala.concurrent.Future

@RunWith(classOf[JUnitRunner])
class AssignmentControllerSpec extends PlaySpecification with Mockito {

  "Assignment controller" should {

    val service=mock[AssignmentServiceApi]
    val controller=new AssignmentController(service)

    "list all records" in new WithApplication() {

      when(service.getAssignment).thenReturn(Future(List(Assignment(1,"scala",7,"average"))))

      val res=call(controller.list,FakeRequest(GET,"/listassign"))

      status(res) must equalTo(OK)
    }

    "list records by id " in new WithApplication{

      when(service.getAssignmentById(1)).thenReturn(Future(List(Assignment(1,"scala",7,"average"))))
      val res=call(controller.listById,FakeRequest(GET,"/listassigid").withSession("id"->"1"))

      status(res) must equalTo(OK)
    }


  }

}
