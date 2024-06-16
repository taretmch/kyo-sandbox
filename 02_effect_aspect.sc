//> using scala "3.4.2"
//> using dep "io.getkyo::kyo-core:0.10.2"

import kyo.*
import java.io.Closeable

class Database extends Closeable {
  def count: Int < IOs = 42
  def close() = {}
}

val countAspect: Aspect[Database, Int, IOs] =
  Aspects.init[Database, Int, IOs]

def count(db: Database): Int < IOs =
  countAspect(db)(_.count)

val countPlusOne =
  new Cut[Database, Int, IOs] {
    def apply[S](v: Database < S)(f: Database => Int < IOs) =
      v.map(db => f(db).map(_ + 1))
  }

def example(db: Database): Int < IOs =
  countAspect.let(countPlusOne) {
    count(db)
  }
