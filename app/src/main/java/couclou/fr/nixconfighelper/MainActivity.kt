package couclou.fr.nixconfighelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchButton = findViewById<Button>(R.id.searchButton)

        searchButton.setOnClickListener{
            Toast.makeText(this, "No corresponding resource found in database", Toast.LENGTH_LONG).show()
        }
    }
}
