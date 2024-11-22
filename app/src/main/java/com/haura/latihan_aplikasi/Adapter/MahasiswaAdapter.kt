package com.haura.latihan_aplikasi.Adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.haura.latihan_aplikasi.DatabaseHelper
import com.haura.latihan_aplikasi.Model.ModelMahasiswa
import com.haura.latihan_aplikasi.R
import com.haura.latihan_aplikasi.UpdateMahasiswaActivity

class MahasiswaAdapter(
    private var mahasiswa: List<ModelMahasiswa>,
    context: Context
) : RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder>() {


    class MahasiswaViewHolder (itemview: View): RecyclerView.ViewHolder(itemview){
        val  nama : TextView = itemview.findViewById(R.id.txtNama)
        val  nim : TextView = itemview.findViewById(R.id.txtNim)
        val  jurusan : TextView = itemview.findViewById(R.id.txtJurusan)
        val updateButton: ImageView = itemview.findViewById(R.id.updateBtn)
        val deleteButton: ImageView = itemview.findViewById(R.id.deleteBtn)
    }

    private val db: DatabaseHelper = DatabaseHelper(context)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MahasiswaAdapter.MahasiswaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mahasiswa,parent,false)
        return MahasiswaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MahasiswaAdapter.MahasiswaViewHolder, position: Int) {
        val mahasiswaItem = mahasiswa[position]
        holder.nama.text = mahasiswaItem.nama
        holder.nim.text = mahasiswaItem.nim
        holder.jurusan.text = mahasiswaItem.jurusan

        holder.updateButton.setOnClickListener{
            val intent = Intent(holder.itemView.context, UpdateMahasiswaActivity::class.java).apply {
                putExtra("note_id", mahasiswaItem.id)
            }
            holder.itemView.context.startActivity(intent)
        }
        holder.deleteButton.setOnClickListener{
            AlertDialog.Builder(holder.itemView.context).apply {
                setTitle("Konfirmasi")
                setMessage("Apakah anda ingin melanjutkan?")
                setIcon(R.drawable.baseline_delete_24)

                setPositiveButton("Yakin"){dialogInterface, i ->
                    db.deleteMahasiswa(mahasiswaItem.id)
                    refreshData(db.getAllData())
                    dialogInterface.dismiss()
                }

                setNegativeButton("Batal"){dialogInterface, i->
                    dialogInterface.dismiss()
                }
            }.show()

            Toast.makeText(holder.itemView.context, "Catatan Dihapus", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return mahasiswa.size
    }

    fun refreshData(newNotes : List<ModelMahasiswa>){
        mahasiswa = newNotes
        notifyDataSetChanged()
    }

}