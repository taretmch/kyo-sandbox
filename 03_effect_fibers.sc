//> using scala "3.4.2"
//> using dep "io.getkyo::kyo-core:0.10.2"

import kyo.*

val fiber: Fiber[Int] < IOs = Fibers.init(Math.cos(42).toInt)

val fiberValue: Int < Fibers = Fibers.get(fiber)

val ios: Int < IOs = IOs(Math.cos(42).toInt)

val par: (Int, String) < Fibers = Fibers.parallel(ios, "example")

val parToList: Seq[Int] < Fibers = Fibers.parallel(Seq(ios, ios.map(_ + 1)))

val parToFiber: Fiber[Seq[Int]] < IOs =
  Fibers.parallelFiber(Seq(ios, ios.map(_ + 1)))

val sleep: Unit < Fibers = Fibers.sleep(1.seconds)

val timeout: Int < Fibers = Fibers.timeout(1.seconds)(Math.cos(42).toInt)
