package own.project.firebasecrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import own.project.firebasecrud.databinding.ActivityLoginBinding
import own.project.firebasecrud.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    lateinit var signupBinding: ActivitySignupBinding

    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signupBinding = ActivitySignupBinding.inflate(layoutInflater)
        val view = signupBinding.root
        setContentView(view)


        signupBinding.signupUser.setOnClickListener {

            val userEmail = signupBinding.editTextEmailSignUp.text.toString()
            val userPassword = signupBinding.editTextPasswordSignUp.text.toString()

            signUpWithFirebase(userEmail,userPassword)
        }
    }


    fun signUpWithFirebase(userEmail:String, userPassword:String){

        auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener{task->
            if (task.isSuccessful){
                Toast.makeText(applicationContext,"your account is created successfully",Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(applicationContext,task.exception?.toString(),Toast.LENGTH_SHORT).show()
            }
        }

    }
}