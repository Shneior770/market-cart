package configurations.applicationInitialization

import com.softwaremill.macwire._
import play.api.ApplicationLoader.Context
import play.api.BuiltInComponentsFromContext
import play.api.db.evolutions.EvolutionsComponents
import play.api.db.slick.{DbName, SlickComponents}
import play.api.db.{DBComponents, HikariCPComponents}
import play.api.libs.ws.ahc.AhcWSComponents
import play.api.routing.Router
import play.filters.HttpFiltersComponents
import router.Routes
import slick.basic.DatabaseConfig
import slick.jdbc.H2Profile
import users.UserController
import users.service.{UserCrud, UserCrudImpl}
import users.userDao.UserDaoImpl


case class AppComponents(context: Context) extends BuiltInComponentsFromContext(context)
  with HttpFiltersComponents
  with controllers.AssetsComponents
  with AhcWSComponents
  with EvolutionsComponents
  with DBComponents
  with HikariCPComponents
  with SlickComponents {

  lazy val h2Db: DatabaseConfig[H2Profile] = slickApi.dbConfig(DbName("market_cart"))
  //  override def httpFilters: Seq[EssentialFilter] = Seq()
  lazy val userDaoImpl: UserDaoImpl = wire[UserDaoImpl]
  lazy val userCrud: UserCrud = wire[UserCrudImpl]
  lazy val userController: UserController = wire[UserController]

  lazy val router: Router = {
    new Routes(httpErrorHandler, userController, assets)
  }
}
