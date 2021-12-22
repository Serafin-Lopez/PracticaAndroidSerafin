package mx.com.qualitycode.practicaandroidserafin.helpers

import android.util.Patterns
import androidx.annotation.VisibleForTesting
import mx.com.qualitycode.practicaandroidserafin.R
import java.util.regex.Matcher
import java.util.regex.Pattern


private const val PHONE_LENGTH = 10
private const val MINIMUM_PASSWORD_LENGTH = 5


// Generic constant for no errors found on the fields
const val NO_ERROR = -1

private const val REGEX_NUMBER_CONSECUTIVELY_REPEATED_MORE_THAN_FIVE_TIMES = "(.)\\1{5,}"

object ValidationUtils {

    /**
     * Validates if the email matches with all the requirements
     * @return string resource id if one of the validations is not met.
     * Return NO_ERROR if everything is ok.
     */
    fun validateEmail(email: String, emptyMessageError: Int, invalidFormatError: Int): Int {
        with(email) {
            if (isEmpty()) {
                return emptyMessageError
            }
            if (!isValidEmail(this)) {
                return invalidFormatError
            }
            return NO_ERROR
        }
    }

    /**
     * Checks if the email is valid using regex
     * @return true if the email is valid, false otherwise
     */
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    /**
     * Checks if the phone number is valid using regex
     * * @return true if the phone is valid, false otherwise
     */
    private fun isValidPhoneNumber(phoneNumber: CharSequence): Boolean {
        return Patterns.PHONE.matcher(phoneNumber).matches()
    }

    /**
     * Verify if one character has been found repeated more than 5 times consecutively
     * @return true if a character is found 6 or more times repeated consecutively,
     * false otherwise
     */
    @VisibleForTesting
    internal fun areNumbersConsecutivelyRepeated(input: String) = Pattern
        .compile(REGEX_NUMBER_CONSECUTIVELY_REPEATED_MORE_THAN_FIVE_TIMES)
        .matcher(input)
        .find()

    /**
     * Validates if the password matches with the requirements
     * @return string resource id if one of the validations is not met.
     * Return NO_ERROR if everything is ok.
     */
    fun validatePassword(passWord: String, emptyMessageError: Int): Int {
        with(passWord) {
            if (isEmpty()) {
                return emptyMessageError
            }
            if (passWord.length < MINIMUM_PASSWORD_LENGTH) {
                return R.string.error_message_invalid_password
            }
            return NO_ERROR
        }
    }

    /**
     * Validates fields generic
     * @param name textView
     * @param messageEnterText first message enter text
     * @param messageInvalidEnterText first message enter text invalid
     * @return string resource id if one of the validations is not met.
     * Return NO_ERROR if everything is ok.
     */
    fun validateGenericFields(name: String, messageEnterText: Int, secondMessageEnterText: Int): Int {
        with(name) {
            if (isEmpty()) {
                return messageEnterText
            }
            if (length < 16) {
                return secondMessageEnterText
            }
            return NO_ERROR
        }
    }

    /**
     * Validates if the phone number matches with all the requirements
     * @return string resource id if one of the validations is not met.
     * Return NO_ERROR if everything is ok.
     */
    fun validatePhone(phone: String, emptyMessageError: Int, invalidFormatError: Int): Int {
        with(phone) {

            if (phone.isEmpty()) {
                return emptyMessageError
            }

            if (length < PHONE_LENGTH) {
                return R.string.the_cell_phone_number_must_be_ten_digits_long
            }
            if (!isValidPhoneNumber(this)
                || areNumbersConsecutivelyRepeated(this)) {
                return invalidFormatError
            }
            return NO_ERROR
        }
    }


}