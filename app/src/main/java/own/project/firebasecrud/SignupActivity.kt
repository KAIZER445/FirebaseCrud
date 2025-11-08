package own.project.firebasecrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import own.project.firebasecrud.databinding.ActivityLoginBinding

class SignupActivity : AppCompatActivity() {

    lateinit var viewBinding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
    }
}