package com.haura.latihan_aplikasi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.haura.latihan_aplikasi.Adapter.MahasiswaAdapter
import com.haura.latihan_aplikasi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: DatabaseHelper
    private lateinit var adapter: MahasiswaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddMahasiswa::class.java)
            startActivity(intent)
        }
        db = DatabaseHelper(this)
        adapter = MahasiswaAdapter(db.getAllData(), this)

        binding.notesRecycle.layoutManager = LinearLayoutManager(this)
        binding.notesRecycle.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        val  notes = db.getAllData()
        Log.d("MainActivity","Notes count ${notes.size}")
        adapter.refreshData(notes)
    }

}