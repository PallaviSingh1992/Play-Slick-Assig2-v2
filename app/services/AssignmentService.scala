package services

import com.google.inject.{ImplementedBy, Inject}
import models.Assignment
import repository.AssignmentRepo

import scala.concurrent.Future

@ImplementedBy(classOf[AssignmentService])
trait AssignmentServiceApi{
  def insertAssignment(id:Int,name:String,marks:Int,remarks:String):Future[Int]
  def updateAssignment(id:Int,name:String,marks:Int,remarks:String):Future[Int]
  def deleteAssignment(id:Int):Future[Int]
  def getAssignment:Future[List[Assignment]]
  def getAssignmentById(id:Int):Future[Seq[Assignment]]
}

class AssignmentService @Inject()(assignment:AssignmentRepo) extends AssignmentServiceApi{

  def insertAssignment(id:Int,name:String,marks:Int,remarks:String):Future[Int]={
    assignment.insert(id,name,marks,remarks)
  }

  def updateAssignment(id:Int,name:String,marks:Int,remarks:String):Future[Int]={
    assignment.update(id,name,marks,remarks)
  }

  def deleteAssignment(id:Int):Future[Int]={
    assignment.delete(id)
  }

  def getAssignment:Future[List[Assignment]]={
    assignment.getAll()
  }

  def getAssignmentById(id:Int):Future[Seq[Assignment]]= {
    assignment.getAssignment(id)
  }
}
