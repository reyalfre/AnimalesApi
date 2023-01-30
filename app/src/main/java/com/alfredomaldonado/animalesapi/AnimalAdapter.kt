package com.alfredomaldonado.animalesapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alfredomaldonado.animalesapi.databinding.ItemAnimalBinding
import com.bumptech.glide.Glide

class AnimalAdapter(private val imgList: List<String>): RecyclerView.Adapter<AnimalAdapter.ViewHoder>() {


    inner class ViewHoder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ItemAnimalBinding.bind(view)

        fun bind(image: String){
            Glide.with(binding.imgAnimal)
                .load(image)
                .into(binding.imgAnimal)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_animal, parent, false)

        return ViewHoder(view)
    }

    override fun onBindViewHolder(holder: ViewHoder, position: Int) {
        val animal = imgList.get(position)
        holder.bind(animal)
        //holder.setListener(animal)
    }

    override fun getItemCount(): Int {
        return imgList.size
    }
}