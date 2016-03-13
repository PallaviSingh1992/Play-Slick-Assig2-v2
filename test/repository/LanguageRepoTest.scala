package repository

import models.Language
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.Application
import play.api.test.WithApplication

import scala.concurrent.Await
import scala.concurrent.duration.Duration

@RunWith(classOf[JUnitRunner])
class LanguageRepoTest extends Specification{

  def langRepo(implicit app:Application)=Application.instanceCache[LanguageRepo].apply(app)

  "Language Repository" should {
    "get language records" in new WithApplication {
      val res = langRepo.getAll()
      val response = Await.result(res, Duration.Inf)
      response.head.id ===1
    }

    "insert language records" in new WithApplication() {
      val res=langRepo.insert(1,"french","basic")
      val response=Await.result(res,Duration.Inf)
      response===1
    }


    "update language records" in new WithApplication(){
      val res=langRepo.update(1,"spansih","basic")
      val response=Await.result(res,Duration.Inf)
      response === 1
    }

    "delete language record" in new WithApplication() {
      val res=langRepo.delete("hindi")
      val response=Await.result(res,Duration.Inf)
      response===1
    }

    "get records by id" in new WithApplication() {
      val res=langRepo.getLanguage(1)
      val response=Await.result(res,Duration.Inf)
      response===Vector(Language(1,"hindi","advanced"))
    }


  }

}
