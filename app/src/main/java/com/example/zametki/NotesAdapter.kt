package com.example.zametki

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zametki.databinding.ItemNoteBinding
import com.example.zametki.models.NotesModel

class NotesAdapter  : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>()  {

    var notesList:List<NotesModel> = emptyList()
        set(newValue){
            field = newValue
            notifyDataSetChanged()
        }



    inner class NoteViewHolder(private val binding : ItemNoteBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(note : NotesModel){
            binding.apply {
                with(binding){
                    titleText.text = note.title
                    priceText.text = note.price.toString()
                    amountText.text = note.amountText.toString()
                    total.text = note.total.toString()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent,false)
        val binding = ItemNoteBinding.bind(view)
        return NoteViewHolder(binding)
    }
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        holder.bind(notesList[position])

    }

    override fun getItemCount(): Int = notesList.size
    }