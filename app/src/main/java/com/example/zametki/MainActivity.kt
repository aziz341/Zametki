package com.example.zametki


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.zametki.databinding.ActivityMainBinding
import com.example.zametki.models.NotesModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    private val binding :ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val adapter:NotesAdapter by lazy {
        NotesAdapter()
    }
    override fun onResume() {
        super.onResume()
        loadData()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvNotes.adapter = adapter
        loadData()

        binding.addBtn.setOnClickListener {
            startActivity(Intent(this,AddNoteActivity::class.java))
        }
        }
    private fun loadData() {
        val sharedPref = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPref.getString("MySaveData", null)
        val type = object : TypeToken<MutableList<NotesModel>>() {}.type
        val loadNotesList : MutableList<NotesModel>? = gson.fromJson(json, type)


            adapter.notesList = loadNotesList ?: listOf()
        }
    }