package com.adedom.library.usecase

import com.adedom.library.domain.model.ValidateSignIn
import com.adedom.library.domain.repository.DefaultTegAuthRepository
import com.adedom.library.domain.usecase.SignInUseCase
import com.adedom.library.domain.usecase.SignInUseCaseImpl
import com.adedom.teg.request.auth.SignInRequest
import com.adedom.teg.response.SignInResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SignInUseCaseImplTest {

    private var repository = mockk<DefaultTegAuthRepository>(relaxed = true)
    private lateinit var useCase: SignInUseCase

    @Before
    fun setup() {
        useCase = SignInUseCaseImpl(repository)
    }

    @Test
    fun callSignIn_success() = runBlockingTest {
        val request = SignInRequest(username = "admin", password = "1234")
        val response = SignInResponse(
            success = true,
            message = "OK",
            accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"
        )
        coEvery { useCase.callSignIn(request) } returns response

        val signInResponse = repository.callSignIn(request)

        assertEquals(response, signInResponse)
    }

    @Test
    fun validateSignIn_username_null() {
        val signIn = SignInRequest(username = null, password = null)

        val validate = useCase.validateSignIn(signIn)

        assertEquals(ValidateSignIn.VALIDATE_ERROR, validate)
    }

    @Test
    fun validateSignIn_username_empty() {
        val signIn = SignInRequest(username = "", password = null)

        val validate = useCase.validateSignIn(signIn)

        assertEquals(ValidateSignIn.USERNAME_EMPTY, validate)
    }

    @Test
    fun validateSignIn_username_incorrect() {
        val signIn = SignInRequest(username = "adm", password = null)

        val validate = useCase.validateSignIn(signIn)

        assertEquals(ValidateSignIn.USERNAME_INCORRECT, validate)
    }

    @Test
    fun validateSignIn_password_null() {
        val signIn = SignInRequest(username = "admin", password = null)

        val validate = useCase.validateSignIn(signIn)

        assertEquals(ValidateSignIn.VALIDATE_ERROR, validate)
    }

    @Test
    fun validateSignIn_password_empty() {
        val signIn = SignInRequest(username = "admin", password = "")

        val validate = useCase.validateSignIn(signIn)

        assertEquals(ValidateSignIn.PASSWORD_EMPTY, validate)
    }

    @Test
    fun validateSignIn_password_incorrect() {
        val signIn = SignInRequest(username = "admin", password = "123")

        val validate = useCase.validateSignIn(signIn)

        assertEquals(ValidateSignIn.PASSWORD_INCORRECT, validate)
    }

    @Test
    fun validateSignIn_success() {
        val signIn = SignInRequest(username = "admin", password = "1234")

        val validate = useCase.validateSignIn(signIn)

        assertEquals(ValidateSignIn.VALIDATE_SUCCESS, validate)
    }

    @Test
    fun validateUsername_success(){
        val username = "adedom"

        val isValidate = useCase.validateUsername(username)

        assertTrue(isValidate)
    }

    @Test
    fun validateUsername_failed(){
        val username = "ade"

        val isValidate = useCase.validateUsername(username)

        assertFalse(isValidate)
    }

    @Test
    fun validateUsername_empty(){
        val username = ""

        val isValidate = useCase.validateUsername(username)

        assertFalse(isValidate)
    }

    @Test
    fun validatePassword_success(){
        val password = "1234"

        val isValidate = useCase.validatePassword(password)

        assertTrue(isValidate)
    }

    @Test
    fun validatePassword_failed(){
        val password = "12"

        val isValidate = useCase.validatePassword(password)

        assertFalse(isValidate)
    }

    @Test
    fun validatePassword_empty(){
        val password = ""

        val isValidate = useCase.validatePassword(password)

        assertFalse(isValidate)
    }

}
