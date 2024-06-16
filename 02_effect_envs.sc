//> using scala "3.4.2"
//> using dep "io.getkyo::kyo-core:0.10.2"

import kyo.*

// 依存性の注入
trait Database:
  def count: Int < IOs

val a: Database < Envs[Database] = Envs.get[Database]

val b: Int < (Envs[Database] & IOs) = a.map(_.count)

val db = new Database:
  def count = 1

val c: Int < IOs = Envs.run(db)(b)

trait Cache:
  def clear: Unit < IOs

val d: Unit < (Envs[Database] & Envs[Cache] & IOs) =
  Envs.get[Database].map { db =>
    db.count.map {
      case 0 =>
        Envs.get[Cache].map(_.clear)
      case _ =>
        ()
    }
  }
