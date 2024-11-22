package com.haura.latihan_aplikasi

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.haura.latihan_aplikasi.Model.ModelMahasiswa
import com.haura.latihan_aplikasi.databinding.ActivityUpdateMahasiswaBinding

class UpdateMahasiswaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateMahasiswaBinding
    private lateinit var db: DatabaseHelper
    private var noteId: Int =-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateMahasiswaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        noteId = intent.getIntExtra("note_id", -1)
        if (noteId == -1){
            finish()
            return
        }
        val note = db.getMahasiswaById(noteId)
        binding.etEditNama.setText(note.nama)
        binding.etEditNim.setText(note.nim)
        binding.etEditjurusan.setText(note.jurusan)

        binding.updateButton.setOnClickListener{
            val newNama = binding.etEditNama.text.toString()
            val newNim = binding.etEditNim.text.toString()
            val newJurusa = binding.etEditjurusan.text.toString()

            val updateNote = ModelMahasiswa(noteId, newNama, newNim,newJurusa)
            db.updateMahasiswa(updateNote)

            Toast.makeText(this, "Catatan Berhasil Diperbarui", Toast.LENGTH_SHORT).show()
            finish()

        }


    }
}