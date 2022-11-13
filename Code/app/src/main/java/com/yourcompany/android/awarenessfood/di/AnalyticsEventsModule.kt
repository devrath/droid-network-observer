package com.yourcompany.android.awarenessfood.di

import com.yourcompany.android.awarenessfood.analytics.AnalyticsEvents
import com.yourcompany.android.awarenessfood.analytics.AnalyticsEventsImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
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
