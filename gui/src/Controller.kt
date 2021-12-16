import java.awt.event.ActionEvent
import java.awt.event.ActionListener


class Controller{

    private val model = Model()
    private val view = ClashGui()

    init{
        view.start()
        view.adAddButtonListener(PrintButton(model, view))
    }

    class PrintButton(model1: Model, view1: ClashGui): ActionListener{
        val model = model1
        val view = view1



        override fun actionPerformed(e: ActionEvent?) {
            if(view.adCrash == "Kotlin"){

                getKotlinCrash(model, view)

            }else if(view.adCrash == "Scala"){
                getScalaCrash(model, view)

            }else{
                println("Should not be here, Error has occurred")
            }
        }

        /**
         * Model and ClashGUi instance passed from Controller class
         * calls kotlinClash to check for Clashes
         */
        fun getKotlinCrash(model: Model, view: ClashGui){ KotlinClash(model, view) }


        /**
         * Model and ClashGUi instance passed from Controller class
         * calls scalaClash from ClashGui as the ScalaClash was only able to be called from a java
         * file to check for Clashes
         */
        fun getScalaCrash(model: Model, view: ClashGui) {
                ClashGui.scalaClash(model, view)
        }





    }



}

fun main(){
    val controller = Controller()

}



