package controllers

import models.{Award, User}
import org.mockito.Mockito._
import org.specs2.mock.Mockito
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._
import services.AwardServiceApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future

@RunWith(classOf[JUnitRunner])
class AwardControllerSpec extends  PlaySpecification with Mockito{
      sequential
  "Award Controller" should{
    val service=mock[AwardServiceApi]
    val controller=new AwardController(service)

    "list all awards" in new WithApplication() {

      when(service.getAward).thenReturn(Future(List(Award(1,"best coder","zonal level"))))
      val res=call(controller.list,FakeRequest(GET,"/list"))
      status(res) must equalTo(OK)
    }

    "add awards" in new WithApplication() {

      when(service.insertAward(1,"best speaker","zonal level")).thenReturn(Future(1))
      val res=call(controller.add,FakeRequest(GET,"/addaward").withSession("id"->"1"))
      status(res) must equalTo(OK)
    }

    "add awards with correct form data" in new WithApplication() {

      when(service.insertAward(1,"best speaker","zonal level")).thenReturn(Future(1))
      val res=call(controller.add,FakeRequest(GET,"/addaward").withFormUrlEncodedBody("id"->"1","name"->"best speaker","details"->"school level").withSession("id"->"1"))
      status(res) must equalTo(OK)
    }

    "Update awards" in new WithApplication() {

      when(service.updateAward(1,"best speaker","zonal")).thenReturn(Future(1))
      val res=call(controller.update,FakeRequest(GET,"/update").withFormUrlEncodedBody("id"->"1","name"->"best speaker","details"->"school level"))
      status(res) must equalTo(OK)
    }

    "Update awards with correct form date" in new WithApplication() {

      when(service.updateAward(1,"best speaker","zonal")).thenReturn(Future(1))
      val res=call(controller.update,FakeRequest(GET,"/update").withFormUrlEncodedBody("id"->"1","name"->"best speaker","details"->"zonal").withSession("id"->"1"))
      status(res) must equalTo(OK)
    }

    "list awards by id" in new WithApplication() {

      when(service.getAwardById(1)).thenReturn(Future(List(Award(1,"best coder","zonal level"))))
      val res=call(controller.listById,FakeRequest(GET,"/listbyid").withFormUrlEncodedBody("id"->"1","name"->"best coder","details"->"zonal level").withSession("id"->"1"))
      status(res) must equalTo(OK)
    }

    "delete award by id" in new WithApplication() {

      when(service.deleteAward("best coder")).thenReturn(Future(1))
      val res=call(controller.delete("best coder"),FakeRequest(GET,"/deleteaward"+"/best coder").withSession("id"->"1"))
      status(res) must equalTo(SEE_OTHER)
    }
  }
}

