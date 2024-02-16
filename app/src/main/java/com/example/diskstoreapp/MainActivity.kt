package com.example.diskstoreapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.diskstoreapp.R.menu.action_menu
import com.example.diskstoreapp.R.menu.disk_context_menu
import com.example.diskstoreapp.database.AppDatabase
import com.example.diskstoreapp.databinding.ActivityMainBinding
import com.example.diskstoreapp.model.Disk

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.actionMenuToolbar)

        db = Room.databaseBuilder(
            this, AppDatabase::class.java, AppDatabase.DATABASE_NAME
        ).allowMainThreadQueries().build()

        /*val Disk1 = Disk(
            serialnumber = "4",
            title = "Suavemente",
            author = "Elvis Crespo",
            year = "1998"
        )
        db.diskDao().save(Disk1)

        val Disk2 = Disk(
            serialnumber = "33",
            title = "El Nano",
            author = "Melendi",
            year = "2005"
        )
        db.diskDao().save(Disk2)*/

        binding.disksRecyclerView.layoutManager =
            GridLayoutManager(this, 1, RecyclerView.VERTICAL, false)

        binding.disksRecyclerView.adapter = DiskAdapter(
            db.diskDao().list(), this, db)

        binding.addDiskButton.setOnClickListener{
            val createBookIntent = Intent(
                this, CreateDiskActivity::class.java
            )

            startActivity(createBookIntent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(action_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onResume() {
        super.onResume()

        val adapter = binding.disksRecyclerView.adapter as DiskAdapter

        adapter.disks = db.diskDao().list()

        adapter.notifyDataSetChanged()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(
            /* menuRes = */ disk_context_menu,
            /* menu = */ menu
        )
    }
}