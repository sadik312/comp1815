import org.json.simple.JSONArray
import org.json.simple.JSONObject

@Suppress("UNCHECKED_CAST")
class Model {

    private val db = Db("db.json")

    fun add(prog: String, year: String, term: String, op: String, module: String, activity: HashMap<String, List<String>>){

        val p = stringProg(prog, year, term)

        if(!db.outProgram().containsKey(p)){
            (db.outProgram()[p] as JSONArray).add(module)
        }

        if(op == "opt"){
            if(!db.outOpt().contains(module)) {
                db.outOpt().add(module)
            }
        }else{
            if(!db.outMan().contains(module)) {
                db.outMan().add(module)
            }
        }

        val activities = JSONObject()
        for((k,v ) in activity){
            activities[k] = v
        }

        db.inModule(module, activities)
    }

    fun stringProg(prog: String, year: String, term: String): String {
        val r : (String, String, String) -> String = {
           s, s2, s3 ->

            if (s == "Postgraduate") {
                if (s3 == "Term 1") {
                    "post_y1_t1"
                } else {
                    "post_y1_t2"
                }
            } else {
                if (s2 == "Year 1") {
                    if (s3 == "Term 1") {
                        "under_y1_t1"
                    } else if (s3 == "Term 2") {
                        "under_y1_t2"
                    } else {
                        "under_y1_t3"
                    }
                } else if (s == "Year 2") {
                    if (s3 == "Term 1") {
                        "under_y2_t1"
                    } else if (s3 == "Term 2") {
                        "under_y2_t2"
                    } else {
                        "under_y2_t3"
                    }
                }else{
                    if (s3 == "Term 1") {
                        "under_y3_t1"
                    } else if (s3 == "Term 2") {
                        "under_y3_t2"
                    } else {
                        "under_y3_t3"
                    }
                }
            }
        }

        return r(prog, year, term)
    }

    fun remove(module: String){
        db.outModule().remove(module)
        db.delProgOpt(module)
    }

    fun getActivity(module: String ): HashMap<*, *> {
        val ret = db.outActivity(module) as HashMap<*, *>
        return ret
    }

    fun getProg(type: String): List<String>{
        return db.outProgram()[type] as List<String>
    }

    fun getOpt(type: String): List<String>{
        var ret = listOf<String>()
        if(type == "opt"){
           ret = db.outOpt() as List<String>
        }else if(type == "man"){
           ret = db.outMan() as List<String>
        }
        return ret
    }

    fun test(man: List<String>, prog: List<String>): List<String> {
        //work for getting a union of the lists
       return man.filter { s ->  prog.contains(s)}
    }





}

