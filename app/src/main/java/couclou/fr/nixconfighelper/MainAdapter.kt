package couclou.fr.nixconfighelper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.option_view.view.*
import kotlinx.android.synthetic.main.package_view.view.*

class MainAdapter(val feed:Feed): RecyclerView.Adapter<CustomViewHolder>() {
    override fun getItemCount(): Int {
        return feed.count
    }

    override fun getItemViewType(position: Int): Int {
        when (feed.elements.get(position)) {
            is Option -> return 0
            is Package -> return 1
            else -> return 2
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        lateinit var cellForRow: View
        when (viewType) {
            0 -> cellForRow = layoutInflater.inflate(R.layout.option_view, parent, false)
            1 -> cellForRow = layoutInflater.inflate(R.layout.package_view, parent, false)
        }
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        if (feed.elements.get(position) is Option) {
            val element = (feed.elements.get(position) as Option)

            val keyname = element.keyname
            val type = element.type
            val readOnly = element.readOnly
            val defaultValue = element.defaultValue
            var description = element.description.replace("\n","")
            var declarations: String = ""
            if (element.declarations.isNotEmpty()) {
                for (declaration in element.declarations) {
                    declarations = "$declarations\n$declaration"
                }
            }

            holder.view.option_keynameValue.text = keyname
            holder.view.typeValue.text = type
            holder.view.readOnlyValue.text = readOnly.toString()
            holder.view.defaultValueValue.text = defaultValue
            holder.view.option_descriptionValue.text = description
            holder.view.declarationsValue.text = declarations
        }

        else if (feed.elements.get(position) is Package) {
            // handle package element type
            val element = (feed.elements.get(position) as Package)

            val id = element.id
            val channel = element.channel
            val keyname = element.keyname
            val name = element.name
            val pname = element.pname
            val version = element.version
            val system = element.system
            val available = element.available
            val description = element.description
            val downloadPage = element.downloadPage
            val homepage = element.homepage
            val license = "Full name : ${element.license.fullName}\nShort name : ${element.license.shortName}\n" +
                                    "URL : ${element.license.url}\nFree : ${element.license.free}"

            var maintainers: String = ""
            if (element.maintainers.isNotEmpty()) {
                when (element.maintainers.size) {
                    1 -> {
                        maintainers = "Name : ${element.maintainers[0].name}\n" +
                                        "Email : ${element.maintainers[0].email}\n" +
                                        "Github : ${element.maintainers[0].github}\n" +
                                        "Github id : ${element.maintainers[0].githubId}"
                    }
                    else -> {
                        for (iterator in element.maintainers.indices) {
                            when (iterator) {
                                0 -> {
                                    maintainers = "Name : ${element.maintainers[iterator].name}\n" +
                                                    "Email : ${element.maintainers[iterator].email}\n" +
                                                    "Github : ${element.maintainers[iterator].github}\n" +
                                                    "Github id : ${element.maintainers[iterator].githubId}\n"
                                }
                                element.maintainers.size - 1 -> {
                                    maintainers = "$maintainers\nName : ${element.maintainers[iterator].name}\n" +
                                                    "Email : ${element.maintainers[iterator].email}\n" +
                                                    "Github : ${element.maintainers[iterator].github}\n" +
                                                    "Github id : ${element.maintainers[iterator].githubId}"
                                }
                                else -> {
                                    maintainers = "$maintainers\nName : ${element.maintainers[iterator].name}\n" +
                                                    "Email : ${element.maintainers[iterator].email}\n" +
                                                    "Github : ${element.maintainers[iterator].github}\n" +
                                                    "Github id : ${element.maintainers[iterator].githubId}\n"
                                }
                            }
                        }
                    }
                }
            }

            var outputsToInstall: String = ""
            if (element.outputsToInstall.isNotEmpty()) {
                when (element.outputsToInstall.size) {
                    1 -> outputsToInstall = element.outputsToInstall[0]
                    else -> {
                        for (iterator in element.outputsToInstall.indices) {
                            when (iterator) {
                                0 -> outputsToInstall = element.outputsToInstall[iterator]
                                else -> outputsToInstall = "$outputsToInstall\n${element.outputsToInstall[iterator]}"
                            }
                        }
                    }
                }
            }

            var platforms: String = ""
            if (element.platforms.isNotEmpty()) {
                for (platform in element.platforms) {
                    platforms = "$platforms \n $platform"
                }

                when (element.platforms.size) {
                    1 -> platforms = element.platforms[0]
                    else -> {
                        for (iterator in element.platforms.indices) {
                            when (iterator) {
                                0 -> platforms = element.platforms[iterator]
                                else -> platforms = "$platforms\n${element.platforms[iterator]}"
                            }
                        }
                    }
                }
            }
            val position = element.position

            holder.view.idValue.text = id.toString()
            holder.view.channelValue.text = channel
            holder.view.package_keynameValue.text = keyname
            holder.view.nameValue.text = name
            holder.view.pnameValue.text = pname
            holder.view.versionValue.text = version
            holder.view.systemValue.text = system
            holder.view.availableValue.text = available.toString()
            holder.view.package_descriptionValue.text = description
            holder.view.downloadPageValue.text = downloadPage
            holder.view.homePageValue.text = homepage
            holder.view.licenseValue.text = license
            holder.view.maintainersValue.text = maintainers
            holder.view.outputsToInstallValue.text = outputsToInstall
            holder.view.platformsValue.text = platforms
            holder.view.positionValue.text = position
        }
    }
}