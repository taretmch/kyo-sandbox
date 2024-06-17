//> using scala "3.4.2"
//> using dep "io.getkyo::kyo-core:0.10.2"

import kyo.*

// Channels エフェクト
// バックプレッシャー通信
// Fibers エフェクトの上に構築される
val channel: Channel[Int] < IOs = Channels.init(capacity = 10)

// ポリシーも使える
val channelWithMpmc: Channel[Int] < IOs = Channels.init(capacity = 10, access = Access.Mpmc)

// アイテムを追加する
// 容量がない場合、スペースが確保されるまで自動的にブロックされる
val put: Unit < Fibers = channel.map(_.put(42))

// アイテムを取得する
// アイテムがない場合、アイテムが追加されるまで自動的にブロックされる
val take: Int < Fibers = channel.map(_.take)

val putFiber: Fiber[Unit] < IOs = channel.map(_.putFiber(42))

val takeFiber: Fiber[Int] < IOs = channel.map(_.takeFiber)

val closed: Option[Seq[Int]] < IOs = channel.map(_.close)
