package org.tmartins.bbcnews.manager

import android.support.v4.app.FragmentManager
import org.tmartins.bbcnews.R
import org.tmartins.bbcnews.view.base.BaseFragment

/**
 * Manages navigation between fragments.
 *
 * This class provides methods to navigate between fragments using the provided [fragmentManager]
 * and the container [containerRsc]
 *
 * @param fragmentManager The FragmentManager instance.
 * @param containerRsc The resource ID of the layout container.
 */
class NavigationManager(private val fragmentManager: FragmentManager, private val containerRsc: Int) {

    //region public functions

    /**
     * Navigate to the specified fragment.
     *
     * This function is responsible for navigating to the provided [fragment] within the application.
     *
     * @param fragment The fragment to navigate to.
     */
    fun navigateTo(fragment: BaseFragment) {
        fragmentManager.beginTransaction().apply {
            val currentFragment = getCurrentFragment()
            add(R.id.frame_layout_main_container, fragment)
            if (currentFragment != null) {
                hide(currentFragment)
                addToBackStack(null)
            }
        }.commit()
    }

    fun onBackPressed() {
        fragmentManager.popBackStack()
    }

    //endregion

    //region private functions

    /**
     *  Retrieve the current fragment attached to the FrameLayout container
     *
     *  @return The current fragment, or null if no fragment is currently attached.
     */
    private fun getCurrentFragment() = fragmentManager.findFragmentById(containerRsc)

    //endregion
}
