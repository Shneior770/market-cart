package users.service

import scala.concurrent.Future

import users.User

trait UserCrud {
  def addUser(user: User): Future[String]

  def getUser(id: String): Future[Option[User]]

  def deleteUser(id: String): Future[Int]

  def listAllUsers: Future[Seq[User]]

  def updateUser(user: User): Future[Int]
}
