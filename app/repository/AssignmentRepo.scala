package repository

import com.google.inject.Inject
import com.google.inject.ImplementedBy
import models.Assignment
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.driver.JdbcProfile
import scala.concurrent.Future

trait AssignmentTable extends UserTable{ self: HasDatabaseConfigProvider[JdbcProfile] =>
  import driver.api._

  class AssignmentTable(tag:Tag) extends Table[Assignment](tag,"assignment") {
    val id=column[Int]("id")
    val name= column[String]("name", O.SqlType("VARCHAR(200)"))
    val marks=column[Int]("marks")
    val remarks=column[String]("remarks", O.SqlType("VARCHAR(200)"))

 /*   def rel = foreignKey("user_id_fk",id, userTableQuery)(_.id)*/
    def * = (id, name,marks,remarks) <>(Assignment.tupled,Assignment.unapply)
  }

  val assignmentTableQuery = TableQuery[AssignmentTable]
}

//@ImplementedBy(classOf[AssignmentImpl])
class  AssignmentRepo @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends AssignmentTable with HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  def insert(id:Int,name:String,marks:Int,remarks:String): Future[Int] = db.run { assignmentTableQuery += Assignment(id,name,marks,remarks) }

  def update(id:Int,name:String,marks:Int,remarks:String): Future[Int] = db.run { assignmentTableQuery.filter(_.id === id).update(Assignment(id,name,marks,remarks)) }

  def delete(id: Int): Future[Int] = db.run { assignmentTableQuery.filter(_.id === id).delete }

  def getAll(): Future[List[Assignment]] = db.run { assignmentTableQuery.to[List].result }

  def getAssignment(id:Int): Future[Seq[Assignment]] = {
    db.run(assignmentTableQuery.filter(_.id === id).result)
  }

}

