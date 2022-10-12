package com.example.shemajamebeli_10.ui

import com.example.shemajamebeli_10.data.remote.model.DTOItem
import java.text.SimpleDateFormat
import java.util.*

data class UIItem (
    val id: Int,
    val firstName: String,
    val lastName: String,
    val avatarImage: String,
    val messageType: String,
    val message: String?,
    val typeIconVisibility: Boolean,
    val unreadAmount: Int,
    val unreadAmountVisibility: Boolean,
    val typingVisibility: Boolean,
    val updated_date: String
)


fun convertDTOtoUIObject(dto: DTOItem): UIItem{
   val typeIconVisibility = dto.message_type!="text"
    val unreadAmountVisibility = dto.unrea_message!=0
    return UIItem(dto.id,dto.first_name,dto.last_name,dto.avatar, dto.message_type, dto.last_message, typeIconVisibility, dto.unrea_message, unreadAmountVisibility, dto.is_typing,
        getData(dto.updated_date,"hh:mm a")!!)
}

fun getData(milliSeconds: Long, dateFormat: String?): String?{
    val formatter = SimpleDateFormat(dateFormat)
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = milliSeconds
    return formatter.format(calendar.time)
}

