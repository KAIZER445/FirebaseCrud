package own.project.firebasecrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import own.project.firebasecrud.databinding.ActivityUpdateUserBinding

class UpdateUserActivity : AppCompatActivity() {

    lateinit var updateUserBinding: ActivityUpdateUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateUserBinding = ActivityUpdateUserBinding.inflate(layoutInflater)
        val view = updateUserBinding.root
        setContentView(view)

        getAndSetData()
    }

    fun getAndSetData(){

        val name = intent.getStringExtra("name")
        val age = intent.getIntExtra("age",0).toString()
        val email = intent.getStringExtra("email")


        updateUserBinding.editTextUpdateName.setText(name)
        updateUserBinding.editTextUpdateEmail.setText(email)
        updateUserBinding.editTextUpdateAge.setText(age)

    }

}