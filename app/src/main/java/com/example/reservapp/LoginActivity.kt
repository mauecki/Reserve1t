package com.example.reservapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val savedMail = getSharedPreference("logowanie", "email")
        val savedPassword = getSharedPreference("logowanie", "haslo")

        et_login_email.setText(savedMail)
        et_login_password.setText(savedPassword)

        tv_register.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        btn_login.setOnClickListener {

            setSharedPreference("logowanie","email", et_login_email.text.toString())
            setSharedPreference("logowanie","haslo", et_login_password.text.toString())

            when{
                TextUtils.isEmpty(et_login_email.text.toString().trim{ it <= ' '}) ->{
                    Toast.makeText(
                        this@LoginActivity,
                        "Please enter email",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(et_login_password.text.toString().trim { it<= ' ' }) -> {
                    Toast.makeText(
                        this@LoginActivity,
                        "Please enter password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    val email: String = et_login_email.text.toString().trim { it <= ' ' }
                    val password: String = et_login_password.text.toString().trim {it <= ' '}


                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                                if (task.isSuccessful){

                                    Toast.makeText(
                                        this@LoginActivity,
                                        "You are Logged succesfully",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    val intent = Intent(this@LoginActivity, MainActivity::class.java)

                                    intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                                    intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                                    setSharedPreference("logowanie","id", FirebaseAuth.getInstance().currentUser!!.uid)
                                    intent.putExtra("email_id", email)

                                    startActivity(intent)

                                    finish()
                                }else{
                                    Toast.makeText(
                                        this@LoginActivity,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                }
            }

        }
    }

    fun Context.setSharedPreference(prefsName: String, key: String, value: String) {
        getSharedPreferences(prefsName, Context.MODE_PRIVATE)
            .edit().apply { putString(key, value); apply() }
    }

    fun Context.getSharedPreference(prefsName: String, key: String): String {
        getSharedPreferences(prefsName, Context.MODE_PRIVATE)
            ?.getString(key, "Put your email!")?.let { return it }
        return "Preference doesn't exist."
    }

}