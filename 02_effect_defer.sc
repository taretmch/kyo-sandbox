//> using scala "3.4.2"
//> using dep "io.getkyo::kyo-core:0.10.2"

import kyo.*

// Defers エフェクトは、遅延評価を行うエフェクト

val a: Int < Defers =
  Defers {
    val result = 21 + 21
    result
  }

val b: Int < Any =
  Defers.run(a)

println(b.pure)

val c: Int < Defers =
  a.map(_ + 1)

val d: Int < Defers =
  a.map(x => c.map(_ + x))

val e: Int < Any =
  Defers.run(d)

println(e.pure)
