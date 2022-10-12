package com.example.shemajamebeli_10.data.remote.model

data class DTOItem(
    val avatar: String,
    val email: String,
    val first_name: String,
    val id: Int,
    val is_typing: Boolean,
    val last_message: String?,
    val last_name: String,
    val message_type: String,
    val unrea_message: Int,
    val updated_date: Long
)