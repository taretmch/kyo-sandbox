//> using scala "3.4.2"
//> using dep "io.getkyo::kyo-core:0.10.2"

import kyo.*

val a: String < Consoles = Consoles.readln

val b: Unit < Consoles = Consoles.print("ok")

val c: Unit < Consoles = Consoles.println("ok")

val d: Unit < Consoles = Consoles.printErr("fail")

val e: Unit < Consoles = Consoles.printlnErr("fail")

val f: Unit < IOs = Consoles.run(e)

val g: Unit < IOs = Consoles.run(Console.default)(e)
