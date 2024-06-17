//> using scala "3.4.2"
//> using dep "io.getkyo::kyo-core:0.10.2"

import kyo.*

// Hubs エフェクト
// メッセージを複数のリスナーに同時に送信するブロードキャストメカニズムをテイキュ王する
// MPMC アクセスを前提としている
import kyo.Hubs.Listener

val hub: Hub[Int] < IOs = Hubs.init

// Hubs からの読み取りはリスナーを介してのみ可能
val listener: Listener[Int] < IOs = hub.map(_.listen)

val listenerWithMessageBuffer: Listener[Int] < IOs = hub.map(_.listen(bufferSize = 3))
