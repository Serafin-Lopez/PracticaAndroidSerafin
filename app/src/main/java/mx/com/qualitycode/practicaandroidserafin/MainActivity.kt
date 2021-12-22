package mx.com.qualitycode.practicaandroidserafin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import mx.com.qualitycode.practicaandroidserafin.helpers.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
        hideViews()

    }

    private fun hideViews() {

        textViewPhone.hide()
        textViewEmail.hide()
        textViewNumberRegister.hide()
        textPassword.hide()

        numberRegisterErrorText.hide()
        phoneErrorText.hide()
        emailErrorText.hide()
        passwordErrorText.hide()

    }

    private fun setupViews() {

        editTextNumberRegister.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                textViewNumberRegister.shouldShow(editTextNumberRegister.text!!.isNotEmpty())
                numberRegisterErrorText.hide()
                editTextNumberRegister.setBackgroundResource(R.drawable.style_text_view)
            }
        })


        editTextPhone.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                textViewPhone.shouldShow(editTextPhone.text!!.isNotEmpty())
                phoneErrorText.hide()
                editTextPhone.setBackgroundResource(R.drawable.style_text_view)
            }
        })


        editTextPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                textPassword.shouldShow(editTextPassword.text!!.isNotEmpty())
                passwordErrorText.hide()
                editTextPasswordLayout.setBackgroundResource(R.drawable.style_text_view)
            }
        })

        editTextEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                textViewEmail.shouldShow(editTextEmail.text!!.isNotEmpty())
                emailErrorText.hide()
                editTextEmail.setBackgroundResource(R.drawable.style_text_view)
            }
        })

        buttonRegister.setOnClickListener {
            if (validations()) {
                Toast.makeText(this,getString(R.string.succes_register),Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun validations(): Boolean {
        var isDataValid = true

        val numberRegister = ValidationUtils.validateGenericFields(editTextNumberRegister.text.toString(),
            R.string.this_field_is_required,R.string.this_field_required_max_length)

        val phone  = ValidationUtils.validatePhone(editTextPhone.text.toString(),R.string.this_field_is_required, R.string.error_message_invalid_phone_number)
        val emailError = ValidationUtils.validateEmail(editTextEmail.text.toString(),R.string.this_field_is_required, R.string.error_message_invalid_email_address)
        val passwordError = ValidationUtils.validatePassword(editTextPassword.text.toString(), R.string.this_field_is_required)


        numberRegister.takeIf { it != NO_ERROR }?.let {
            numberRegisterErrorText.text = getString(it)
            numberRegisterErrorText.show()
            editTextNumberRegister.setBackgroundResource(R.drawable.style_text_view_error)
            isDataValid = false
        }

        emailError.takeIf { it != NO_ERROR }?.let {
            emailErrorText.text = getString(it)
            emailErrorText.show()
            editTextEmail.setBackgroundResource(R.drawable.style_text_view_error)
            isDataValid = false
        }

        passwordError.takeIf { it != NO_ERROR }?.let {
            passwordErrorText.text = getString(it)
            passwordErrorText.show()
            editTextPasswordLayout.setBackgroundResource(R.drawable.style_text_view_error)
            isDataValid = false
        }

        phone.takeIf { it != NO_ERROR }?.let {
            phoneErrorText.text = getString(it)
            phoneErrorText.show()
            editTextPhone.setBackgroundResource(R.drawable.style_text_view_error)
            isDataValid = false
        }

        return isDataValid
    }
}