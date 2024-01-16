package com.demircandemir.relaysample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.demircandemir.relaysample.data.local.dao.MealDao
import com.demircandemir.relaysample.data.local.dao.MealRemoteKeysDao
import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.domain.model.MealRemoteKeys


@Database(entities = [MealInfo::class, MealRemoteKeys::class], version = 1)
@TypeConverters(DatabaseConvertor::class)
abstract class EatWiseDatabase: RoomDatabase() {

    abstract fun mealDao(): MealDao

    abstract fun mealRemoteKeysDao(): MealRemoteKeysDao

}