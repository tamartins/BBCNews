package org.tmartins.bbcnews.view.base

import android.Manifest
import android.content.pm.PackageManager
import android.hardware.fingerprint.FingerprintManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import org.tmartins.bbcnews.BuildConfig
import org.tmartins.bbcnews.R
import org.tmartins.bbcnews.manager.NavigationManager
import org.tmartins.bbcnews.utils.getCipher
import org.tmartins.bbcnews.view.list.NewsListFragment

/**
 * The main activity of the application.
 *
 * This activity serves as the entry point of the application.
 * Should be responsible for application navigation.
 */
class MainActivity : AppCompatActivity() {

    private val navigationManager by lazy {
        NavigationManager(supportFragmentManager, R.id.frame_layout_main_container)
    }

    //region override functions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initActionBar()
        if (savedInstanceState == null) {
            callFingerprintLogin()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        navigationManager.onBackPressed()
    }

    //endregion

    //region public functions

    /**
     * Navigate to the specified fragment.
     *
     * @param fragment The fragment to navigate to.
     */
    fun navigateTo(fragment: BaseFragment) {
        navigationManager.navigateTo(fragment)
    }

    //endregion

    //region private functions

    /**
     * Initialize the action bar title.
     */
    private fun initActionBar() {
        //Sets the title of the action bar to the value defined in [BuildConfig.SOURCE]
        supportActionBar?.title = BuildConfig.SOURCE
    }

    /**
     * Initiates the fingerprint login flow.
     * Checks for the availability of the fingerprint and shows an error if it fails.
     */
    private fun callFingerprintLogin() {
        val fingerprintManager = getSystemService(FingerprintManager::class.java)

        if (!fingerprintManager.isHardwareDetected || !fingerprintManager.hasEnrolledFingerprints()) {
            goToFirstScreen()
            return
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            showFingerprintError()
            return
        }

        val cryptoObject = FingerprintManager.CryptoObject(getCipher())
        fingerprintManager.authenticate(cryptoObject, null, 0, authenticationCallback, null)
    }

    /**
     * Shows an error Toast for fingerprint.
     */
    private fun showFingerprintError() {
        Toast.makeText(this, getString(R.string.error_msg_fingerprint), Toast.LENGTH_LONG).show()
    }

    /**
     * Callback used for fingerprint authentication
     */
    private val authenticationCallback = object : FingerprintManager.AuthenticationCallback() {
        override fun onAuthenticationSucceeded(result: FingerprintManager.AuthenticationResult?) {
            goToFirstScreen()
        }

        override fun onAuthenticationFailed() {
            super.onAuthenticationFailed()
            showFingerprintError()
        }

        override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
            super.onAuthenticationError(errorCode, errString)
            showFingerprintError()
        }
    }

    private fun goToFirstScreen() {
        navigationManager.navigateTo(NewsListFragment.newInstance())
    }

    //endregion
}