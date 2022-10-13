package uz.context.jetpackpagination.screens.detail

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import uz.context.jetpackpagination.repository.Repository
import uz.context.jetpackpagination.util.Constants.TAG
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _state: MutableState<DetailState> = mutableStateOf(DetailState())
    val state: State<DetailState> get() = _state

    fun getImageById(id: String) {
        viewModelScope.launch {
            Log.d(TAG, "getImageById: $id viewmodel")
            repository.getImageById(id)
                .onStart {
                    Log.d(TAG, "getImageById: onstart")
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }.catch {
                    _state.value = _state.value.copy(
                        isLoading = false, error = it.message.toString()
                    )
                    Log.d(TAG, "getImageById: catch ${it.message}")
                }.collect {
                    _state.value = _state.value.copy(
                        detail = it, isLoading = false, error = null
                    )
                    Log.d(TAG, "getImageById: $it collect")
                }
        }
    }
}