package com.example.zametki

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.zametki.databinding.ActivityAddNoteBinding
import com.example.zametki.models.NotesModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AddNoteActivity : AppCompatActivity() {
    private val binding: ActivityAddNoteBinding by lazy {
        ActivityAddNoteBinding.inflate(layoutInflater)
    }

    private var notesList = mutableListOf<NotesModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        loadData()
        binding.saveBtn.setOnClickListener {
            addNewNote()
        }


    }

    private fun loadData() {
        val sharedPref = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPref.getString("MySaveData", null)
        val type = object : TypeToken<MutableList<NotesModel>>() {}.type
        val loadNotesList: MutableList<NotesModel>? = gson.fromJson(json, type)
        notesList = loadNotesList ?: mutableListOf()

    }

    private fun addNewNote() {
        binding.apply {
            when {
                titleText.text.isBlank() -> showToast()
                priceText.text.isBlank() -> showToast()
                amountText.text.isBlank() -> showToast()
                else -> {
                    val amount = amountText.text.toString().toInt()
                    val price = priceText.text.toString().toInt()
                    val total = amount * price
                    val titleText = titleText.text.toString()
                    val newNote = NotesModel(
                        title = titleText,
                        price = price,
                        amountText = amountText,
                        total = total
                    )
                    saveData(newNote)
                }
            }
        }
    }

    private fun saveData(note: NotesModel) {
        notesList.add(note)
        val sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        val gson = Gson()
        val editor = sharedPreferences.edit()
        val saveObj: String = gson.toJson(notesList)
        editor.putString("MySaveData", saveObj)
        editor.apply()
        Toast.makeText(this, "Заметка успешно добавлена", Toast.LENGTH_SHORT).show()
        val intent = Intent(this,MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun showToast() {
        Toast.makeText(this, "cначала заполните все поля", Toast.LENGTH_SHORT).show()
    }
}