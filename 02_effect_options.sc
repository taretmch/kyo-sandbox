//> using scala "3.4.2"
//> using dep "io.getkyo::kyo-core:0.10.2"

import kyo.*

val a: Int < Options = Options.get(Some(1))

val b: Int < Options = Options(1)

assert(Options.run(Options(null)).pure == Options.run(Options.get(None)).pure)

val c: Int < Options = Options.getOrElse(None, 42)

val d: Int < Options = Options.getOrElse(Some(1), c)
