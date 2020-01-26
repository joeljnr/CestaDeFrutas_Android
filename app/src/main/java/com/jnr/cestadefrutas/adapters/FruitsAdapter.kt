package com.jnr.cestadefrutas.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.jnr.cestadefrutas.R
import com.jnr.cestadefrutas.models.Fruit
import com.jnr.cestadefrutas.viewmodels.FruitsViewModel
import com.jnr.cestadefrutas.views.MainActivity
import kotlinx.android.synthetic.main.row_fruit.view.*

class FruitsAdapter internal constructor(context: Context): RecyclerView.Adapter<FruitViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var fruits = emptyList<Fruit>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
        val cellForRow = inflater.inflate(R.layout.row_fruit, parent, false)
        return FruitViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return fruits.size
    }

    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        holder.view.text_fruit.text = "${position + 1} - ${fruits[position].name}"
        holder.fruit = fruits[position]
    }

    internal fun setFruits(fruits: List<Fruit>) {
        this.fruits = fruits
        notifyDataSetChanged()
    }

}

class FruitViewHolder(var view: View, var fruit: Fruit? = null): RecyclerView.ViewHolder(view) {

    var act = view.context as MainActivity

    var fruitViewModel = ViewModelProviders.of(act).get(FruitsViewModel::class.java)

    init {
        view.setOnClickListener {
            if (MainActivity.isDeleting) {
                val builder = AlertDialog.Builder(act)
                builder.setTitle("Exemplo de delete")
                builder.setMessage("Tem certeza que deseja excluir ${fruit!!.name}?")
                builder.setPositiveButton("Sim") { dialogInterface, i ->
                    fruitViewModel.delete(fruit!!)
                    Toast.makeText(view.context, "${fruit!!.name} deletado", Toast.LENGTH_LONG).show()
                }

                builder.setNegativeButton("Cancelar", null)
                builder.show()

            }
        }
    }
}