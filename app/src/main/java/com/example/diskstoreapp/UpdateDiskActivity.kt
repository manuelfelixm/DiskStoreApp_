package com.example.diskstoreapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.diskstoreapp.database.AppDatabase
import com.example.diskstoreapp.databinding.ActivityCreateDiskBinding
import com.example.diskstoreapp.databinding.ActivityUpdateDiskBinding
import com.example.diskstoreapp.model.Disk

class UpdateDiskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateDiskBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateDiskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .allowMainThreadQueries().build()

        val serialnumber = intent.getStringExtra("serialnumber")
        val title = intent.getStringExtra("title")
        val author = intent.getStringExtra("author")
        val year = intent.getStringExtra("year")

        binding.serialnumberEditText.setText(serialnumber)
        binding.titleEditText.setText(title)
        binding.authorEditText.setText(author)
        binding.yearEditText.setText(year)

        binding.saveButton.setOnClickListener {
            val newSerialnumber = binding.serialnumberEditText.text.toString()
            val newTitle = binding.titleEditText.text.toString()
            val newAuthor = binding.authorEditText.text.toString()
            val newYear = binding.yearEditText.text.toString()

            // Obtener el disco existente de la base de datos
            val existingDisk = serialnumber?.let { it1 -> db.diskDao().findBySerialNumber(it1) }

            if (existingDisk != null) {
                // Actualizar los datos del disco
                existingDisk.serialnumber = newSerialnumber
                existingDisk.title = newTitle
                existingDisk.author = newAuthor
                existingDisk.year = newYear

                // Actualizar el disco en la base de datos
                db.diskDao().update(existingDisk)

                Toast.makeText(this, "Los datos del disco se han actualizado", Toast.LENGTH_SHORT)
                    .show()
                finish()
            } else {
                Toast.makeText(
                    this,
                    "No se encontró el disco en la base de datos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
