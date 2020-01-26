package com.jnr.cestadefrutas.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jnr.cestadefrutas.R
import com.jnr.cestadefrutas.adapters.FruitsAdapter
import com.jnr.cestadefrutas.models.Fruit
import com.jnr.cestadefrutas.viewmodels.FruitsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var fabAdd: FloatingActionButton
    private lateinit var fabDelete: FloatingActionButton
    private lateinit var recyclerFruits: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var adapter: FruitsAdapter
    private lateinit var fruitsViewModel: FruitsViewModel

    companion object {
        var isDeleting = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fabAdd = findViewById(R.id.fab_add)
        fabDelete = findViewById(R.id.fab_delete)

        fruitsViewModel = ViewModelProviders.of(this).get(FruitsViewModel::class.java)

        fruitsViewModel.allFruits.observe(this, Observer {
            adapter.setFruits(it)
        })

        initRecyclerView()
    }

    fun initRecyclerView() {
        viewManager = LinearLayoutManager(this)

        recyclerFruits = findViewById<RecyclerView>(R.id.recycler_fruits).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
        }

        adapter = FruitsAdapter(this)
        recyclerFruits.adapter = adapter
    }

    fun addFruit(view: View) {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        builder.setTitle("Exemplo de insert")
        builder.setMessage("Insira um nome para a fruta a ser adicionada a cesta: ")
        val dialogLayout = inflater.inflate(R.layout.alert_add, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.edit_text_add)
        builder.setView(dialogLayout)
        builder.setPositiveButton("Salvar") { dialogInterface, i ->
            fruitsViewModel.insert(Fruit(editText.text.toString()))
        }

        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }

    fun deleteFruit(view: View) {
        if (isDeleting) {
            fabDelete.setImageResource(R.drawable.ic_delete)
            isDeleting = false
        } else {
            Toast.makeText(this, "Selecione uma fruta para ser excluída", Toast.LENGTH_LONG).show()
            fabDelete.setImageResource(R.drawable.ic_cancel)
            isDeleting = true
        }
    }
}
