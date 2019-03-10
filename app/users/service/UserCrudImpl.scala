package users.service

import scala.concurrent.Future

import users.User
import users.userDao.UserDao

class UserCrudImpl(userDao: UserDao) extends UserCrud {
  override def addUser(user: User): Future[String] =
    userDao.add(user)

  override def getUser(id: String): Future[Option[User]] =
    userDao.get(id)

  override def deleteUser(id: String): Future[Int] =
    userDao.delete(id)

  override def listAllUsers: Future[Seq[User]] =
    userDao.listAll

  override def updateUser(user: User): Future[Int] =
    userDao.update(user)
}
