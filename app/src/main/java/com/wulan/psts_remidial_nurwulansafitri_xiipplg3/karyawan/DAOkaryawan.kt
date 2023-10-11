package com.wulan.psts_remidial_nurwulansafitri_xiipplg3.karyawan

import androidx.room.*


@Dao
interface DAOkaryawan {
@Insert
fun Insertdatakaryawan(Entitykaryawan: Entitykaryawan)

@Update
fun Updatedatakaryawan(Entitykaryawan: Entitykaryawan)

@Delete
fun Deletedatakaryawan(Entitykaryawan: Entitykaryawan)

@Query("SELECT * FROM tb_karyawan")
fun getdatakaryawan() : List<Entitykaryawan>

}