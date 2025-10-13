package com.example.rpgmob

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ImageButton
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class lista_personagens : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_personagens)

        // Configura o botão de voltar
        val btnVoltar = findViewById<ImageButton>(R.id.btnBack)
        btnVoltar.setOnClickListener {
            finish() // Volta para a tela anterior
        }

        // Carrega e exibe a lista de personagens
        carregarPersonagens()
    }


    //Recarrega lista de personagens sempre que a activity se tornar visível, para garantir que novos personagens criados apareçam na lista.
    override fun onResume() {
        super.onResume()
        carregarPersonagens()
    }

    //Carrega os personagens salvos no SharedPreferences e exibe na ListView.
    private fun carregarPersonagens() {
        val listView = findViewById<ListView>(R.id.listViewPersonagens)
        val txtSemPersonagens = findViewById<TextView>(R.id.txtSemPersonagens)

        // Recupera os IDs dos personagens salvos
        val sharedPreferences = getSharedPreferences("RPG_PERSONAGENS", MODE_PRIVATE)
        val personagensIds = sharedPreferences.getString("personagens_ids", "") ?: ""

        if (personagensIds.isEmpty()) {
            // Não há personagens salvos
            txtSemPersonagens.visibility = View.VISIBLE
            listView.visibility = View.GONE
            return
        }

        // Há personagens salvos
        txtSemPersonagens.visibility = View.GONE
        listView.visibility = View.VISIBLE

        val listaIds = personagensIds.split(",")
        val listaPersonagens = ArrayList<HashMap<String, String>>()

        // Recupera os dados de cada personagem
        for (id in listaIds) {
            val nome = sharedPreferences.getString("$id-nome", "") ?: ""
            val classe = sharedPreferences.getString("$id-classe", "") ?: ""
            val aparencia = sharedPreferences.getInt("$id-aparencia", -1)
            val forca = sharedPreferences.getString("$id-forca", "0") ?: "0"
            val defesa = sharedPreferences.getString("$id-defesa", "0") ?: "0"
            val destreza = sharedPreferences.getString("$id-destreza", "0") ?: "0"
            val vida = sharedPreferences.getString("$id-vida", "0") ?: "0"

            // Cria um HashMap com os dados do personagem
            val personagem = HashMap<String, String>()
            personagem["id"] = id
            personagem["nome"] = nome
            personagem["classe"] = "Classe: $classe"
            personagem["aparencia"] = aparencia.toString()
            personagem["atributos"] = "FOR: $forca | DEF: $defesa | DES: $destreza | VIDA: $vida"

            listaPersonagens.add(personagem)
        }

        // Cria o adaptador para a ListView
        val adapter = SimpleAdapter(
            this,
            listaPersonagens,
            R.layout.item_personagem,
            arrayOf("nome", "classe", "atributos", "aparencia"),
            intArrayOf(R.id.txtNomePersonagem, R.id.txtClasse, R.id.txtAtributos, R.id.imgAparenciaPersonagem)
        )

        // Define um ViewBinder para carregar a imagem correta baseada no índice de aparência
        adapter.setViewBinder { view, data, textRepresentation ->
            if (view.id == R.id.imgAparenciaPersonagem && textRepresentation != null) {
                val imageView = view as android.widget.ImageView
                val aparenciaIndex = textRepresentation.toIntOrNull() ?: -1

                // Carrega a imagem correspondente ao índice de aparência
                val imageResource = when (aparenciaIndex) {
                    0 -> R.drawable.imagem1
                    1 -> R.drawable.imagem2
                    2 -> R.drawable.imagem3
                    3 -> R.drawable.imagem4
                    4 -> R.drawable.imagem5
                    else -> R.drawable.imagem1 // Imagem padrão se não encontrar
                }

                imageView.setImageResource(imageResource)
                return@setViewBinder true
            }
            false
        }

        // Define o adaptador na ListView
        listView.adapter = adapter

    }
}