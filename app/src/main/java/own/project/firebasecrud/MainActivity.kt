package own.project.firebasecrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import own.project.firebasecrud.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding : ActivityMainBinding

    val database :FirebaseDatabase = FirebaseDatabase.getInstance()
    val myReference :DatabaseReference = database.reference.child("MyUsers")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding =  ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        mainBinding.floatingActionButton2.setOnClickListener{
            val intent = Intent(this, AddUserActivity::class.java)
            startActivity(intent)
        }

        retrieveDataFromDatabase()
    }


    fun retrieveDataFromDatabase(){
        myReference.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot){

                for(eachUser in snapshot.children){
                    val user = eachUser.getValue(Users::class.java)

                    if (user!=null){
                        println("UserID = ${user.userId}")
                        println("UserName = ${user.userName}")
                        println("UserAge = ${user.userAge}")
                        println("UserEmail = ${user.userEmail}")
                        println("*************************")
                    }
                }
            }

            override fun onCancelled(error: DatabaseError){

            }
        })
    }
}