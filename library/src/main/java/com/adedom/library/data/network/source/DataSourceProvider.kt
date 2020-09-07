package com.adedom.library.data.network.source

import com.adedom.library.data.network.ServiceFactory
import com.adedom.library.data.network.api.TegAuthApi

class DataSourceProvider(private val serviceFactory: ServiceFactory) {

    fun getTegAuthDataSource(): TegAuthApi = serviceFactory.create(TegAuthApi::class.java)

}
