/*
 * Copyright (c) 2022 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.yourcompany.android.awarenessfood

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.yourcompany.android.awarenessfood.databinding.FragmentFoodTriviaBinding
import com.yourcompany.android.awarenessfood.repositories.models.FoodTriviaApiState
import com.yourcompany.android.awarenessfood.viewmodels.FoodTriviaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodTriviaFragment : Fragment() {

  private val viewModel: FoodTriviaViewModel by viewModels()

  private var binding: FragmentFoodTriviaBinding? = null

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    binding = FragmentFoodTriviaBinding.inflate(layoutInflater, container, false)
    return binding?.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    viewModel.foodTriviaState.observe(viewLifecycleOwner, Observer {
      handleFoodTriviaApiState(it)
    })

    binding?.apply {
      getFoodTriviaButton.setOnClickListener {
        viewModel.getRandomFoodTrivia()
      }
    }

    viewModel.getRandomFoodTrivia()
  }

  override fun onDestroyView() {
    super.onDestroyView()
    binding = null
  }

  private fun handleFoodTriviaApiState(foodTriviaApiState: FoodTriviaApiState) {
    when (foodTriviaApiState) {
      is FoodTriviaApiState.Result -> binding?.foodTriviaTextView?.text = foodTriviaApiState.trivia
      FoodTriviaApiState.Error -> showError()
    }
  }

  private fun showError() {
    binding?.let {
      Snackbar.make(it.root, getString(R.string.trivia_error), Snackbar.LENGTH_SHORT)
          .apply {
            view.setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.purple_500))
            show()
          }
    }
  }
}