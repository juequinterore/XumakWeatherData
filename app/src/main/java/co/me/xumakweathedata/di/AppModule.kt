package co.me.xumakweathedata.di

import co.me.di.APIURLQualifier
import co.me.xumakweathedata.BuildConfig
import org.koin.dsl.module

val appModule = module {

    single(APIURLQualifier) {
        BuildConfig.API_URL
    }

}