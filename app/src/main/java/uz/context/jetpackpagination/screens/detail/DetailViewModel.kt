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
            delay(2000)
            repository.getImageById(id)
                .onStart {
                    _state.value = DetailState(isLoading = true)
                }.catch {
                    _state.value = DetailState(isLoading = false, error = it.message.toString())
                }.collect {
                    _state.value = DetailState(detail = it, isLoading = false)
                }
        }
    }
}