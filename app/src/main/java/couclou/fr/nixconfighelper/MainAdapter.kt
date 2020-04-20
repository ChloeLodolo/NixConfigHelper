package couclou.fr.nixconfighelper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

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
        //println(feed.elements)
        /*val keyname = feed.elements.get(position).keyname
        val type = feed.elements.get(position).type
        val readOnly = feed.elements.get(position).readOnly
        val defaultValue = feed.elements.get(position).defaultValue
        var description = feed.elements.get(position).description.replace("\n","")

        holder.view.keynameValue.text = keyname
        holder.view.typeValue.text = type
        holder.view.readOnlyValue.text = readOnly.toString()
        holder.view.defaultValueValue.text = defaultValue
        holder.view.descriptionValue.text = description

        var declarations: String = ""
        if (declarations.isNotEmpty()) {
            for (declaration in feed.elements.get(position).declarations) {
                declarations = "$declarations \n $declaration"
            }
        }
        holder.view.declarationsValue.text = declarations*/
    }
}