package ru.skillbranch.kotlinexample

object UserHolder {
    private val map = mutableMapOf<String, User>()

    fun registerUser(
        fullName: String,
        email: String,
        password: String
    ): User {
        val us: User = User.makeUser(fullName, email = email, password = password)
        if (map.containsKey(us.login)) {
            throw IllegalArgumentException("A user with this email already exists")
        }

        return us.also { user ->
            map[user.login] = user
        }
    }

    fun registerUserByPhone(
        fullName: String,
        rawPhone: String
    ): User {
        val us: User = User.makeUser(fullName, phone = rawPhone)
        if (map.containsKey(us.login)) {
            throw IllegalArgumentException("A user with this phone already exists")
        }

        return us.also { user ->
            map[user.login] = user
        }
    }

    fun loginUser(login: String, password: String): String? {
        return map[login.trim()]?.run {
            if (checkPassword(password)) this.userInfo
            else null
        }
    }

    fun requestAccessCode(phone: String?): String {
        return ""
    }

}