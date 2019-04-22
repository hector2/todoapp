package doritos.com.todoapp.dagger

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import dagger.Component
import doritos.com.todoapp.ui.TasksListFragment
import javax.inject.Singleton
import dagger.BindsInstance



/*
 * We mark this interface with the @Component annotation.
 * And we define all the modules that can be injected.
 * Note that we provide AndroidSupportInjectionModule.class
 * here. This class was not created by us.
 * It is an internal class in Dagger 2.10.
 * Provides our activities and fragments with given module.
 * */
@Singleton
@Component(
    modules = [
        AppModule::class]
)
interface AppComponent {
    fun inject(fragment: TasksListFragment)


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }

}