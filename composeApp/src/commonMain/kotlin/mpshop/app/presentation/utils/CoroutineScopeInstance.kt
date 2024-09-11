package mpshop.app.presentation.utils

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

class CoroutineScopeInstance(
    mainContext: CoroutineContext,
) : InstanceKeeper.Instance {

    val scope = CoroutineScope(mainContext + SupervisorJob())

    override fun onDestroy() {
        scope.cancel()
    }

}