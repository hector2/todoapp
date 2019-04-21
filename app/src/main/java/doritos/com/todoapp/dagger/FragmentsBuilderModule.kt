package doritos.com.todoapp.dagger



import dagger.Module
import dagger.android.ContributesAndroidInjector
import doritos.com.todoapp.ui.TasksListFragment

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeTasksListFragment(): TasksListFragment


}