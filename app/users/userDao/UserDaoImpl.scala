package users.userDao

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import slick.basic.DatabaseConfig
import slick.jdbc.H2Profile
import users.User

class UserDaoImpl(val h2Db: DatabaseConfig[H2Profile]) extends UserDao {

  import h2Db._
  import profile.api._

  val users: TableQuery[UserTable] = TableQuery[UserTable]

  override def add(user: User): Future[String] = {
    db.run(users += user).map(_ => "User successfully added").recover {
      case ex: Exception =>
        val f = ex.getCause.getMessage
        f
    }
  }

  override def get(id: String): Future[Option[User]] = {
    db.run(users.filter(_.id === id).result.headOption)
  }

  override def delete(id: String): Future[Int] = {
    db.run(users.filter(_.id === id).delete)
  }

  override def listAll: Future[Seq[User]] = {
    db.run(users.result)
  }

  override def update(user: User): Future[Int] = {
    db.run(users.filter(userx => userx.id === user.id).update(user))
  }

  class UserTable(tag: Tag) extends Table[User](tag, "users") {

    override def * =
      (id, password, email, firstName, lastName) <> (User.tupled, User.unapply)

    def id: Rep[String] = column[String]("Id", O.PrimaryKey)

    def password: Rep[String] = column[String]("Password")

    def email: Rep[String] = column[String]("Email")

    def firstName: Rep[String] = column[String]("First_name")

    def lastName: Rep[String] = column[String]("Last_name")
  }

}

