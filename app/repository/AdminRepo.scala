package services

import com.google.inject.Inject
import models.User
import models.Assignment
import repository._
import slick.driver
import com.google.inject.Inject
import com.google.inject.ImplementedBy

import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.driver.JdbcProfile
import scala.concurrent.Future


class AdminRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider, user:UserRepo, award:AwardRepo, assign:AssignmentRepo, lang:LanguageRepo, proLang:ProgLanguageRepo) extends HasDatabaseConfigProvider[JdbcProfile] with UserTable{
//  val valuestmt= for{
//    (id,name,email,award) <- user.userTableQuery join award.awardTableQuery on(_.id==_.id)
//    (assignment)<- user.userTableQuery join assign.assignmentTableQuery on(_.id==_.id)
//    (language)<- user.userTableQuery join lang.languageTableQuery on(_.id==_.id)
//    (proglang)<-user.userTableQuery join proLang.progLanguageTableQuery on (_.id==_.id)
//  }yield (id,name,email,award,assignment,language,proglang);

  import driver.api._

  def getAll() = db.run {userTableQuery.to[List].result  }

  }