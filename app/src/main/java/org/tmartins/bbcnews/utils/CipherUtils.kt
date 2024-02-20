package org.tmartins.bbcnews.utils

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator

private const val KEY_STORE_TYPE = "AndroidKeyStore"
private const val PROVIDER = "AndroidKeyStore"
private const val KEY_STORE_ALIAS = "key"
private const val CIPHER = "AES/CBC/PKCS7Padding"

fun getCipher(): Cipher {
    val keyStore = KeyStore.getInstance(KEY_STORE_TYPE)
    keyStore.load(null)

    val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, PROVIDER)
    val builder = KeyGenParameterSpec.Builder(KEY_STORE_ALIAS, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
            .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
            .setUserAuthenticationRequired(true)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)

    builder.setInvalidatedByBiometricEnrollment(true)
    keyGenerator.init(builder.build())
    val secretKey = keyGenerator.generateKey()

    val cipher = Cipher.getInstance(CIPHER)
    cipher.init(Cipher.ENCRYPT_MODE, secretKey)

    return cipher
}
