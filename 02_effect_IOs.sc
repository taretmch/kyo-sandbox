//> using scala "3.4.2"
//> using dep "io.getkyo::kyo-core:0.10.2"

import kyo.*
import scala.util.*

// apply
val a: Int < IOs = IOs(1)

// fail
val b: Int < IOs = IOs.fail("error")
val c: Int < IOs = IOs.fail(new Exception)

// fromTry
val d: Int < IOs = IOs.fromTry(Try(1))

// toTry
// [error] value toTry is not a member of object kyo.IOs - did you mean IOs.fromTry?
// val e: Try[Int] < IOs = IOs.toTry("1".toInt)

// cathing
val f: Int < IOs = IOs.catching("str".toInt) {
  case _: NumberFormatException => 0
}

// 基本的に、IOs の処理は KyoApp で行う
// KyoApp を利用しない場合、IOs が唯一のエフェクトである場合のみ、run メソッドを使うこともできる
val g: Int < IOs = IOs(42)
val h: Int = IOs.run(g).pure
