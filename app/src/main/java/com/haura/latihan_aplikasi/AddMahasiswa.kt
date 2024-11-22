package com.haura.latihan_aplikasi

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.haura.latihan_aplikasi.Model.ModelMahasiswa
import com.haura.latihan_aplikasi.databinding.ActivityAddMahasiswaBinding

class AddMahasiswa : AppCompatActivity() {

    private lateinit var binding: ActivityAddMahasiswaBinding
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMahasiswaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        binding.saveButton.setOnClickListener{
            val nama = binding.etNama.text.toString()
            val nim = binding.etNim.text.toString()
            val jurusan = binding.etjurusan.text.toString()
            val mahasiswa = ModelMahasiswa(0, nama, nim, jurusan)

            db.insertMahasiswa(mahasiswa)
            finish()
            Toast.makeText(this, "Catatan Disimpan", Toast.LENGTH_SHORT).show()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}