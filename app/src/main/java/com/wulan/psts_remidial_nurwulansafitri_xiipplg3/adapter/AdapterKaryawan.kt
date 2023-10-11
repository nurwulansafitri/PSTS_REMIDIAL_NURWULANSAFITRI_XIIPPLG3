package com.wulan.psts_remidial_nurwulansafitri_xiipplg3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wulan.psts_remidial_nurwulansafitri_xiipplg3.R
import com.wulan.psts_remidial_nurwulansafitri_xiipplg3.karyawan.Entitykaryawan

class AdapterKaryawan(val list: ArrayList<Entitykaryawan>, var listener:onAdapterListener):
RecyclerView.Adapter<AdapterKaryawan.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val namakaryawan = itemView.findViewById<TextView>(R.id.datanama)
        val alamatkaryawan = itemView.findViewById<TextView>(R.id.dataalamat)
        val tanggallahir = itemView.findViewById<TextView>(R.id.datatanggallahir)
        val edit = itemView.findViewById<Button>(R.id.btnedit)
        val hapus = itemView.findViewById<Button>(R.id.btndelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.adapter_karyawan, parent, false
                )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.namakaryawan.text = list[position].nama
        holder.alamatkaryawan.text = list[position].alamat
        holder.tanggallahir.text = list[position].tanggal_lahir
        holder.edit.setOnClickListener {
            listener.onEdit(list[position])
        }
        holder.hapus.setOnClickListener {
            listener.onDelete(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(newList: List<Entitykaryawan>) {
        list.clear()
        list.addAll(newList)
    }
    interface onAdapterListener{
        fun onEdit(Entitykaryawan : Entitykaryawan)
        fun onDelete(Entitykaryawan: Entitykaryawan)
    }
}