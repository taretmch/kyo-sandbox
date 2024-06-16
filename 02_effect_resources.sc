//> using scala "3.4.2"
//> using dep "io.getkyo::kyo-core:0.10.2"

import kyo.*
import java.io.Closeable

class Database extends Closeable {
  def count: Int < IOs = 42
  def close() = println("Database closed")
}

val db: Database < Resources = Resources.acquire(new Database)

// run: エフェクトを処理し、計算で使用されたリソースを解放する
val b: Int < Fibers = Resources.run(db.map(_.count))

def withDb[T](f: Database => T < Fibers): T < Resources =
  IOs(new Database).map { db =>
    Resources.ensure(db.close).map { _ =>
      f(db)
    }
  }

val c: Int < Resources = withDb(_.count)

val d: Int < Fibers = Resources.run(c)
