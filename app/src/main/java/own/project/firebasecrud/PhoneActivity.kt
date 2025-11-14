package own.project.firebasecrud

import android.content.Intent
import android.credentials.Credential
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import own.project.firebasecrud.databinding.ActivityPhoneBinding
import java.util.concurrent.TimeUnit

class PhoneActivity : AppCompatActivity() {

    lateinit var PhoneBinding: ActivityPhoneBinding
    val auth : FirebaseAuth = FirebaseAuth.getInstance()

    var verificationCode = ""
    lateinit var mCallbacks : PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        PhoneBinding = ActivityPhoneBinding.inflate(layoutInflater)
        setContentView(PhoneBinding.root)

        // Initialize Callbacks FIRST
        mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {}

            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(this@PhoneActivity, e.message, Toast.LENGTH_LONG).show()
            }

            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                verificationCode = verificationId
            }
        }

        // Only ONE click listener
        PhoneBinding.buttonSendSMScode.setOnClickListener {
            val raw = PhoneBinding.editTextPhoneNumber.text.toString()
            val userPhoneNumber = "+977$raw"

            val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(userPhoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this@PhoneActivity)
                .setCallbacks(mCallbacks)
                .build()

            PhoneAuthProvider.verifyPhoneNumber(options)
        }

        PhoneBinding.buttonVerify.setOnClickListener {
            signinWithSMSCode()
        }
    }

    fun signinWithSMSCode(){
        val userEnterCode = PhoneBinding.editTextVerificationCode.text.toString()
        val credential = PhoneAuthProvider.getCredential(verificationCode, userEnterCode)
        signinWithPhoneAuthCredentials(credential)
    }

    fun signinWithPhoneAuthCredentials(credential: PhoneAuthCredential){
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful){
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(applicationContext,"Incorrect code", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
