package couclou.fr.nixconfighelper

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class ElementAdapter: JsonDeserializer<Element> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext
    ): Element {
        val jsonObject = json.asJsonObject
        val type = jsonObject.get("type").asString
        println(type.capitalize())
        println(jsonObject)
        try {
            return context.deserialize(jsonObject, Class.forName(type.capitalize()))
        } catch (cnfe: ClassNotFoundException) {
            throw JsonParseException("Unknown element type : $type $cnfe")
        }

        /*when (type) {
            "option" ->
                try {
                    return context.deserialize(jsonObject, Class.forName("Option"))
                } catch (cnfe: ClassNotFoundException) {
                    throw JsonParseException("Unknow element type : $type $cnfe")
                }
            "package" ->
                try {
                    return context.deserialize(jsonObject, Class.forName("Package"))
                } catch (cnfe: ClassNotFoundException) {
                    throw JsonParseException("Unknow element type : $type $cnfe")
                }
            else -> return Element()
        }*/
    }
}