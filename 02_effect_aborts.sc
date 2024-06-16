//> using scala "3.4.2"
//> using dep "io.getkyo::kyo-core:0.10.2"

import kyo.*

// Either から値を抽出
val a: Int < Aborts[String] = Aborts.get(Right(1))
val b: Int < Aborts[String] = Aborts.get(Left("error"))

// 強制エラー
val c: Int < Aborts[String] = Aborts.fail("error")

// 例外キャッチ
val d: Int < Aborts[Exception] = Aborts.catching(throw new Exception)
