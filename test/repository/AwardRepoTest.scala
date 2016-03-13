package repository

import models.Award
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.Application
import play.api.test.WithApplication

import scala.concurrent.Await
import scala.concurrent.duration.Duration

@RunWith(classOf[JUnitRunner])
class AwardRepoTest extends Specification{

  def awardRepo(implicit app:Application)=Application.instanceCache[AwardRepo].apply(app)

  "Award Repository" should {
    "get award records" in new WithApplication {
      val res = awardRepo.getAll()
      val response = Await.result(res, Duration.Inf)
      response.head.id ===1
    }

    "insert award records" in new WithApplication() {
      val res=awardRepo.insert(4,"best orator","school level")
      val response=Await.result(res,Duration.Inf)
      response===1
    }

    "update award records" in new WithApplication{
      val res=awardRepo.update(1,"best singer","school level")
      val response=Await.result(res,Duration.Inf)
      response===0
    }

    "delete award records" in new WithApplication() {
      val res=awardRepo.delete("best dancer")
      val response=Await.result(res,Duration.Inf)
      response===0
    }

    "get awards by id" in new WithApplication() {
      val res=awardRepo.getAward(1)
      val response=Await.result(res,Duration.Inf)
      response===Vector(Award(1,"best programmer","school level"))
    }
  }
}
