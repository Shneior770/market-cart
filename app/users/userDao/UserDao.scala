package users.userDao

import scala.concurrent.Future

import users.User

trait UserDao {
  def add(user: User): Future[String]

  def delete(id: String): Future[Int]

  def update(user: User): Future[Int]

  def get(id: String): Future[Option[User]]

  def listAll: Future[Seq[User]]
}
