//> using scala "3.4.2"
//> using dep "io.getkyo::kyo-core:0.10.2"

import kyo.*

// State モナドににている
// 計算内で状態を純粋関数型の方法で管理する
// 現在の値
val a: Int < Vars[Int] = Vars.get[Int]

// 値を設定
val b: Unit < Vars[Int] = Vars.set(10)

// 値の更新
val c: Unit < Vars[Int] = Vars.update[Int](v => v + 1)

// 計算内で使用する
val d: String < Vars[Int] =
  Vars.use[Int](v => v.toString)

// エフェクト処理
val e: String < Any =
  Vars.run(10)(d)

println(e)
