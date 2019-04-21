package doritos.com.todoapp.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider



import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import doritos.com.todoapp.viewmodels.TaskListViewModel
import doritos.com.todoapp.viewmodels.ViewModelFactory as ViewModelFactory


@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(TaskListViewModel::class)
    internal abstract fun bindTaskListViewModel(viewModel: TaskListViewModel): ViewModel

    //@Binds
    //internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}