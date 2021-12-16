
@Suppress("UNCHECKED_CAST")
class KotlinClash(model1: Model, view2: ClashGui){


    private val model = model1
    private val view = view2

    /**
     * Input holds user input as a HashMap with key as the entry field and value as the entered value
     * ClashModule list holds the Modules that can potentially clash due to User Entered Module being
     * on same Program, Year, Term
     */
    private val input = HashMap<String, String>()
    private var clashModule = listOf<String>()

   init{
       input["program"] = view.adProgramme
       input["year"] = view.adYear
       input["term"] = view.adTerm
       input["module"] = view.adModuleText
       input["opt"] = view.comRadioButton
       input["activity"] = view.adActivity
       input["day"] = view.adDay
       input["stime"] = view.adTime
       input["etime"] = view.adEndTime

       if(!clash()){
           println("added")
           view.showAddedLabel()
           add()
       }else{
           view.showClashedLabel()
       }
   }

    /**
     * Method to add the current user entered module to database
     */
    private fun add(){
        val activity = HashMap<String, List<String>>()

        activity.put(input["activity"] as String, listOf(input["day"] as String, input["stime"] as String, input["etime"] as String))
        model.add(input["program"].toString(),
            input["year"].toString(), input["term"].toString(), input["opt"].toString(),
            input["module"].toString(), activity)
    }

    /**
     * Main clash detection function
     * @return True for clash and False for NO CLASH
     */
    private fun clash(): Boolean{
        //TRUE == CLASH
        //FALSE == NO CLASH

        if(input["opt"] == "opt"){
            println("Optional Module selected")
        }else{
            val program = model.stringProg(input["program"].toString(), input["year"].toString(), input["term"].toString())
            clashModule = model.getOpt("man").filter { s -> model.getProg(program).contains(s)  }

            for(i in clashModule){
                for((k,v) in model.getActivity(i)){
                   if( checkStart(v, input["day"].toString(), input["stime"]!!.toInt(),  input["etime"]!!.toInt())){
                       //CLASH DETECTED
                       println("Clashed: $i:$k")
                       return true
                   }else{
                       //NO CLASH DETECTED
                   }
                }
            }
        }
        return false
    }

    /**
     * Checks if days clash
     * @return return Ture for clash and False for no clash
      */
    private fun checkDay(activityDay: String, day: String): Boolean{
        var ret = false
        if(activityDay == day){
           ret = true
        }
        return ret
    }

    /**
     * Checks clashing times
     * @return True for clash and False for no Clash
     */
    private fun checkStart(activityField: List<String>, day:String, stime: Int, etime:Int): Boolean{
        //return TRUE if CLASH
        // FALSE = NO CLASH
        val day2 = activityField[0]
        val stime2 = activityField[1].toInt()
        val etime2 = activityField[2].toInt()
        var ret = true
        if(checkDay(day, day2)) {
            if((stime < stime2 && (etime < stime2))){
                ret = false
            }else if( (stime > stime2) && (stime > etime2)){
                ret = false
            }else if((stime == etime2)){
                ret = false
            }
        }else{
            ret = false
        }
        return ret
    }
}