package com.wulan.psts_remidial_nurwulansafitri_xiipplg3.karyawan

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_karyawan")
data class Entitykaryawan(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID_karyawan")
    val ID_karyawan: Int,
    @ColumnInfo(name = "nama")
    val nama: String,
    @ColumnInfo(name = "alamat")
    val alamat: String,
    @ColumnInfo(name = "tanggal lahir")
    val tanggal_lahir: String
)
