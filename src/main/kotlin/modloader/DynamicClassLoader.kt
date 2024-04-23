package asteroid4.tileengine.modloader

import java.io.IOException
import java.net.URL
import java.net.URLClassLoader
import java.nio.file.Paths

class DynamicClassLoader(name: String?, parent: ClassLoader?) :
    URLClassLoader(name, arrayOfNulls(0), parent) {
    @JvmOverloads
    constructor(parent: ClassLoader? = Thread.currentThread().contextClassLoader) : this("classpath", parent)

    fun add(url: URL?) {
        addURL(url)
    }

    /*
     *  Required for Java Agents when this classloader is used as the system classloader
     */
    @Suppress("unused")
    @Throws(IOException::class)
    private fun appendToClassPathForInstrumentation(jarfile: String) {
        add(Paths.get(jarfile).toRealPath().toUri().toURL())
    }

    companion object {
        init {
            registerAsParallelCapable()
        }

        fun findAncestor(cl: ClassLoader?): DynamicClassLoader? {
            var cl = cl
            do {
                if (cl is DynamicClassLoader) return cl

                cl = cl!!.parent
            } while (cl != null)

            return null
        }
    }
}