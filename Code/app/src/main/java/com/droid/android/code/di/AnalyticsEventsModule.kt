package com.droid.android.code.di

import com.droid.android.code.analytics.AnalyticsEvents
import com.droid.android.code.analytics.AnalyticsEventsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AnalyticsEventsModule {

    @Binds
    @Singleton
    abstract fun bindAnalyticsEvents(analyticsEventsImpl: AnalyticsEventsImpl): AnalyticsEvents
}
