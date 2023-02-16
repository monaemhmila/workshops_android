package com.example.curriculumvitaeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.util.Patterns.*
import android.view.View
import android.widget.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val next = findViewById<Button>(R.id.next_button)
        val reset = findViewById<Button>(R.id.reset_button)
        var fullname = findViewById<TextView>(R.id.editTextTextPersonName)
        val age = findViewById<TextView>(R.id.editTextNumber)
        val email = findViewById<TextView>(R.id.editTextEmail)
        val radiogp = findViewById<RadioGroup>(R.id.radioGroup)
        val homme = findViewById<RadioButton>(R.id.radioButton_homme)

        next.setOnClickListener(this)

        email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (EMAIL_ADDRESS.matcher(email.text.toString()).matches())
                    next.isEnabled = true
                else {
                    next.isEnabled = false
                    email.setError("Check your email!")
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        age.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(age.text.toString().length <3){
                    next.isEnabled=true
                }
                else{
                    next.isEnabled=false
                    age.setError("Check your age ! ")
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        val android_skill=findViewById<SeekBar>(R.id.seekBar_android)
        val ios_skill=findViewById<SeekBar>(R.id.seekBar_ios)
        val flutter_skill=findViewById<SeekBar>(R.id.seekBar_flutter)

        reset.setOnClickListener(View.OnClickListener {
            fullname.setText("")
            age.setText("")
            email.setText("")
            android_skill.setProgress(0,true)
            flutter_skill.setProgress(0,true)
            ios_skill.setProgress(0,true)
            radiogp.clearCheck()
            homme.isChecked=true


        })
    }

    override fun onClick(p0: View?) {
        val fullname = findViewById<TextView>(R.id.editTextTextPersonName)
        val age = findViewById<TextView>(R.id.editTextNumber)
        val email = findViewById<TextView>(R.id.editTextEmail)
        val android_skill=findViewById<SeekBar>(R.id.seekBar_android)
        val ios_skill=findViewById<SeekBar>(R.id.seekBar_ios)
        val flutter_skill=findViewById<SeekBar>(R.id.seekBar_flutter)
        if (fullname.text.toString().isEmpty()) {
            Toast.makeText(applicationContext, "Check your name !", Toast.LENGTH_SHORT).show()
        } else if (age.text.toString().isEmpty()) {
            Toast.makeText(applicationContext, "Check your age !", Toast.LENGTH_SHORT).show()
        } else if (!EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
            Toast.makeText(applicationContext, "Check your email !", Toast.LENGTH_SHORT).show()
        } else if (android_skill.progress >= 80 && ios_skill.progress < 80 && flutter_skill.progress < 80) {
            Toast.makeText(applicationContext, "Vous êtes excellent en Android", Toast.LENGTH_SHORT)
                .show()
        }
        else if(ios_skill.progress >=80 && android_skill.progress < 80 && flutter_skill.progress < 80){
                Toast.makeText(applicationContext, "Vous êtes excellent en IOS", Toast.LENGTH_SHORT).show()
            }
        else if (flutter_skill.progress >=80 && android_skill.progress < 80 && ios_skill.progress < 80){
                Toast.makeText(applicationContext, "Vous êtes excellent en Flutter", Toast.LENGTH_SHORT).show()
            }

        else {
            when(android_skill.progress and ios_skill.progress and flutter_skill.progress ){
                in 80..100 -> Toast.makeText(applicationContext, "Vous avez d'excellent skills", Toast.LENGTH_SHORT)
                    .show()
                in 0 .. 30 -> Toast.makeText(applicationContext, "Vous devez traviller vos skills", Toast.LENGTH_SHORT)
                    .show()
                else -> Toast.makeText(applicationContext, "Vous avez de bons skills", Toast.LENGTH_SHORT)
                    .show()
            }

        }

    }
}