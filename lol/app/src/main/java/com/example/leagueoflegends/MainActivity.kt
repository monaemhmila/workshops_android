package com.example.leagueoflegends

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Button

import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.internal.ToolbarUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<MaterialToolbar>(R.id.topAppBar)
        setSupportActionBar(toolbar)
        val btnChampion = findViewById<Button>(R.id.btnChampions)
        var btnSpells = findViewById<Button>(R.id.btnSpells)

        btnSpells.setOnClickListener {
            changeFragement(Spells(),"")
        }
        btnChampion.setOnClickListener {
            changeFragement(champions(),"")
        }
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView,champions()).commit()
    }

    private fun changeFragement(fragment: Fragment, name: String) {
        if (name.isEmpty())
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment)
                .commit()
        else {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment)
                .addToBackStack(name).commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_app_bar,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.info -> changeFragement(about(),"about")
        }
        return super.onOptionsItemSelected(item)
    }
}