package org.tmartins.bbcnews.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_base_service.*
import org.tmartins.bbcnews.R
import org.tmartins.bbcnews.contract.NewsListContract
import org.tmartins.bbcnews.extension.setComponentVisibility
import org.tmartins.bbcnews.presenter.Response

/**
 * Base Fragment for fragments that make initial requests.
 *
 * It includes its own layout and add child fragment layouts into its own.
 * It is responsible for managing the visibility of layout components defined in [Component]
 *
 * @param T The type of data expected to be received on initial request.
 */
abstract class BaseServiceFragment<T> : BaseFragment(), NewsListContract.View<T> {

    private var childLayout: ViewGroup? = null

    //region override functions

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_base_service, container, false) as ViewGroup
        //add child fragment layout
        // Check if childLayout is null or not
        if (childLayout == null) {
            childLayout = inflater.inflate(getLayoutResource(), container, false) as ViewGroup
            rootView.addView(childLayout)
        }
        return rootView
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) callInitRequest() else onRestore(savedInstanceState)
    }

    override fun onResponse(response: Response<T>) {
        when (response) {
            is Response.Loading -> onLoading()
            is Response.Error -> onError(response.message)
            is Response.Success -> onSuccess(response.data)
        }
    }

    //endregion

    //region components visibility

    private fun onLoading() {
        updateComponentsVisibility(Component.LOADER)
    }

    private fun onSuccess(data: T?) {
        updateComponentsVisibility(Component.CONTAINER)
        onSuccessResponse(data)
    }

    private fun onError(message: String?) {
        text_view_base_service_error_msg.text = message
        updateComponentsVisibility(Component.ERROR)
    }

    private fun updateComponentsVisibility(component: Component) {
        childLayout?.setComponentVisibility(component == Component.CONTAINER)
        progress_bar_base_service_loader.setComponentVisibility(component == Component.LOADER)
        text_view_base_service_error_msg.setComponentVisibility(component == Component.ERROR)
    }

    //endregion

    //region abstract functions

    /**
     * Called when a successful response is received. It provides the response data [response] of type [T]
     *
     * @param response The response data received upon successful completion.
     */
    protected abstract fun onSuccessResponse(response: T?)

    /**
     * Responsible for initiating the initial service request.
     */
    protected abstract fun callInitRequest()

    /**
     * Retrieve the layout resource ID.
     *
     * @return The layout resource ID to be inflated by the children fragment.
     */
    protected abstract fun getLayoutResource(): Int

    /**
     * Called when fragment is recreated
     */
    protected abstract fun onRestore(savedInstanceState: Bundle?)

    //endregion
}

/**
 * Represents the service fragment components whose visibility should be managed.
 */
private enum class Component {
    //Application loader. Should be visible when requests is being called.
    LOADER,
    //Fragment layout. Should be visible if the request is completed successfully and there are no errors.
    CONTAINER,
    //Error State. Should be visible if initial requests returns an error.
    ERROR,
}
