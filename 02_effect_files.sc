//> using scala "3.4.2"
//> using dep "io.getkyo::kyo-core:0.10.2"
//> using dep "io.getkyo::kyo-os-lib:0.10.2"

// kyo-os-lib は、次のバージョンで kyo-core に統合される。ドキュメントもそうなっている
// 参照: https://github.com/getkyo/kyo/commit/c1676f08dec8b3b089eee776ff08462e7a81394e

import kyo.*

val path: Files = Files("tmp", "file.txt")

val content: String < IOs = path.read

val writeResult: Unit < IOs = path.write("Hello, world!")

val exists: Boolean < IOs = path.exists

val createDir: Unit < IOs = Files("tmp", "test").mkDir

val res: Unit < IOs = IOs.run {
  exists.map {
    case true  => content.map(s => Consoles.run(Consoles.println(s"File content: $s")))
    case false => writeResult.andThen(createDir).andThen(Consoles.run(Consoles.println("File created.")))
  }
}

// Stream は次バージョンでサポートされる。
