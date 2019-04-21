package doritos.com.todoapp.application

import android.app.Application
import doritos.com.todoapp.dagger.AppComponent
import doritos.com.todoapp.dagger.AppModule
import doritos.com.todoapp.dagger.DaggerAppComponent


class App : Application() {


    override fun onCreate() {
        super.onCreate()
    }
}