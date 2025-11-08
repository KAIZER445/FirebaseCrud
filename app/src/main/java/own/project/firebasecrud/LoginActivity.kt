package own.project.firebasecrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import own.project.firebasecrud.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var loginBinding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        val view = loginBinding.root
        setContentView(view)


        loginBinding.login.setOnClickListener {

        }

        loginBinding.signup.setOnClickListener {

            val intent = Intent(this@LoginActivity,SignupActivity::class.java)
            startActivity(intent)

        }
    }
}