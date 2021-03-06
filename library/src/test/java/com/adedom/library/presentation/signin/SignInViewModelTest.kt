package com.adedom.library.presentation.signin

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adedom.library.domain.Resource
import com.adedom.library.domain.model.ValidateSignIn
import com.adedom.library.presentation.usercase.SignInUseCase
import com.adedom.library.util.getOrAwaitValue
import com.adedom.teg.models.request.SignInRequest
import com.adedom.teg.models.response.SignInResponse
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
        val result = Resource.Success(response)
        coEvery { useCase.callSignIn(request) } returns result

        viewModel.callSignIn()

        val signInResponse = viewModel.signIn.getOrAwaitValue()
        assertEquals(result.data, signInResponse)
    }

    @Test
    fun callSignIn_failed() {
        viewModel.setUsername("adedom")
        viewModel.setPassword("1234")
        val throwable = Throwable("Api error")
        val result = Resource.Error(throwable)
        coEvery { useCase.callSignIn(any()) } throws result.throwable

        viewModel.callSignIn()

        val error = viewModel.error.getOrAwaitValue()
        assertEquals(result.throwable, error)
    }

}
