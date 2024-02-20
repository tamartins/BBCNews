package org.tmartins.bbcnews.model.repository

import org.tmartins.bbcnews.model.api.ApiClient
import org.tmartins.bbcnews.model.api.ApiServices

/**
 * Base for all repositories in the model.
 */
open class BaseRepository {

    protected val apiKey = "88bde587a8da43a7b3d394b7e55fe421"

    protected val apiClient: ApiServices = ApiClient.client.create(ApiServices::class.java)
}
