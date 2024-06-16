//> using scala "3.4.2"
//> using dep "io.getkyo::kyo-core:0.10.2"

import kyo.*

/** Pending 型
  * - T < Effects : T は期待される出力の値。Effects は未処理の副作用を表す
  * - 全ての型は T < Any と見なされる。Any は、未処理の副作用がないことを意味する
  */
val noEffect: Int < Any = 1
println(noEffect.pure)

// map と flatMap は同じように使うことができる。map を使うことが推奨されている
def flatMapExample(
  v1: Int < IOs,
  v2: Int < Aborts[Exception]
): Int < (IOs & Aborts[Exception]) =
  v1.flatMap(v => v2.map(_ + v))

def mapExample(
  v1: Int < IOs,
  v2: Int < Aborts[Exception]
): Int < (IOs & Aborts[Exception]) =
  v1.map(v => v2.map(_ + v))

val io1: Int < IOs = 1.pure
val abort1: Int < Aborts[Exception] = 1.pure
println(flatMapExample(io1, abort1))
println(mapExample(io1, abort1))

// Unit が生成される場合、andThen を使って合成できる
val outputUnit: Unit < IOs = IOs(println("hello"))
val outputString: String < IOs = outputUnit.andThen("test")

// エフェクトは反変型
val a: Int < Any = 1
val b: Int < Options = a
val c: Int < (Options & Aborts[Exception]) = b
val d: Int < (Options & Aborts[Exception]) = 42

/** 命名規則
  * - init*: エフェクトが扱うコンテナ型のインスタンスを初期化する。例えば、Fibers.initは新しいFiberを返す
  * - get*: コンテナ型の値を抽出する。Fibers.getはFiber[T]に対してT < Fibersを返す
  * - run*: エフェクトを処理する
  */
val initExample: Int < Options = 1
val getExample: Int < Options = Options.get(Option(1))
val runExample1: Option[Int] < Any = Options.run(initExample)
val runExample2: Option[Int] < Any = Options.run(getExample)
val pureExample1: Option[Int] = runExample1.pure
val pureExample2: Option[Int] = runExample2.pure
