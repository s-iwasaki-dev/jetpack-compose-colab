package s.iwasaki.dev.basicstatecodelab.domain

import WellnessTaskItemState

class WellnessTaskRepository(
    var unstableVariable: String = "make this class unstable"
) {
    fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }.map {
        WellnessTaskItemState(id = it.id, taskName = it.label, checked = it.checked)
    }
}