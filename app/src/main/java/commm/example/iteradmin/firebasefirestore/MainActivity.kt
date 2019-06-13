package commm.example.iteradmin.firebasefirestore

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class MainActivity : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = FirebaseFirestore.getInstance()
        val name = findViewById<EditText>(R.id.name)
        val branch = findViewById<EditText>(R.id.branch)
        val city = findViewById<EditText>(R.id.city)
        val save = findViewById<Button>(R.id.save)
        val data = findViewById<TextView>(R.id.data)
        val load = findViewById<Button>(R.id.load)
        save.setOnClickListener {

            val name: String = name.text.toString()
            val city: String = city.text.toString()
            val branch: String = branch.text.toString()
            val map = hashMapOf(
                    "name" to name,
                    "city" to city,
                    "branch" to branch
            )
            db.collection("users").add(map).addOnSuccessListener {
                Toast.makeText(this, "add to fire store", Toast.LENGTH_LONG).show()
            }
                    .addOnFailureListener {
                        Toast.makeText(this, "something went wrong", Toast.LENGTH_LONG).show()
                    }
        }
        load.setOnClickListener {

            db.collection("users")
                    .get()
                    .addOnSuccessListener { querySnapshot ->
                        for (d in querySnapshot.documents){
                        val name: String = d.data?.get("name").toString()
                        Toast.makeText(this, name, Toast.LENGTH_LONG).show()
                    }
        }
                    .addOnFailureListener {  }
        }
        }

    }

