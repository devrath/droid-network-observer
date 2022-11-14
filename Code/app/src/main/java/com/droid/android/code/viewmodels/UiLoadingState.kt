package com.droid.android.code.viewmodels

sealed class UiLoadingState {
  object Loading : UiLoadingState()
  object NotLoading : UiLoadingState()
}