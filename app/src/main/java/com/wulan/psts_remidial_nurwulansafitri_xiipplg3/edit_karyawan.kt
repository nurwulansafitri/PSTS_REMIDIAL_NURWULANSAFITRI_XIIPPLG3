package com.wulan.psts_remidial_nurwulansafitri_xiipplg3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.wulan.psts_remidial_nurwulansafitri_xiipplg3.databinding.ActivityEditKaryawanBinding
import com.wulan.psts_remidial_nurwulansafitri_xiipplg3.karyawan.DB_karyawan
import com.wulan.psts_remidial_nurwulansafitri_xiipplg3.karyawan.Entitykaryawan

class edit_karyawan : AppCompatActivity() {

    private lateinit var binding: ActivityEditKaryawanBinding
    private val db by lazy { DB_karyawan.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditKaryawanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra("ID_karyawan").toString().toInt()
        val data = db.DB_karyawan().getdatakaryawan()

        binding.editnama.setText(data[0].nama)
        binding.editalamat.setText(data[0].alamat)
        binding.edittanggallahir.setText(data[0].tanggal_lahir)
        binding.btnedit.setOnClickListener {
            if (binding.editnama.text.isNotEmpty()&&
                    binding.editalamat.text.isNotEmpty()&&
                    binding.edittanggallahir.text.isNotEmpty()){

                db.DB_karyawan().Updatedatakaryawan(Entitykaryawan(
                    id,
                    binding.editnama.text.toString(),
                    binding.editalamat.text.toString(),
                    binding.edittanggallahir.text.toString()
                ))

                Toast.makeText(applicationContext, "Data berhasil diubah",Toast.LENGTH_SHORT).show()
                startActivity(
                    Intent(this,MainActivity::class.java)
                )
                onBackPressed()
            }else{
                Toast.makeText(applicationContext, "ubah data terlebih dahulu",Toast.LENGTH_SHORT).show()
            }
        }
    }
}