package com.yourcompany.android.awarenessfood.di

import com.yourcompany.android.awarenessfood.network.RecipesService
import com.yourcompany.android.awarenessfood.repositories.FoodTriviaRepository
import com.yourcompany.android.awarenessfood.repositories.FoodTriviaRepositoryImpl
import com.yourcompany.android.awarenessfood.repositories.RecipeRepository
import com.yourcompany.android.awarenessfood.repositories.RecipeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val API_KEY = "5a1e4aee6fd74a2b9d23fd2d14f0706f"

@Module
@InstallIn(SingletonComponent::class)
object RecipesModule {

  @Provides
  @Singleton
  fun providesRetrofitService(): RecipesService {
    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor { chain ->
          val url = chain.request().url().newBuilder()
              .addQueryParameter("apiKey", API_KEY)
              .build()

          val requestBuilder = chain.request().newBuilder().url(url)
          chain.proceed(requestBuilder.build())
        }
        .build()

    val builder = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://api.spoonacular.com/")
        .addConverterFactory(GsonConverterFactory.create())

    return builder.build().create(RecipesService::class.java)
  }

  @Provides
  @Singleton
  fun providesRecipeRepository(recipesService: RecipesService): RecipeRepository {
      return RecipeRepositoryImpl(recipesService)
  }

  @Provides
  @Singleton
  fun providesTriviaRepository(recipesService: RecipesService): FoodTriviaRepository {
      return FoodTriviaRepositoryImpl(recipesService)
  }
}