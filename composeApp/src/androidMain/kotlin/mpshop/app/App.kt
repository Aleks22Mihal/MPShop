package mpshop.app

import android.app.Application
import mpshop.app.di.initKoin
import org.koin.android.ext.koin.androidContext

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@App)
        }

    }

}
