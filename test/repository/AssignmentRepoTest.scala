package repository

import models.Assignment
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.Application
import play.api.test.WithApplication
import scala.concurrent.Await
import scala.concurrent.duration.Duration

@RunWith(classOf[JUnitRunner])
class AssignmentRepoTest extends Specification{

  def assigRepo(implicit app:Application)=Application.instanceCache[AssignmentRepo].apply(app)

  "Assignment Repository" should {

    "get assignment records" in new WithApplication {
      val res = assigRepo.getAll()
      val response = Await.result(res, Duration.Inf)
      response.head.id ===1
    }

    "insert assignment records" in new WithApplication() {
      val res=assigRepo.insert(1,"slick",5,"no remark")
      val response=Await.result(res,Duration.Inf)
      response===1
    }

    "delete assignment record" in new WithApplication() {
      val res=assigRepo.delete(1)
      val response=Await.result(res,Duration.Inf)
      response===1
    }


    "update assignment records" in new WithApplication(){
      val res=assigRepo.update(1,"c++",7,"can do better")
      val response=Await.result(res,Duration.Inf)
      response === 1

    }

    "get assignment by id" in new WithApplication() {
      val res=assigRepo.getAssignment(1)
      val response=Await.result(res,Duration.Inf)
      response===Vector(Assignment(1,"scala",7,"no remark"))
    }
  }

}
