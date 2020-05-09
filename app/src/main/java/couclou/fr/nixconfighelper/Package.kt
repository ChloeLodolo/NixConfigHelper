package couclou.fr.nixconfighelper

class Package(val id: Int,
              val channel: String,
              val keyname: String,
              val name: String,
              val pname: String,
              val version: String,
              val system: String,
              val available: Boolean,
              val description: String,
              val downloadPage: String,
              val homepage: String,
              val license: License,
              val maintainers: List<Maintainer>,
              val outputsToInstall: List<String>,
              val platforms: List<String>,
              val position: String)
    : Element