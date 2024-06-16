//> using scala "3.4.2"
//> using dep "io.getkyo::kyo-core:0.10.2"

import kyo.*

// Seq の各要素を評価
val a: Int < Choices = Choices.get(Seq(1, 2, 3, 4))

val b: Int < Choices = a.map(v => Choices.filter(v < 2).map(_ => v))

val c: Int < Choices = b.map {
  case 1 => 42
  case _ => Choices.drop
}

val d: Seq[Int] < Any = Choices.run(c)

println(d)
