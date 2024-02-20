package org.tmartins.bbcnews.view.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.tmartins.bbcnews.BuildConfig
import org.tmartins.bbcnews.R
import org.tmartins.bbcnews.manager.NavigationManager
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
        if(savedInstanceState == null) navigationManager.navigateTo(NewsListFragment.newInstance())
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

    //endregion
}