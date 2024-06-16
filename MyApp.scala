import kyo.*

object MyApp extends KyoApp {

  run {
    for {
      _ <- Consoles.println(s"Main args: $args")
      currentTime <- Clocks.now
      _ <- Consoles.println(s"Current time: $currentTime")
      randomNumber <- Randoms.nextInt(100)
      _ <- Consoles.println(s"Random number: $randomNumber")
    } yield
      "Done"
  }
}
