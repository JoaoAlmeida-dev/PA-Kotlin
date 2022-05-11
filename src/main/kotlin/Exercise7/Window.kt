package Exercise7

import java.awt.*
import javax.swing.JButton
import javax.swing.JFrame
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.hasAnnotation


interface FrameSetup {
    val title: String
    val layoutManager: LayoutManager
}

interface Action {
    val name: String
    fun execute(window: Window)
}

class Window {
    private val frame = JFrame()

    // 1) eliminar dependencia de DefaultSetup; @Inject
    @Inject
    lateinit var setup: FrameSetup

    // 2) eliminar dependencias das acoes concretas (Center, Size); @InjectAdd
    @InjectAdd
    var actions: MutableList<Action> = mutableListOf()

    init {
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.size = Dimension(300, 200)
    }

    val location get() = frame.location
    val dimension get() = frame.size

    fun open() {
        frame.title = setup.title
        frame.layout = setup.layoutManager
        actions.forEach { action ->
            val button = JButton(action.name)
            button.addActionListener { action.execute(this@Window) }
            frame.add(button)
        }
        frame.isVisible = true
    }

    fun move(x: Int, y: Int) {
        frame.location = Point(x, y)
    }

    fun setSize(width: Int, height: Int) {
        require(width > 0)
        require(height > 0)
        frame.size = Dimension(width, height)
    }


}

fun main() {
    //val w = Window() // substituir por criacao com injecao
    //w.open()

    //val clazz: KClass<*> = Class.forName("Window").kotlin

    val w: Window = Injector.create(Window::class) as Exercise7.Window // em vez de Window()
    w.open()

}
