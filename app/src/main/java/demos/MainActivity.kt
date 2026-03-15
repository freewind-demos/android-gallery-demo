package demos

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Gallery
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Gallery 图片画廊演示
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gallery = findViewById<Gallery>(R.id.gallery)
        val items = arrayOf("图片1", "图片2", "图片3", "图片4", "图片5")
        gallery.adapter = ArrayAdapter(this, android.R.layout.simple_gallery_item, items)
        gallery.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, "选择了: ${items[position]}", Toast.LENGTH_SHORT).show()
        }
    }
}
