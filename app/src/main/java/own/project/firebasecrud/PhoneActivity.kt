package own.project.firebasecrud

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import own.project.firebasecrud.databinding.ActivityPhoneBinding

class PhoneActivity : AppCompatActivity() {

    lateinit var PhoneBinding: ActivityPhoneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        PhoneBinding = ActivityPhoneBinding.inflate(layoutInflater)
        val view = PhoneBinding.root
        setContentView(view)

        PhoneBinding.buttonSendSMScode.setOnClickListener {

        }

        PhoneBinding.buttonVerify.setOnClickListener {

        }
    }
}