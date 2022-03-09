package Exercise2

import java.io.File

class Files {
    companion object {
        fun filesDemo() {
            val root: File = File(System.getProperty("user.dir"))
            val dirList: List<File> = root.dirFiles { file: File -> file.parent.contains("kotlin") }
            println(dirList)
        }

        private fun File.dirFiles(fileFilter: ((File) -> Boolean) = { _: File -> true }): List<File> {
            fun dirFilesRecAux(dir: File, list: MutableList<File>): MutableList<File> {
                dir.listFiles().forEach {
                    if (it.isFile) {
                        if (fileFilter(it)) {
                            list.add(it)
                        }
                    } else {
                        (dirFilesRecAux(it, list))
                    }
                }
                return list
            }

            val files: MutableList<File> = mutableListOf<File>()

            return dirFilesRecAux(this, files)
        }

    }
}