package services

import models.Language
import org.mockito.Mockito._
import org.specs2.mock.Mockito
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import repository.LanguageRepo
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

@RunWith(classOf[JUnitRunner])
class LanguageServiceTest  extends PlaySpecification with Mockito{

  "Language Service" should {
    val service=mock[LanguageRepo]
    val controller=new LanguageService(service)

    "get language"in new WithApplication() {

      when(service.getAll).thenReturn(Future(List(Language(1,"english","advanced"))))
      val res=controller.getLanguage()
      val response=Await.result(res,Duration.Inf)
      response===List(Language(1,"english","advanced"))
    }

    "insert language"in new WithApplication() {

      when(service.insert(1,"english","advanced")).thenReturn(Future(1))
      val res=controller.insertLanguage(1,"english","advanced")
      val response=Await.result(res,Duration.Inf)
      response===1
    }

    "update language" in new WithApplication() {

      when(service.update(1,"english","advanced")).thenReturn(Future(1))
      val res=controller.updateLanguage(1,"english","advanced")
      val response=Await.result(res,Duration.Inf)
      response===1
    }

    "delete language"in new WithApplication() {
      when(service.delete("english")).thenReturn(Future(1))
      val res=controller.deleteLanguage("english")
      val response=Await.result(res,Duration.Inf)
      response===1
    }

    "get language by id"in new WithApplication() {
      when(service.getLanguage(1)).thenReturn(Future(List(Language(1,"english","advanced"))))
      val res=controller.getLanguageById(1)
      val response=Await.result(res,Duration.Inf)
      response===List(Language(1,"english","advanced"))
    }

  }

}
