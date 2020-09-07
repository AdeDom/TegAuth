package com.adedom.tegauth.presentation.signin

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adedom.library.domain.model.ValidateSignIn
import com.adedom.library.domain.usecase.SignInUseCase
import com.adedom.teg.request.auth.SignInRequest
import com.adedom.teg.response.SignInResponse
import com.adedom.tegauth.util.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.KoinContextHandler
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
class SignInViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val useCase = mockk<SignInUseCase>(relaxed = true)
    private lateinit var viewModel: SignInViewModel

    @Before
    fun setup() {
        viewModel = SignInViewModel(useCase)
    }

    @After
    fun cleanup() {
        KoinContextHandler.stop()
    }

    @Test
    fun setUsername_completed() {
        val username = "adedom"
        val isValidate = true
        every { useCase.validateUsername(username) } returns isValidate

        viewModel.setUsername(username)

        val state = viewModel.state.getOrAwaitValue()
        assertEquals(username, state.username)
        assertEquals(isValidate, state.isValidUsername)
    }

    @Test
    fun setUsername_incorrect() {
        val username = "ade"
        val isValidate = false
        every { useCase.validateUsername(username) } returns isValidate

        viewModel.setUsername(username)

        val state = viewModel.state.getOrAwaitValue()
        assertEquals(username, state.username)
        assertEquals(isValidate, state.isValidUsername)
    }

    @Test
    fun setUsername_empty() {
        val username = ""
        val isValidate = false
        every { useCase.validateUsername(username) } returns isValidate

        viewModel.setUsername(username)

        val state = viewModel.state.getOrAwaitValue()
        assertEquals(username, state.username)
        assertEquals(isValidate, state.isValidUsername)
    }

    @Test
    fun setPassword_completed() {
        val password = "1234"
        val isValidate = true
        every { useCase.validatePassword(password) } returns isValidate

        viewModel.setPassword(password)

        val state = viewModel.state.getOrAwaitValue()
        assertEquals(password, state.password)
        assertEquals(isValidate, state.isValidPassword)
    }

    @Test
    fun setPassword_incorrect() {
        val password = "123"
        val isValidate = false
        every { useCase.validatePassword(password) } returns isValidate

        viewModel.setPassword(password)

        val state = viewModel.state.getOrAwaitValue()
        assertEquals(password, state.password)
        assertEquals(isValidate, state.isValidPassword)
    }

    @Test
    fun setPassword_empty() {
        val password = ""
        val isValidate = false
        every { useCase.validatePassword(password) } returns isValidate

        viewModel.setPassword(password)

        val state = viewModel.state.getOrAwaitValue()
        assertEquals(password, state.password)
        assertEquals(isValidate, state.isValidPassword)
    }

    @Test
    fun onSignIn_completed() {
        val username = "admin"
        val password = "1234"
        val request = SignInRequest(username = username, password = password)
        viewModel.setUsername(username)
        viewModel.setPassword(password)
        val success = ValidateSignIn.VALIDATE_SUCCESS
        every { useCase.validateSignIn(request) } returns success

        viewModel.onSignIn()

        val signInEvent = viewModel.signInEvent.getOrAwaitValue()
        assertEquals(success, signInEvent)
    }

    @Test
    fun callSignIn_completed() {
        val username = "adedom"
        val password = "1234"
        viewModel.setUsername(username)
        viewModel.setPassword(password)
        val request = SignInRequest(username = username, password = password)
        val response = SignInResponse(
            success = true,
            message = "OK",
            accessToken = "asdexcvxcb"
        )
        coEvery { useCase.callSignIn(request) } returns response

        viewModel.callSignIn()

        val signInResponse = viewModel.signIn.getOrAwaitValue()
        assertEquals(response, signInResponse)
    }

    @Test
    fun callSignIn_failed() {
        val username = "adedom"
        val password = "1234"
        viewModel.setUsername(username)
        viewModel.setPassword(password)
        val request = SignInRequest(username = username, password = password)
        val throwable = Throwable("Api error")
        coEvery { useCase.callSignIn(request) } throws throwable

        viewModel.callSignIn()

        val error = viewModel.error.getOrAwaitValue()
        assertEquals(throwable, error)
    }

}
