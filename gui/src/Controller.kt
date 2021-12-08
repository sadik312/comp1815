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


        fun getKotlinCrash(model: Model, view: ClashGui){ KotlinClash(model, view) }

        fun getScalaCrash(model1: Model, view1: ClashGui){

        }





    }



}

fun main(){
    val controller = Controller()



}



