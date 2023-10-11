package com.wulan.psts_remidial_nurwulansafitri_xiipplg3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.wulan.psts_remidial_nurwulansafitri_xiipplg3.databinding.ActivityInputKaryawanBinding
import com.wulan.psts_remidial_nurwulansafitri_xiipplg3.karyawan.DB_karyawan
import com.wulan.psts_remidial_nurwulansafitri_xiipplg3.karyawan.Entitykaryawan

class input_karyawan : AppCompatActivity() {
    private lateinit var binding: ActivityInputKaryawanBinding
    private lateinit var database : DB_karyawan
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputKaryawanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = DB_karyawan.getInstance(applicationContext)

        binding.btnsimpan.setOnClickListener {
            if (binding.tvnama.text.isNotEmpty() &&
                    binding.tvalamat.text.isNotEmpty() &&
                    binding.tvtanggallahir.text.isNotEmpty()
            ) {
                database.DB_karyawan().Insertdatakaryawan(
                    Entitykaryawan(
                        0,
                        binding.tvnama.text.toString(),
                        binding.tvalamat.text.toString(),
                        binding.tvtanggallahir.text.toString()
                    ))
                binding.tvnama.setText("")
                binding.tvalamat.setText("")
                binding.tvtanggallahir.setText("")

                startActivity(
                    Intent(this, MainActivity::class.java)
                )
            }else{
                Toast.makeText(
                    applicationContext, "silahkan isi semua data terlebih dahulu",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}