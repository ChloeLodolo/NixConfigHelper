package couclou.fr.nixconfighelper

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView_main.layoutManager = LinearLayoutManager(this)
        //fetchJson()

        val searchBox = findViewById<EditText>(R.id.searchBox)
        searchBox.setOnEditorActionListener { v, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    // clear focus on searchBox and remove soft keyboard
                    searchBox.clearFocus()
                    val view = this.currentFocus
                    view?.let { v ->
                        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                        imm?.hideSoftInputFromWindow(v.windowToken, 0)
                    }

                    // fetch data accordingly to query and checkboxes
                    val query: String = searchBox.text.toString()
                    val optionsCheckBox = findViewById<CheckBox>(R.id.optionsCheckbox)
                    val packagesCheckBox = findViewById<CheckBox>(R.id.packagesCheckbox)

                    if (!optionsCheckBox.isChecked && !packagesCheckBox.isChecked) {
                        // toaster pour indiquer qu'il faut sélectionner au moins un des deux
                    }
                    else if (optionsCheckBox.isChecked && !packagesCheckBox.isChecked) {
                        fetchJson(query, "options/")
                    }
                    else if (!optionsCheckBox.isChecked && packagesCheckBox.isChecked) {
                        fetchJson(query, "packages/")
                    }
                    else if (optionsCheckBox.isChecked && packagesCheckBox.isChecked) {
                        fetchJson(query,"")
                    }

                    true
                }
                else -> false
            }
        }
    }

    private fun fetchJson(query: String, library: String) {
        val url = "https://nix-config.nicolasguilloux.eu/api/channels/nixos-19.09/$library/search?query=$query"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                println(body)

                val gson = GsonBuilder().registerTypeAdapter(Element::class.java, ElementAdapter()).create()
                val feed = gson.fromJson(body,Feed::class.java)
                runOnUiThread {
                    recyclerView_main.adapter = MainAdapter(feed)
                }
            }
        })
    }
}