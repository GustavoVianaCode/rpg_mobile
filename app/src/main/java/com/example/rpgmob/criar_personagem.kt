package com.example.rpgmob

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.compareTo

class criar_personagem : AppCompatActivity() {
    private var aparenciaSelecionada: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_personagem)

        // Configurar os cliques dos botões de aparência
        val btnAparencia1 = findViewById<ImageButton>(R.id.imgAparencia1)
        val btnAparencia2 = findViewById<ImageButton>(R.id.imgAparencia2)
        val btnAparencia3 = findViewById<ImageButton>(R.id.imgAparencia3)
        val btnAparencia4 = findViewById<ImageButton>(R.id.imgAparencia4)

        // Lista de todos os botões de aparência para facilitar a manipulação
        val botoesAparencia = listOf(btnAparencia1, btnAparencia2, btnAparencia3, btnAparencia4)

        // Configurar os listeners de clique
        btnAparencia1.setOnClickListener { selecionarAparencia(0, botoesAparencia) }
        btnAparencia2.setOnClickListener { selecionarAparencia(1, botoesAparencia) }
        btnAparencia3.setOnClickListener { selecionarAparencia(2, botoesAparencia) }
        btnAparencia4.setOnClickListener { selecionarAparencia(3, botoesAparencia) }
    }
    private fun selecionarAparencia(index: Int, botoes: List<ImageButton>) {
        // Desmarcar o botão anteriormente selecionado
        if (aparenciaSelecionada != -1 && aparenciaSelecionada < botoes.size) {
            botoes[aparenciaSelecionada].isSelected = false
        }

        // Selecionar o novo botão
        aparenciaSelecionada = index
        botoes[aparenciaSelecionada].isSelected = true

        // Aqui você pode adicionar lógica adicional, como salvar a escolha do usuário
    }
}




