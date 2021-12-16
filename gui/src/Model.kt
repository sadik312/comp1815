import org.json.simple.JSONArray
import org.json.simple.JSONObject
@Suppress("UNCHECKED_CAST", "CAST_NEVER_SUCCEEDS")
class Model {

    /**
     * Instantiating Db class that has access and manages database
     */
    private val db = Db("db.json")


    /**
     * Adds Module to the database after clearing clash detection
     * @param prog Module's program to be added to
     * @param year Module's year to be added to
     * @param term Module's term to be added to
     * @param op Module's optional/compulsory to be added to
     * @param activity Hashmap containing activity type (Lecture, Lab..) as key and activity attributes as value
     *
     */
    fun add(prog: String, year: String, term: String, op: String, module: String, activity: Map<String, List<String>>){

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

    /**
     * Gets String for the corresponding array to add to
     */
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

    /**
     * Removes module from database
     */
    fun remove(module: String){
        db.outModule().remove(module)
        db.delProgOpt(module)
    }

    /**
     * Gets activity fields of a specified Module
     */
    fun getActivity(module: String): HashMap<String, List<String>> {
        return db.outActivity(module) as HashMap<String, List<String>>
    }

    /**
     * Gets program Array from the database as a List of strings
     */
    fun getProg(type: String): List<String>{
        return db.outProgram()[type] as List<String>
    }

    /**
     * Gets Optional or Compulsory module array from the database as List of strings containing all modules
     */
    fun getOpt(type: String): List<String>{
        var ret = listOf<String>()
        if(type == "opt"){
           ret = db.outOpt() as List<String>
        }else if(type == "man"){
           ret = db.outMan() as List<String>
        }
        return ret
    }

}



