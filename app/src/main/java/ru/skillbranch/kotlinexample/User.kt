package ru.skillbranch.kotlinexample

class User (
    private val firstName: String,
    private val lastName: String?,
    email: String? = null,
    rawPhone: String? = null,
    meta: Map<String, Any>? = null
){
    val userInfo: String
    private val fullName: String
        get() = listOfNotNull(firstName, lastName)
            .joinToString(" ")
            .capitalize()

    private val initials: String
    get() = listOfNotNull(firstName, lastName)
        .map { it.first().toUpperCase() }
        .joinToString { " " }

    private var phone: String? = null
    set(value) {
        // В приходящем value которое приходит в сетер, удаляем все элементы кроме чисел + оставляем
        field = value?.replace("[^+\\d]".toRegex(), "")
    }

    private var _login: String? = null
    private var login: String
        set(value) {
            _login = value?.toLowerCase()
        }
        get() = _login!!

    private lateinit var passwordHash: String
}