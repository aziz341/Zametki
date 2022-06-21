package com.example.zametki.models

import android.widget.EditText
import java.io.Serializable

class NotesModel(
    val title: String,
    val price: Int,
    val amountText: EditText,
    val total: Int
) : Serializable
