import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.FileReader
import java.io.FileWriter



fun main(args: Array<String>) {

}


fun test(){
    val obj : JSONObject = JSONObject()

    val program = JSONObject()
    val post_1_1 = JSONArray()
    val post_1_2 = JSONArray()
    val under_1_1 = JSONArray()
    val under_1_2 = JSONArray()
    val under_2_1 = JSONArray()
    val under_2_2 = JSONArray()
    val under_3_1 = JSONArray()
    val under_3_2 = JSONArray()


    obj["program"] = program
    program["post_y1_t1"] = post_1_1
    program["post_y1_t2"] = post_1_2
    program["under_y1_t1"] = under_1_1
    program["under_y1_t2"] = under_1_2
    program["under_y2_t1"] = under_2_1
    program["under_y2_t2"] = under_2_2
    program["under_y3_t1"] = under_3_1
    program["under_y3_t2"] = under_3_2

    val module : JSONObject = JSONObject()
    val activity : JSONObject = JSONObject()
    val lecture : JSONArray = JSONArray()

    obj["module"] = module
    val man = JSONArray()
    val opt = JSONArray()

    obj["man"] = man
    obj["opt"] = opt
    println(obj)

}

class Db(filePath: String){

    private val file : String = filePath
    private val parser : JSONParser = JSONParser()
    private val reader: FileReader = FileReader(filePath)
    var obj: JSONObject = parser.parse(reader) as JSONObject



    private fun write(jObj: JSONObject){

        val writer = FileWriter(file)
        writer.write(jObj.toJSONString())
        writer.flush()
        writer.close()
    }

    fun clear(){
        ifEmpty()
    }


    fun outProgram(): JSONObject{
        return obj["program"] as JSONObject
    }



    fun outMan(): JSONArray{
        return obj["man"] as JSONArray
    }

    fun outOpt(): JSONArray{
        return obj["opt"] as JSONArray
    }

    fun outModule(): JSONObject{
        return obj["module"] as JSONObject
    }

    fun outActivity(module:String): JSONObject{
        return outModule()[module] as JSONObject
    }

    fun outdt(module:String, activity:String): JSONArray{
        return outActivity(module)[activity] as JSONArray
    }


    fun inProgram(progName: String, module: JSONObject){
        outProgram()[progName]= module
        write(obj)
    }


    fun inModule(modName: String, module: JSONObject){
        outModule()[modName] = module
        write(obj)
    }

    fun inActivity(module : String, activityName: String, array :JSONArray){
        (outModule()[module] as JSONObject)[activityName] = array
        write(obj)
    }

    fun checkProg(module: String): String {
        var ret = ""
        for((k,v) in outProgram().iterator()){
                if((v as JSONArray).contains(module)){
                    ret = k as String
            }
        }
        return ret
    }

    fun checkOpt(module: String): String {
        var ret = ""
        if(outMan().contains(module)){
            ret = "man"
        }else if(outOpt().contains(module)){
            ret  = "opt"
        }else{
            println("module not in opt list")
        }

        return ret
    }

    fun delProgOpt(module: String){
        val opt = checkOpt(module)
        val prog = checkProg(module)

        (obj[opt] as JSONArray).remove(module)
        (outProgram()[prog] as JSONArray).remove(module)
        write(obj)
    }

    fun ifEmpty(){

        val obj : JSONObject = JSONObject()

        val program = JSONObject()
        val post_1_1 = JSONArray()
        val post_1_2 = JSONArray()
        val under_1_1 = JSONArray()
        val under_1_2 = JSONArray()
        val under_2_1 = JSONArray()
        val under_2_2 = JSONArray()
        val under_3_1 = JSONArray()
        val under_3_2 = JSONArray()


        obj["program"] = program
        program["post_y1_t1"] = post_1_1
        program["post_y1_t2"] = post_1_2
        program["under_y1_t1"] = under_1_1
        program["under_y1_t2"] = under_1_2
        program["under_y2_t1"] = under_2_1
        program["under_y2_t2"] = under_2_2
        program["under_y3_t1"] = under_3_1
        program["under_y3_t2"] = under_3_2

        val module : JSONObject = JSONObject()
        val activity : JSONObject = JSONObject()
        val lecture : JSONArray = JSONArray()

        obj["module"] = module

        val man = JSONArray()
        val opt = JSONArray()

        obj["man"] = man
        obj["opt"] = opt

        write(obj)
    }




}