package controllers

import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._


@RunWith(classOf[JUnitRunner])
class LoginControllerSpec extends PlaySpecification {

  "Login controller " should{
    "render login page"in new WithApplication{
      val res=route(FakeRequest(GET,"/")).get
      status(res) must equalTo(OK)
    }

    "get login data" in new WithApplication {
      val res=route(FakeRequest(GET,"/login")).get
      status(res) must equalTo(SEE_OTHER)
    }

    "render home page" in new WithApplication {
      val res=route(FakeRequest(GET,"/home")).get
      status(res) must equalTo(OK)
    }

    "logout from application" in  new WithApplication{
      val res=route(FakeRequest(GET,"/logout")).get
      status(res) must equalTo(SEE_OTHER)
    }
  }

}
