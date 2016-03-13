package repository

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.Application
import play.api.test.WithApplication

import scala.concurrent.Await
import scala.concurrent.duration.Duration

@RunWith(classOf[JUnitRunner])
class UserRepoTest extends Specification {

  def userRepo(implicit app:Application)=Application.instanceCache[UserRepo].apply(app)

  "User Repository" should {
    "get a student record" in new WithApplication {
      val res=userRepo.getUser("himani@knoldus.in")
      val response=Await.result(res,Duration.Inf)
      response.head.name === "himani"
    }

    "get all student records" in new WithApplication() {
      val res=userRepo.getAll()
      val response=Await.result(res,Duration.Inf)
      response.head.name==="himani"
    }

    "add student records" in new WithApplication{
      val res=userRepo.insert(3,"santosh","santosh@gmail.com","72745690","santosh")
      val response=Await.result(res,Duration.Inf)
      response ===1
    }

    "delete student record" in new WithApplication{
      val res=userRepo.delete(2)
      val response=Await.result(res,Duration.Inf)
      response ===1
    }

    "update student record" in new WithApplication() {
      val res=userRepo.update(1,"simar","simar@gmail.com","22469870","simar")
      val response=Await.result(res,Duration.Inf)
      response===1
    }
  }
}
