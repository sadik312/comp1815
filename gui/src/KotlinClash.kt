
@Suppress("UNCHECKED_CAST")
class KotlinClash(model1: Model, view2: ClashGui){


    val model = model1
    val view = view2

    val input = HashMap<String, String>()
    var clashModule = listOf<String>()

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




    private fun add(){
        val activity = HashMap<String, List<String>>()

        activity.put(input["activity"] as String, listOf(input["day"] as String, input["stime"] as String, input["etime"] as String))
        model.add(input["program"].toString(),
            input["year"].toString(), input["term"].toString(), input["opt"].toString(),
            input["module"].toString(), activity)
    }

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
                   if( checkStart((v as List<String>), input["day"].toString(), input["stime"]!!.toInt(),  input["etime"]!!.toInt())){
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


    private fun checkDay(activityDay: String, day: String): Boolean{
        var ret = false
        if(activityDay == day){
           ret = true
        }
        return ret
    }


    private fun checkStart(activityDay: List<String>, day:String, stime: Int, etime:Int): Boolean{
        //return TRUE if CLASH
        // FALSE = NO CLASH
        val day2 = activityDay[0]
        val stime2 = activityDay[1].toInt()
        val etime2 = activityDay[2].toInt()
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