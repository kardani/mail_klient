package com.masoudk.repository.model

data class Message(
    val id: String,
    val date: String,
    val from: String,
    val email: String,
    val subject: String,
    val content: String)