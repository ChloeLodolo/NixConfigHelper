package couclou.fr.nixconfighelper

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class ElementAdapter: JsonDeserializer<Element> {
    @RequiresApi(Build.VERSION_CODES.P)

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Element? {
        val jsonObject = json?.asJsonObject
        val configType = jsonObject?.get("configurationType")?.asString?.capitalize()
        println(jsonObject)
        try {
            val fullName = typeOfT?.typeName;
            val packageText = fullName?.substring(0, fullName.lastIndexOf(".") + 1)
            return context?.deserialize(jsonObject, Class.forName(packageText + configType))
        } catch (cnfe: ClassNotFoundException) {
            throw JsonParseException("Unknown element type : $cnfe")
        }
    }
}