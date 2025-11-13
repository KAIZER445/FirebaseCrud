package own.project.firebasecrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import own.project.firebasecrud.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var loginBinding: ActivityLoginBinding

    val auth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        val view = loginBinding.root
        setContentView(view)


        loginBinding.login.setOnClickListener {

            val userEmail = loginBinding.editTextEmailSignIn.text.toString()
            val userPassword = loginBinding.editTextPasswordSignIn.text.toString()

            signInWithFirebase(userEmail, userPassword)
        }

        loginBinding.signup.setOnClickListener {

            val intent = Intent(this@LoginActivity,SignupActivity::class.java)
            startActivity(intent)
        }

        loginBinding.buttonForgot.setOnClickListener {
            val intent = Intent(this@LoginActivity, ForgetActivity::class.java)
            startActivity(intent)
        }
    }

    fun signInWithFirebase(userEmail : String, userPassword : String){
        auth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                    Toast.makeText(applicationContext,"login successful",Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext,task.exception?.toString(),Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onStart() {
        super.onStart()

        val user = auth.currentUser

        if (user != null){
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
