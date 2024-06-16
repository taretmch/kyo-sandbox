//> using scala "3.4.2"
//> using dep "io.getkyo::kyo-core:0.10.2"
//> using dep "ch.qos.logback:logback-classic:1.5.6"
//> using dep "org.slf4j:slf4j-api:2.0.13"

import kyo.*

val err: Unit < IOs = Logs.error("example")
val info: Unit < IOs = Logs.info("example")
val warn: Unit < IOs = Logs.warn("example")
val debug: Unit < IOs = Logs.debug("example")
val throwable: Unit < IOs = Logs.error(new Exception("example"))

val allLogs: Unit < IOs = for {
  _ <- err
  _ <- info
  _ <- warn
  _ <- debug
  _ <- throwable
} yield ()

IOs.run(allLogs).pure
// 22:01:34.803 [main] ERROR kyo.logs -- [02_effect_logs.scala:14] example
// 22:01:34.807 [main] INFO kyo.logs -- [02_effect_logs.scala:15] example
// 22:01:34.808 [main] WARN kyo.logs -- [02_effect_logs.scala:16] example
// 22:01:34.809 [main] DEBUG kyo.logs -- [02_effect_logs.scala:17] example
// 22:02:11.665 [main] ERROR kyo.logs -- [02_effect_logs.scala:18] Params("new Exception(\"example\")" -> java.lang.Exception: example)
