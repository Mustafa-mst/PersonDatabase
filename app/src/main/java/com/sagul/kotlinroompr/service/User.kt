package com.sagul.kotlinroompr.service

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "users")
data class User(@PrimaryKey(autoGenerate = true)
                var id:Int,
    @ColumnInfo("name")
    var name:String,
    @ColumnInfo("age")
    var age:String
):Parcelable{

}
