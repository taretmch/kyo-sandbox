//> using scala "3.4.2"
//> using dep "io.getkyo::kyo-core:0.10.2"

import kyo.*

// スタックセーフな再帰
val a: Int < Any =
  Loops.transform(1)(i =>
    if i < 5 then Loops.continue(i + 1)
    else Loops.done(i)
  )

val b: Int < Any =
  Loops.transform(1, 1)((i, j) =>
    if i + j < 5 then Loops.continue(i + 1, j + 1)
    else Loops.done(i)
  )

val d: Int < IOs =
  Loops.transform(1)(i =>
    if i < 5 then
      IOs(println(s"Iteration: $i")).map(_ => Loops.continue(i + 1))
    else
      Loops.done(i)
  )

val e: Int < Consoles =
  Loops.transform(1)(i =>
    if i < 5 then
      Consoles.println(s"Iteration: $i").map(_ => Loops.continue(i + 1))
    else
      Loops.done(i)
  )
