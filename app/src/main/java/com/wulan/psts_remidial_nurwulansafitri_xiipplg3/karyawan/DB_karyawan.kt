package com.wulan.psts_remidial_nurwulansafitri_xiipplg3.karyawan

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Entitykaryawan::class], version = 1)
abstract class DB_karyawan : RoomDatabase() {
    abstract fun DB_karyawan():DAOkaryawan

    companion object{
        @Volatile
        private var INSTANCE :DB_karyawan?=null
        private var key = Any()
        @Synchronized
        fun getInstance(context: Context): DB_karyawan {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, DB_karyawan::class.java, "datakaryawan")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE!!
        }
    }
}