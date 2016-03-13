package services

import models.Assignment
import org.mockito.Mockito._
import org.specs2.mock.Mockito
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import repository.AssignmentRepo
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

@RunWith(classOf[JUnitRunner])
class AssignmentServiceTest extends PlaySpecification with Mockito{

  "Assignment Service" should{

    val service=mock[AssignmentRepo]
    val controller=new AssignmentService(service)

    "get records" in new WithApplication() {

      when(service.getAll()).thenReturn(Future(List(Assignment(1,"scala",7,"average"))))
      val res=controller.getAssignment
      val response=Await.result(res,Duration.Inf)
      response===List(Assignment(1,"scala",7,"average"))
    }

    "insert records" in new WithApplication() {
      when(service.insert(1,"scala",7,"average")).thenReturn(Future(1))
      val res=controller.insertAssignment(1,"scala",7,"average")
      val response=Await.result(res,Duration.Inf)
      response===1
    }

    "update records" in new WithApplication() {

      when(service.update(1,"scala",7,"average")).thenReturn(Future(1))
      val res=controller.updateAssignment(1,"scala",7,"average")
      val response=Await.result(res,Duration.Inf)
      response===1
    }

    "delete records" in new WithApplication() {
      when(service.delete(1)).thenReturn(Future(1))
      val res=controller.deleteAssignment(1)
      val response=Await.result(res,Duration.Inf)
      response===1
    }


    "get records by id" in new WithApplication() {

      when(service.getAssignment(1)).thenReturn(Future(List(Assignment(1,"scala",7,"average"))))
      val res=controller.getAssignmentById(1)
      val response=Await.result(res,Duration.Inf)
      response===List(Assignment(1,"scala",7,"average"))
    }
  }

}
