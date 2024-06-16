//> using scala "3.4.2"
//> using dep "io.getkyo::kyo-core:0.10.2"

import kyo.*

// 初期化
val a: Chunk[Int] = Chunks.init(1, 2, 3)
val b: Chunk[Int] = Chunks.initSeq(Seq(4, 5, 6))

// O(1)
val c: Chunk[Int] = a.append(4)
val d: Chunk[Int] = b.take(2)
val e: Chunk[Int] = c.dropLeft(1)

// O(n)
val f: Chunk[String] = d.map(_.toString).pure
val g: Chunk[Int] = e.filter(_ % 2 == 0).pure
