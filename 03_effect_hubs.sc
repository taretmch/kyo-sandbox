//> using scala "3.4.2"
//> using dep "io.getkyo::kyo-core:0.10.2"

import kyo.*

// Hubs エフェクト
// メッセージを複数のリスナーに同時に送信するブロードキャストメカニズムをテイキュ王する
// MPMC アクセスを前提としている
import kyo.Hubs.Listener
