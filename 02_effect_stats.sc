//> using scala "3.4.2"
//> using dep "io.getkyo::kyo-core:0.10.2"
//> using dep "io.getkyo::kyo-stats-otel:0.10.2"

import kyo.*
import kyo.stats.*

val stats: Stats = Stats.initScope("my_application", "my_module")

val counter: Counter = stats.initCounter("my_counter")

val histogram: Histogram = stats.initHistogram(name = "my_histogram", description = "historgram example")

val gauge: Gauge = stats.initGauge("free_memory") {
  Runtime.getRuntime().freeMemory()
}

val res: Int < IOs = IOs(42)
val traced: Int < IOs = stats.traceSpan("my_span")(res)

println(IOs.run(traced).pure)
