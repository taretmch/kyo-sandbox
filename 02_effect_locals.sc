//> using scala "3.4.2"
//> using dep "io.getkyo::kyo-core:0.10.2"

import kyo.*

// ローカルの初期化
val myLocal: Local[Int] = Locals.init(42)

// ローカルの現在の値
val a: Int < IOs = myLocal.get

// 43(42 + 1) が返る
val b: Int < IOs = myLocal.let(42)(a.map(_ + 1))
