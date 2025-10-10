package com.example.rpgmob

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val criarbtn = findViewById<Button>(R.id.btnCreate)
        criarbtn.setOnClickListener {
            startActivity(Intent(this, criar_personagem::class.java))
        }
        val btnListaPersonagens = findViewById<Button>(R.id.btnListaPersonagens)
        btnListaPersonagens.setOnClickListener {
            startActivity(Intent(this, lista_personagens::class.java))
        }
    }

}