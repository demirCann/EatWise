package com.demircandemir.relaysample.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demircandemir.relaysample.domain.model.UserInfo
import com.demircandemir.relaysample.util.Constants.USER_INFO_TABLE

//@Dao
//interface UserInfoDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertUserInfo(userInfo: UserInfo)
//
//    @Query("SELECT * FROM $USER_INFO_TABLE WHERE id = :id")
//    suspend fun getUserInfo(id: String): UserInfo
//}