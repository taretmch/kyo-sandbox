//> using scala "3.4.2"
//> using dep "io.getkyo::kyo-core:0.10.2"
//> using dep "io.getkyo::kyo-tapir:0.10.2"

import kyo.*
import sttp.tapir.*
import sttp.tapir.server.netty.*

val healthRoutes: Unit < Routes =
  Routes.add(_.get.in("health").out(stringBody)) { _ => "ok" }

val healthEndpoint: Endpoint[Unit, Unit, Unit, String, Any] = endpoint.get.in("health").out(stringBody)
val healthRoutes2: Unit < Routes =
  Routes.add(healthEndpoint)(_ => "ok")

val nettyServer: NettyKyoServerBinding < Fibers =
  Routes.run(healthRoutes.andThen(healthRoutes2))

val nettyServerWithSettings: NettyKyoServerBinding < Fibers =
  Routes.run(NettyKyoServer().port(9999))(healthRoutes.andThen(healthRoutes2))
