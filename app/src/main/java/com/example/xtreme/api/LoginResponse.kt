package com.example.xtreme.api

import com.example.xtreme.models.User

data class LoginResponse(val error: Boolean, val message: String?, val user: User)