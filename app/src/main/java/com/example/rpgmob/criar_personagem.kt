package com.example.rpgmob

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.compareTo
import androidx.activity.enableEdgeToEdge

class criar_personagem : AppCompatActivity() {
    private var aparenciaSelecionada: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_personagem)

        // --- EXISTENTE: Configurar os cliques dos botões de aparência ---
        val btnAparencia1 = findViewById<ImageButton>(R.id.imgAparencia1)
        val btnAparencia2 = findViewById<ImageButton>(R.id.imgAparencia2)
        val btnAparencia3 = findViewById<ImageButton>(R.id.imgAparencia3)
        val btnAparencia4 = findViewById<ImageButton>(R.id.imgAparencia4)

        val botoesAparencia = listOf(btnAparencia1, btnAparencia2, btnAparencia3, btnAparencia4)

        btnAparencia1.setOnClickListener { selecionarAparencia(0, botoesAparencia) }
        btnAparencia2.setOnClickListener { selecionarAparencia(1, botoesAparencia) }
        btnAparencia3.setOnClickListener { selecionarAparencia(2, botoesAparencia) }
        btnAparencia4.setOnClickListener { selecionarAparencia(3, botoesAparencia) }

        // --- NOVO: Referências para TextViews (valores) e ImageButtons (rolar dados) ---
        // Certifique-se que esses IDs existem no seu XML:
        // txtValorForca, txtValorDefesa, txtValorDestreza
        // btnRolarForca, btnRolarDefesa, btnRolarDestreza

        val txtForca = findViewById<TextView>(R.id.txtValorForca)
        val txtDefesa = findViewById<TextView>(R.id.txtValorDefesa)
        val txtDestreza = findViewById<TextView>(R.id.txtValorDestreza)

        val btnForca = findViewById<ImageButton>(R.id.btnRolarForca)
        val btnDefesa = findViewById<ImageButton>(R.id.btnRolarDefesa)
        val btnDestreza = findViewById<ImageButton>(R.id.btnRolarDestreza)

        // Listener para Força
        btnForca.setOnClickListener { view ->
            val n = rollD20()
            txtForca.text = n.toString()
            // desabilita o botão para evitar múltiplos rolls (opcional)
            view.isEnabled = false
            // animação simples
            view.animate().rotationBy(360f).setDuration(300).start()
        }

        // Listener para Defesa
        btnDefesa.setOnClickListener { view ->
            val n = rollD20()
            txtDefesa.text = n.toString()
            view.isEnabled = false
            view.animate().rotationBy(360f).setDuration(300).start()
        }

        // Listener para Destreza
        btnDestreza.setOnClickListener { view ->
            val n = rollD20()
            txtDestreza.text = n.toString()
            view.isEnabled = false
            view.animate().rotationBy(360f).setDuration(300).start()
        }
    }

    // Função utilitária para gerar um d20 (1..20)
    private fun rollD20(): Int = Random.nextInt(1, 21)

    /*
    // Se preferir 3d6 (atributos clássicos 3..18), substitua rollD20() por:
    private fun roll3d6(): Int = List(3) { Random.nextInt(1, 7) }.sum()
    */

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
