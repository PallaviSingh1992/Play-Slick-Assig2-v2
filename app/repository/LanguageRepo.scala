package repository

import com.google.inject.Inject
import com.google.inject.ImplementedBy
import models.Language
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.driver.JdbcProfile
import scala.concurrent.Future

trait LanguageTable extends UserTable{ self: HasDatabaseConfigProvider[JdbcProfile] =>
  import driver.api._

  class LanguageTable(tag:Tag) extends Table[Language](tag,"language") {
    val id=column[Int]("id")
    val name= column[String]("name", O.SqlType("VARCHAR(200)"))
    val fluency=column[String]("fluency", O.SqlType("VARCHAR(200)"))

    /*def rel = foreignKey("user_id_fk",id, userTableQuery)(_.id)*/
    def * = (id, name,fluency) <>(Language.tupled, Language.unapply)
  }

  val languageTableQuery = TableQuery[LanguageTable]
}

//@ImplementedBy(classOf[LanguageImpl])
class  LanguageRepo @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends LanguageTable with HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  def insert(id: Int, name: String, fluency: String): Future[Int] = {
    val insertQuery=languageTableQuery += Language(id, name, fluency)
    db.run {insertQuery}
  }

  def update(id: Int, name: String, fluency: String): Future[Int] = db.run {languageTableQuery.filter(_.id === id).update(Language(id, name, fluency))}

  def delete(name:String): Future[Int] = db.run { languageTableQuery.filter(_.name === name).delete}

  def getAll(): Future[List[Language]] = db.run {
    val test=languageTableQuery.to[List]
    test.result
  }
  def getLanguage(id:Int): Future[Seq[Language]] = {
    db.run(languageTableQuery.filter(_.id === id).result)
  }
}

