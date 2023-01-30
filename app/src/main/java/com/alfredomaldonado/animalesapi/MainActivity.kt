package com.alfredomaldonado.animalesapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfredomaldonado.animalesapi.databinding.ActivityMainBinding
import com.alfredomaldonado.animalesapi.server.RemoteConnection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: AnimalAdapter
    private val animalImages = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchAnimal.setOnQueryTextListener(this)
        setupRecycler()

        //setContentView(R.layout.activity_main)
    }

    private fun setupRecycler() {
        mAdapter = AnimalAdapter(animalImages)
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mAdapter
        }
    }

    //llamar a retrofit
    private fun searchByAnimal(query: String){
        CoroutineScope(Dispatchers.IO).launch {
        val call = RemoteConnection.service.getAnimalByBreeds("$query/images")

        val result = call.body()
        runOnUiThread{ //salimos de las coroutines
            if(call.isSuccessful){
                val imgList =result?.imagen ?: emptyList()  //lista con datos vacía
                animalImages.clear()
                animalImages.addAll(imgList)
                mAdapter.notifyDataSetChanged()
            }else{
                Toast.makeText(this@MainActivity, "hay un error", Toast.LENGTH_SHORT).show()
            }
        }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            searchByAnimal(query.toLowerCase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
       return true
    }
}