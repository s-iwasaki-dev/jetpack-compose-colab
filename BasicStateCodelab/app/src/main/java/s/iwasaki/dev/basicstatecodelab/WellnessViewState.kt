package s.iwasaki.dev.basicstatecodelab

import WellnessTasksListState
import s.iwasaki.dev.basicstatecodelab.composable.WaterCounterState

data class WellnessViewState(
    val counterBlock: CounterBlock,
    val taskListBlock: TaskListBlock,
) {
    data class CounterBlock(
        val waterCounterState: WaterCounterState
    )

    data class TaskListBlock(
        val tasksListState: WellnessTasksListState
    )
}
