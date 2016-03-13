package services

import models.ProgLanguage
import org.mockito.Mockito._
import org.specs2.mock.Mockito
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import repository.ProgLanguageRepo
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

@RunWith(classOf[JUnitRunner])
class ProgLanguageServiceTest extends PlaySpecification with Mockito {

  "Programming language Service" should {

    val service=mock[ProgLanguageRepo]
    val controller=new ProgLanguageService(service)

    "get records"in new WithApplication() {

      when(service.getAll()).thenReturn(Future(List(ProgLanguage(1,"scala"))))
        val res=controller.getProg()
        val response=Await.result(res,Duration.Inf)
        response===List(ProgLanguage(1,"scala"))
    }

    "get records by id" in new WithApplication{
      when(service.getProgLanguage(1)).thenReturn(Future(List(ProgLanguage(1,"scala"))))
      val res=controller.getProgId(1)
      val response=Await.result(res,Duration.Inf)
      response===List(ProgLanguage(1,"scala"))
    }

    "add records" in new WithApplication() {
      when(service.insert(1,"scala")).thenReturn(Future(1))
      val res=controller.insertProg(1,"scala")
      val response=Await.result(res,Duration.Inf)
      response===1
    }

    "update records" in new WithApplication() {
      when(service.update(1,"scala")).thenReturn(Future(1))
      val res=controller.updateProg(1,"scala")
      val response=Await.result(res,Duration.Inf)
      response===1
    }

    "delete record" in new WithApplication() {
      when(service.delete("scala")).thenReturn(Future(1))
      val res=controller.deleteProg("scala")
      val response=Await.result(res,Duration.Inf)
      response===1
    }
  }

}
