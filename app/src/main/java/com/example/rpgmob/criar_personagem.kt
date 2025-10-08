package com.example.rpgmob

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.compareTo
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.widget.Toolbar

class criar_personagem : AppCompatActivity() {
    private var aparenciaSelecionada: Int = -1
    lateinit var botao_voltar: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_personagem)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // --- EXISTENTE: Configurar os cliques dos botões de aparência ---
        val btnAparencia1 = findViewById<ImageButton>(R.id.imgAparencia1)
        val btnAparencia2 = findViewById<ImageButton>(R.id.imgAparencia2)
        val btnAparencia3 = findViewById<ImageButton>(R.id.imgAparencia3)
        val btnAparencia4 = findViewById<ImageButton>(R.id.imgAparencia4)
        val btnAparencia5 = findViewById<ImageButton>(R.id.imgAparencia5)

        val botoesAparencia = listOf(btnAparencia1, btnAparencia2, btnAparencia3, btnAparencia4, btnAparencia5)

        btnAparencia1.setOnClickListener { selecionarAparencia(0, botoesAparencia) }
        btnAparencia2.setOnClickListener { selecionarAparencia(1, botoesAparencia) }
        btnAparencia3.setOnClickListener { selecionarAparencia(2, botoesAparencia) }
        btnAparencia4.setOnClickListener { selecionarAparencia(3, botoesAparencia) }
        btnAparencia5.setOnClickListener { selecionarAparencia(4, botoesAparencia) }

        // --- NOVO: Referências para TextViews (valores) e ImageButtons (rolar dados) ---
        // Certifique-se que esses IDs existem no seu XML:
        // txtValorForca, txtValorDefesa, txtValorDestreza
        // btnRolarForca, btnRolarDefesa, btnRolarDestreza

        val txtForca = findViewById<TextView>(R.id.txtValorForca)
        val txtDefesa = findViewById<TextView>(R.id.txtValorDefesa)
        val txtDestreza = findViewById<TextView>(R.id.txtValorDestreza)
        val txtVida = findViewById<TextView>(R.id.txtValorVida)

        val btnForca = findViewById<ImageButton>(R.id.btnRolarForca)
        val btnDefesa = findViewById<ImageButton>(R.id.btnRolarDefesa)
        val btnDestreza = findViewById<ImageButton>(R.id.btnRolarDestreza)
        val btnVida = findViewById<ImageButton>(R.id.btnRolarVida)

        botao_voltar = findViewById(R.id.btnBack)
        botao_voltar.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val classes = findViewById<AutoCompleteTextView>(R.id.classes)
        escolherClasses(classes)

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

        btnVida.setOnClickListener { view ->
            val vidaBase = 20
            val bonusVida = rollD20()
            val vidaTotal = vidaBase + bonusVida

            txtVida.text = vidaTotal.toString()
            view.isEnabled = false
            view.animate().rotationBy(360f).setDuration(300).start()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }
    // Função utilitária para gerar um d20 (1..20)
    private fun rollD20(): Int = Random.nextInt(1, 21)

    fun escolherClasses(classes: AutoCompleteTextView){
        val classes_array = resources.getStringArray(R.array.classes_array)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,classes_array)
        classes.setAdapter(adapter)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.resetar_atributo -> {
                val atributos = listOf<TextView>(
                    findViewById<TextView>(R.id.txtValorForca),
                    findViewById<TextView>(R.id.txtValorDefesa),
                    findViewById<TextView>(R.id.txtValorDestreza),
                    findViewById<TextView>(R.id.txtValorVida)
                )
                val butoes = listOf<ImageButton>(
                    findViewById<ImageButton>(R.id.btnRolarForca),
                    findViewById<ImageButton>(R.id.btnRolarDefesa),
                    findViewById<ImageButton>(R.id.btnRolarDestreza),
                    findViewById<ImageButton>(R.id.btnRolarVida)
                )
                atributos.forEach { it ->
                    it.text = 0.toString()
                }
                butoes.forEach { it ->
                    it.isEnabled = true
                }
                true
            }
            else -> {
                Toast.makeText(this, "Quero fazer o L", Toast.LENGTH_SHORT).show()
                false
            }
        }
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
