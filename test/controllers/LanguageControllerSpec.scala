package controllers

import models.Language
import org.mockito.Mockito._
import org.specs2.mock.Mockito
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.LanguageServiceApi

import scala.concurrent.Future

@RunWith(classOf[JUnitRunner])
class LanguageControllerSpec extends PlaySpecification with Mockito {

  "Language Controller" should{

    val service=mock[LanguageServiceApi]
    val controller=new LanguageController(service)

    "list languages" in new WithApplication() {

      when(service.getLanguage()).thenReturn(Future(List(Language(1,"english","advanced"))))

      val res=call(controller.list,FakeRequest(GET,"/list"))

      status(res) must equalTo(OK)
    }

   "add language" in new WithApplication(){

     when(service.insertLanguage(1,"spanish","basic")).thenReturn(Future(1))
     val res=call(controller.add,FakeRequest(GET,"/addlang"))
     status(res) must equalTo(OK)
   }

    "list languages by id" in new WithApplication(){

      when(service.getLanguageById(1)).thenReturn(Future(List(Language(1,"english","advanced"))))
      val res=call(controller.listById,FakeRequest(GET,"/listlangid").withSession("id"->"1"))
      
      status(res) must equalTo(OK)
    }

    "update language" in new WithApplication(){

      when(service.updateLanguage(1,"spanish","basic")).thenReturn(Future(1))
      val res=call(controller.add,FakeRequest(GET,"/update"))
      status(res) must equalTo(OK)
    }

    "delete language by id" in new WithApplication() {

      when(service.deleteLanguage("scala")).thenReturn(Future(1))
      val res=call(controller.delete("scala"),FakeRequest(GET,"/deletelang"+"/scala").withSession("id"->"1"))
      status(res) must equalTo(SEE_OTHER)
    }

  }

}
