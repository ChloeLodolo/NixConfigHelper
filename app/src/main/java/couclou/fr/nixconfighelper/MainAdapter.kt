package couclou.fr.nixconfighelper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.elements_row.view.*

class MainAdapter(val feed:Feed): RecyclerView.Adapter<CustomViewHolder>() {
    override fun getItemCount(): Int {
        return feed.count
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.elements_row, parent, false)
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

            holder.view.keynameValue.text = keyname
            holder.view.typeValue.text = type
            holder.view.readOnlyValue.text = readOnly.toString()
            holder.view.defaultValueValue.text = defaultValue
            holder.view.descriptionValue.text = description

            var declarations: String = ""
            if (declarations.isNotEmpty()) {
                for (declaration in element.declarations) {
                    declarations = "$declarations \n $declaration"
                }
            }
            holder.view.declarationsValue.text = declarations
        }

        else if (feed.elements.get(position) is Package) {
            // handle package element type
        }
    }
}