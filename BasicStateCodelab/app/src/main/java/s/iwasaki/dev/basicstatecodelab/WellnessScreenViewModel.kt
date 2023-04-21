
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import s.iwasaki.dev.basicstatecodelab.domain.WellnessTask

class WellnessScreenViewModel : ViewModel() {
    private val _viewState = MutableStateFlow(WellnessScreenState.initialState)
    val viewState = _viewState.asStateFlow()

    init {
        _viewState.update {
            it.copy(
                wellnessTasksListState = WellnessTasksListState(
                    list = getWellnessTasks()
                )
            )
        }
    }

    fun onIncrement() {
        // FIXME ViewStateインスタンスを作り直すとすべてのComposableの再コンポーズが走ってしまってそう（ログ的には走っちゃってる）
        _viewState.update {
            it.copy(
                waterCounterState = it.waterCounterState.copy(
                    count = it.waterCounterState.count + 1
                )
            )
        }
    }

    fun onCloseTask(taskId: Int) {
        _viewState.update {
            it.copy(
                wellnessTasksListState = it.wellnessTasksListState.copy(
                    list = it.wellnessTasksListState.list.filterNot { it.id == taskId }
                )
            )
        }
    }

    fun onCheckedTask(taskId: Int, checked: Boolean) {
        // FIXME listを丸ごと作り直しているのでパフォーマンスが悪い
        _viewState.update {
            it.copy(
                wellnessTasksListState = it.wellnessTasksListState.copy(
                    list = it.wellnessTasksListState.list.map { item ->
                        if (item.id == taskId) item.copy(checked = checked) else item
                    }
                )
            )
        }
    }
}

private fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }.map {
    WellnessTaskItemState(id = it.id, taskName = it.label, checked = it.checked)
}