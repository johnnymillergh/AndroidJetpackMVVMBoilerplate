package com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.main.model;

import androidx.room.ColumnInfo
import androidx.room.Entity;
import androidx.room.PrimaryKey
import java.time.LocalDateTime

/**
 * Description: UserVisitRecord, change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, date: 9/6/2021 4:26 PM
 **/
@Entity(tableName = "user_visit_record")
data class UserVisitRecord constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val user: String,
    @ColumnInfo(name = "visit_time")
    val visitTime: LocalDateTime,
    @ColumnInfo(name = "os_version")
    val osVersion: String
)
