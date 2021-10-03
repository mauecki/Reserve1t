package com.example.reservapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.reservapp.fragments.firstFragment
import com.example.reservapp.fragments.secondFragment
import com.example.reservapp.fragments.list.thirdFragment
import com.example.reservapp.fragments.fourthFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val firstFragment = firstFragment()
    private val secondFragment = secondFragment()
    private val thirdFragment = thirdFragment()
    private val fourthFragment = fourthFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val userId = intent.getStringExtra("user_id")
        val emailId = intent.getStringExtra("email_id")


        replaceFragment(secondFragment)
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_firstFragment -> replaceFragment(firstFragment)
                R.id.ic_secondFragment -> replaceFragment(secondFragment)
                R.id.ic_thirdFragment -> replaceFragment(thirdFragment)
                R.id.ic_fourthFragment -> replaceFragment(fourthFragment)
            }
            true
        }


/*
        tv_user_id.text = "User ID:: $userId"
        tv_email_id.text = "Email ID: $emailId"

        btn_logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        finish()
        }
  */

    }
    private fun replaceFragment(fragment: Fragment){
        if(fragment!=null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }

}