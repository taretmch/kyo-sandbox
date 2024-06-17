//> using scala "3.4.2"
//> using dep "io.getkyo::kyo-core:0.10.2"

import kyo.*

// Queues エフェクト
// - IOs の上で動作する
// - JCTools ライブラリに基づいたスレッドセーフなキューデータ構造を提供する
// - ScalaJS では ArrayQueue が提供される

// Bounded queue
val queue: Queue[Int] < IOs = Queues.init(capacity = 10)

val size: Int < IOs = queue.map(_.size)

val capacity: Int < IOs = queue.map(_.capacity)

val added: Boolean < IOs = queue.map(_.offer(42))

val poll: Option[Int] < IOs = queue.map(_.poll)

val peek: Option[Int] < IOs = queue.map(_.peek)

val isEmpty: Boolean < IOs = queue.map(_.isEmpty)

val isFull: Boolean < IOs = queue.map(_.isFull)

val allItems: Seq[Int] < IOs = queue.map(_.drain)

val closed: Option[Seq[Int]] < IOs = queue.map(_.close)

// Unbounded queue
// 推奨されない。GC のオーバーヘッドがシステムの失敗を引き起こす可能性がある
// - dropping: 満杯になると新しいエントリを破棄する
// - sliding: 必要に応じて、最も古いエントリを破棄して新しいエントリのスペースを確保する
val unbounded: Queues.Unbounded[Int] < IOs = Queues.initUnbounded()

// 並行アクセスポリシー (JVM のみ)
// enqueue, dequeue に対するアトミック操作を提供する
//
// - Mpmc: Multiple Producers, Multiple Consumers (柔軟だが遅くなる)
// - Mpsc: Multiple Producers, Single Consumer
// - Spmc: Single Producer, Multiple Consumers
// - Spsc: Single Producer, Single Consumer (厳しいが高速)
val queueWithMpmc: Queue[Int] < IOs = Queues.init(capacity = 10, access = Access.Mpmc)
