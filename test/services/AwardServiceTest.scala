package services

import models.Award
import org.mockito.Mockito._
import org.specs2.mock.Mockito
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import repository.AwardRepo
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

@RunWith(classOf[JUnitRunner])
class AwardServiceTest extends PlaySpecification with Mockito{

  "Award Service" should {

    val service=mock[AwardRepo]
    val controller=new AwardService(service)

    "get award" in new WithApplication() {
      when(service.getAward(1)).thenReturn(Future(Seq(Award(1,"best speaker","zonal"))))
      val res=controller.getAwardById(1)
      val response=Await.result(res,Duration.Inf)
      response===List(Award(1,"best speaker","zonal"))
    }

    "insert award" in new WithApplication() {
      when(service.insert(1,"best coder","state level")).thenReturn(Future(1))
      val res=controller.insertAward(1,"best coder","state level")
      val response=Await.result(res,Duration.Inf)
      response===1
    }

    "update award" in new WithApplication() {

      when(service.update(1,"best coder","school")).thenReturn(Future(1))
      val res=controller.updateAward(1,"best coder","school")
      val response=Await.result(res,Duration.Inf)
      response===1
    }

    "delete award" in new WithApplication() {

      when(service.delete("best speaker")).thenReturn(Future(1))
      val res=controller.deleteAward("best speaker")
      val response=Await.result(res,Duration.Inf)
      response===1
    }

    "get all awards" in new WithApplication() {

      when(service.getAll()).thenReturn(Future(List(Award(1,"best speaker","zonal level"))))
      val res=controller.getAward
      val response=Await.result(res,Duration.Inf)
      response===List(Award(1,"best speaker","zonal level"))
    }
  }

}
