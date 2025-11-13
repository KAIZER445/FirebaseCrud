package own.project.firebasecrud

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import own.project.firebasecrud.databinding.ActivityForgetBinding

class ForgetActivity : AppCompatActivity() {

    lateinit var forgetBinding: ActivityForgetBinding

    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        forgetBinding = ActivityForgetBinding.inflate(layoutInflater)
        val view = forgetBinding.root
        setContentView(view)

        forgetBinding.buttonRest.setOnClickListener {

            val email = forgetBinding.editTextReset.text.toString()

            auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        applicationContext, "we sent a password reset mail to your mail address",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                } else {
                    val errorMessage = when (val exception = task.exception) {
                        is FirebaseAuthInvalidUserException -> {
                            "No account found with that email address"
                        }

                        is FirebaseAuthInvalidCredentialsException -> {
                            "Invalid email format"
                        }

                        else -> {
                            exception?.localizedMessage ?: "Something went wrong"
                        }
                    }

                    Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}    