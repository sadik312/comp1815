import java.awt.event.ActionEvent
import java.awt.event.ActionListener

//class Controller(val model: Model, val view: ClashGui){
//
//    fun detectClash(){
//        println("EH EH")
//    }
//
//}

class Controller(){

    constructor(var model: Model, var view: ClashGui){
        this.model = Model()
        this.view = ClashGui()
        this.view.adAddButtonListener(PrintButton())
    }


    init{
        this.model = Model()
        this.view = ClashGui()
        this.view.adAddButtonListener(PrintButton())
    }

    class PrintButton : ActionListener{
        override fun actionPerformed(e: ActionEvent?) {
            println("EH EH THIS IS WHERE YOU ADD ISH")
        }

        fun kotlinClash(){

        }

    }


    fun detectClash(){
        println("EH EH")
    }
}

fun main(){
    var eheh : Controller.PrintButton = Controller.PrintButton()
    eheh.actionPerformed()



}



