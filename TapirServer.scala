import java.util.concurrent.Executors

import kyo.*

import sttp.tapir.*
import sttp.tapir.server.netty.*

object TapirServer extends KyoApp:
  val port         = 9000
  val healthRoutes = Routes.add(_.get.in("health").out(stringBody)) { _ => "ok" }
  val options      = NettyKyoServerOptions.default(enableLogging = false).forkExecution(false)
  val cfg          = NettyConfig.default.withSocketKeepAlive.copy(lingerTimeout = None)
  val server       = NettyKyoServer(options, cfg).host("0.0.0.0").port(port)
  val timer        = Timer(Executors.newSingleThreadScheduledExecutor())

  run {
    defer {
      await(Consoles.println(s"Server starting on port $port..."))
      val binding = await(Routes.run(server)(Timers.let(timer)(healthRoutes)))
      await(Consoles.println(s"Server started: ${binding.localSocket}"))
    }
  }

end TapirServer
