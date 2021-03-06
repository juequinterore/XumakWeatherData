package co.me.xumakweathedata

import android.app.Application
import co.me.di.domainModule
import co.me.di.infrastructureModule
import co.me.xumakweathedata.di.appModule
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class XumakWeatherApplication : Application() {

    @FlowPreview
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@XumakWeatherApplication)
            modules(
                listOf(
                    appModule,
                    domainModule,
                    infrastructureModule
                )
            )
        }
    }

}