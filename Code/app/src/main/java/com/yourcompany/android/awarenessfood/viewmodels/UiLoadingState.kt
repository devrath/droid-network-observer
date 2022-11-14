package com.yourcompany.android.awarenessfood.viewmodels

sealed class UiLoadingState {
  object Loading : UiLoadingState()
  object NotLoading : UiLoadingState()
}