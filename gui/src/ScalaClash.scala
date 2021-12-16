import scala.collection.mutable
import scala.jdk.CollectionConverters._

object ScalaClash {



  def detect(model: Model, view: ClashGui): Unit = {
    if(!clash(model, view)){
      println("Added")
      add(model, view)
    }else{
      println("Crashed")
    }
  }

  private def add(model: Model, view: ClashGui): Unit ={

    val activities: mutable.HashMap[String, java.util.List[String]] = mutable.HashMap(view.getAdActivity -> List(view.getAdDay, view.getAdTime, view.getAdEndTime).asJava)
    model.add(view.getAdProgramme, view.getAdYear, view.getAdTerm,view.getComRadioButton, view.getAdModuleText, activities.asJava)
  }

  private def clash(model: Model, view: ClashGui): Boolean ={

    val input: mutable.HashMap[String, String]= mutable.HashMap("program"->view.getAdProgramme, "year"->view.getAdYear,
    "term"->view.getAdTerm, "module"->view.getAdModuleText, "opt"->view.getComRadioButton, "activity"->view.getAdActivity,
      "day"->view.getAdDay, "stime"->view.getAdTime, "etime"->view.getAdEndTime)



    if(input("opt") == "opt"){
      return false
    }


    val program: List[String] = model.getProg(model.stringProg(input("program"), input("year"), input("term"))).asScala.toList
    val opt: List[String] = model.getOpt(input("opt")).asScala.toList
    val modules = opt.filter(program.contains(_))

    for (x <- modules){
      val p = model.getActivity(x)
      for((y,z) <- p.asScala){
        if(checkStart(z.asScala.toSeq, input("day"), input("stime").toInt, input("etime").toInt)){
          return true
        }
      }

    }
    return false
  }

  private def checkStart(activityField: Seq[String], day:String, stime: Int, etime:Int): Boolean ={

    val day2 = activityField.head
    val stime2 = activityField(1).toInt
    val etime2 = activityField(2).toInt
    var ret = true
    if(day2 == day) {
      if((stime<stime2 && (etime<stime2))){
        ret = false
      }else if((stime > stime2) && (stime > etime2)){
        ret = false
      }else if((stime == etime2)){
        ret = false
      }
    }else{
      ret = false
    }
    ret
  }
}
