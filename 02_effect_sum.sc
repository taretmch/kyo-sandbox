//> using scala "3.4.2"
//> using dep "io.getkyo::kyo-core:0.10.2"

import kyo.*

// Writer モナドに似ており、計算の主な結果とともに値の Chunk を収集する

// 値追加
val a: Unit < Sums[Int] =
  Sums.add(42)

val b: String < Sums[Int] =
  for 
    _ <- Sums.add(1)
    _ <- Sums.add(2)
    _ <- Sums.add(3)
  yield "done"

// エフェクト処理
val c: (Chunk[Int], String) < Any =
  Sums.run(b)

println(c)
// (Chunk(1, 2, 3),done)

val d: String < (Sums[Int] & Sums[String]) =
  for 
    _ <- Sums.add(1)
    _ <- Sums.add("two")
    _ <- Sums.add(3)
  yield "done"

val e: (Chunk[Int], (Chunk[String], String)) < Any =
  Sums.run[Int](Sums.run[String](d))

println(e)
