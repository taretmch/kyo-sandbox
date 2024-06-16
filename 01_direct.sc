//> using scala "3.4.2"
//> using dep "io.getkyo::kyo-core:0.10.2"
//> using dep "io.getkyo::kyo-direct:0.10.2"

import kyo.*

// Direct Style: await: map の syntax sugar
val strEff: String < (Aborts[Exception] & Options) =
  defer {
    val a: String = await(Options.get(Some("hello")))
    val b: String = await(Aborts.get(Right("world")))
    a + " " + b
  }


defer {
  val a: Int = 5
  val b: Int = await(IOs(10))
  val c: String =
    if (await(IOs(true))) "True branch" else "False branch"
  val d: Boolean=
    await(IOs(true)) && await(IOs(false))
  val e: Boolean =
    await(IOs(true)) || await(IOs(false))

  while (await(IOs(false))) { () }

  val matchResult: String =
    await(IOs(1)) match
      case 1 => "One"
      case _ => "Other"
}
