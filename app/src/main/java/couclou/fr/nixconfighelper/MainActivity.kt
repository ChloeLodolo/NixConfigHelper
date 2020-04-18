package couclou.fr.nixconfighelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.elements_row.view.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val searchButton = findViewById<Button>(R.id.searchButton)
        /*searchButton.setOnClickListener{
            //Toast.makeText(this, R.string.noResourceFound, Toast.LENGTH_LONG).show()
        }*/

        recyclerView_main.layoutManager = LinearLayoutManager(this)
        fetchJson()
    }

    fun fetchJson() {
        println("Attempting to fetch Json")
        val url = "https://nix-config.nicolasguilloux.eu/api/channels/nixos-19.09/options/search?query=boot"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                println(body)

                val gson = GsonBuilder().create()
                val feed = gson.fromJson(body,Feed::class.java)
                runOnUiThread {
                    recyclerView_main.adapter = MainAdapter(feed)
                }
            }
        })
    }
}

class Feed(val page: Int, val count: Int, val totalPages: Int, val totalCount: Int, val elements:List<Option>)
class Option(val keyname: String, val type: String, val readOnly: Boolean, val defaultValue: String, val description: String, val declarations: List<String>)

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
        val keyname = feed.elements.get(position).keyname
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
        for (declaration in feed.elements.get(position).declarations) {
            declarations = "$declarations \n $declaration"
        }
        holder.view.declarationsValue.text = declarations
    }

}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {

}
