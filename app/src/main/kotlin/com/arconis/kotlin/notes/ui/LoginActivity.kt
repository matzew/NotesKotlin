package com.arconis.kotlin.notes.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import com.arconis.kotlin.notes.R
import com.arconis.kotlin.notes.data.User
import com.arconis.kotlin.notes.service.UserService
import com.arconis.kotlin.notes.util.*

/**
 * Created by lex on 03.09.15.
 */
class LoginActivity : AppCompatActivity() {

    private val user: EditText by ActivityView(R.id.login_username)
    private val password: EditText by ActivityView(R.id.login_password)

    private val userService = UserService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        click(R.id.login_action) { onLoginClicked() }
        click(R.id.login_register) { onRegisterClicked() }
    }

    private fun onRegisterClicked() {
        if (!checkCredentials()) {
            return
        }
        if (userService.doesUserExist(user.value())) {
            toast(R.string.user_exists)
        } else {
            val newUser = User(username = user.value(), password = password.value())
            userService.saveUser(newUser)
            MainActivity.startWithUser(this, newUser)
        }

    }

    private fun onLoginClicked() {
        if (!checkCredentials()) {
            return
        }
        login()
    }

    private fun login() {
        val user = userService.getUserByName(user.value())
        if (user == null) {
            toast(R.string.user_not_existing)
            return
        }

        if (user.password == password.value()) {
            MainActivity.startWithUser(this, user)
        } else {
            toast(R.string.password_incorrect)
        }
    }

    private fun checkCredentials(): Boolean {
        return user.hasNoErrors(R.string.enter_a_user) { it.isNotBlank() } && password.hasNoErrors(R.string.enter_a_password, ::notBlankAndAtLeastTwoChars)
    }
}

private fun notBlankAndAtLeastTwoChars(text: String): Boolean {
    return text.isNotBlank() && text.length >= 2
}