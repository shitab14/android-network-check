package smir.shitab14.networkcheck.network_util

/**
 * Created by Shitab Mir on 16 August, 2022.
 * HungryNaki (Technology), Daraz Bangladesh Limited, Alibaba Group
 * mushfiq.islam@hungrynaki.com | shitabmir@gmail.com
 **/
import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun observe(): Flow<Status>

    enum class Status {
        Available, Unavailable, Losing, Lost
    }
}