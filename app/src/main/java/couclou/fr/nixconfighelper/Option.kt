package couclou.fr.nixconfighelper

class Option(val keyname: String,
             val type: String,
             val readOnly: Boolean,
             val defaultValue: String,
             val description: String,
             val declarations: List<String>)
    : Element