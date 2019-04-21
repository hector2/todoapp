package doritos.com.todoapp.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import doritos.com.todoapp.ui.MainActivity


@Suppress("unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}