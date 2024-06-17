//> using scala "3.4.2"
//> using dep "io.getkyo::kyo-core:0.10.2"

import kyo.*

/** Fibers
  *
  * Fibersエフェクトは、管理されたスレッドプールを介して計算を非同期で実行するためのメカニズムを提供する。
  * init関数は、新しい「グリーンスレッド」（ファイバーとも呼ばれる）を生成して、指定された計算を処理する。
  * これにより、並列実行とシステムリソースの効率的な使用が可能になる。
  * また、ファイバーはLocalsの適切な伝播を維持し、フォーキングプロセス中にコンテキスト情報が伝わることを保証する。
  */


val fiber: Fiber[Int] < IOs = Fibers.init(Math.cos(42).toInt)

val fiberValue: Int < Fibers = Fibers.get(fiber)

val ios: Int < IOs = IOs(Math.cos(42).toInt)

val par: (Int, String) < Fibers = Fibers.parallel(ios, "example")

val parToList: Seq[Int] < Fibers = Fibers.parallel(Seq(ios, ios.map(_ + 1)))

val parToFiber: Fiber[Seq[Int]] < IOs =
  Fibers.parallelFiber(Seq(ios, ios.map(_ + 1)))

val sleep: Unit < Fibers = Fibers.sleep(1.seconds)

val timeout: Int < Fibers = Fibers.timeout(1.seconds)(Math.cos(42).toInt)
