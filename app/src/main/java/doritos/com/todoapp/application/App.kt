package doritos.com.todoapp.application

import android.app.Application
import doritos.com.todoapp.BuildConfig
import doritos.com.todoapp.dagger.AppComponent
import doritos.com.todoapp.dagger.AppModule
import doritos.com.todoapp.dagger.DaggerAppComponent
import doritos.com.todoapp.dagger.DaggerComponentProvider
import timber.log.Timber


class App() : Application(), DaggerComponentProvider {

    override val component: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }


    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree());
        }
    }
}