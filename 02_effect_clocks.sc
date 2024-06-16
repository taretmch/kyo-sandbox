//> using scala "3.4.2"
//> using dep "io.getkyo::kyo-core:0.10.2"

import kyo.*
import java.time.Instant

val a: Instant < IOs = Clocks.now

println(IOs.run(a).pure)
