package com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.main.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.main.model.UserVisitRecord

@Dao
interface UserVisitRecordDao {
    @Insert
    fun insert(userVisitRecord: UserVisitRecord)

    @Query("SELECT * FROM user_visit_record order by visit_time DESC")
    fun selectAll(): List<UserVisitRecord>
}
