package com.wulan.psts_remidial_nurwulansafitri_xiipplg3

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.wulan.psts_remidial_nurwulansafitri_xiipplg3.adapter.AdapterKaryawan
import com.wulan.psts_remidial_nurwulansafitri_xiipplg3.databinding.ActivityMainBinding
import com.wulan.psts_remidial_nurwulansafitri_xiipplg3.karyawan.DB_karyawan
import com.wulan.psts_remidial_nurwulansafitri_xiipplg3.karyawan.Entitykaryawan
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val db by lazy { DB_karyawan.getInstance(this) }
    private lateinit var adapter: AdapterKaryawan
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = AdapterKaryawan(arrayListOf(),
        object : AdapterKaryawan.onAdapterListener{
            override fun onEdit(Entitykaryawan: Entitykaryawan)  {
                EditData(Entitykaryawan)
            }
            override fun onDelete(Entitykaryawan: Entitykaryawan) {
                HapusData(Entitykaryawan)
            }
        })
        binding.listData.adapter = adapter
        binding.listData.layoutManager = LinearLayoutManager(applicationContext, VERTICAL, false)
        binding.listData.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))
        binding.btntambah.setOnClickListener{
            startActivity(
                Intent(this, input_karyawan::class.java)
            )
        }
        getData()
    }
    private fun HapusData(Entitykaryawan: Entitykaryawan) {
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("konfirmasi hapus data")
            setMessage("apakah anda yakin akan menghapus data ${Entitykaryawan.nama}?")
            setNegativeButton("batal"){
                dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()
            }
            setPositiveButton("hapus"){
                dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()

                CoroutineScope(Dispatchers.IO).launch {
                    db.DB_karyawan().Deletedatakaryawan(Entitykaryawan)
                    finish()
                    startActivity(intent)
                }
            }
            dialog.show()
        }
    }

    private fun EditData(tb_karyawan: Entitykaryawan){
        startActivity(Intent(this,edit_karyawan::class.java)
            .putExtra("ID_karyawan", tb_karyawan.ID_karyawan.toString()))
    }

    override fun onResume() {
        super.onResume()
        getData()
    }
    fun getData(){
        binding.listData.layoutManager = LinearLayoutManager(this)
        CoroutineScope(Dispatchers.IO).launch {
            val data = db.DB_karyawan().getdatakaryawan()
            adapter.setData(data)
            withContext(Dispatchers.Main){
                adapter.notifyDataSetChanged()
            }
        }
        binding.listData.adapter = adapter
    }
}