package controllers

import models.ProgLanguage
import org.mockito.Mockito._
import org.specs2.mock.Mockito
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.ProgLanguageApi

import scala.concurrent.Future

@RunWith(classOf[JUnitRunner])
class ProgLanguageControllerSpec extends PlaySpecification with Mockito {


  "Prog Language Controller" should {

    val service = mock[ProgLanguageApi]
    val controller = new ProgLanguageController(service)

    "list languages" in new WithApplication() {

      when(service.getProg()).thenReturn(Future(List(ProgLanguage(1,"scala"))))

      val res = call(controller.list, FakeRequest(GET, "/listprog"))
      status(res) must equalTo(OK)
    }

    "list languages by id" in new WithApplication() {
      when(service.getProgId(1)).thenReturn(Future(List(ProgLanguage(1,"scala"))))
      val res = call(controller.listById, FakeRequest(GET, "/listprog").withSession("id"->"1"))
      status(res) must equalTo(OK)
    }

    "add new language" in new WithApplication() {
      when(service.insertProg(1,"scala")).thenReturn(Future(1))
      val res=call(controller.add,FakeRequest(GET,"/add").withSession("id"->"1"))
      status(res) must equalTo(OK)
    }

    "delete record by id" in new WithApplication() {

      when(service.deleteProg("scala")).thenReturn(Future(1))
      val res=call(controller.delete("scala"),FakeRequest(GET,"/deleteprog"+"/scala").withSession("id"->"1"))
      status(res) must equalTo(SEE_OTHER)
    }
  }
}
