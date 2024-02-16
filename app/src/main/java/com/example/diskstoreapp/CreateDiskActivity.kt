package com.example.diskstoreapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.diskstoreapp.database.AppDatabase
import com.example.diskstoreapp.databinding.ActivityCreateDiskBinding
import com.example.diskstoreapp.model.Disk

class CreateDiskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateDiskBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateDiskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
                ).allowMainThreadQueries().build()

        binding.saveButton.setOnClickListener {
            val serialnumber = binding.serialnumberEditText.text.toString()
            val title = binding.titleEditText.text.toString()
            val author = binding.authorEditText.text.toString()
            val year = binding.yearEditText.text.toString()

            val existingDisk = db.diskDao().findBySerialNumber(serialnumber)

            if (existingDisk != null) {
                // Mostrar mensaje de error
                Toast.makeText(this, "El disco con este número de serie ya existe", Toast.LENGTH_SHORT).show()
            } else {
                // Guardar el disco en la base de datos
                val disk = Disk(
                    serialnumber = serialnumber,
                    title = title,
                    author = author,
                    year = year
                )
                db.diskDao().save(disk)

                finish()
            }
        }
    }
}