package doritos.com.todoapp.dagger

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import doritos.com.todoapp.data.AppRepository
import doritos.com.todoapp.data.local.AppDatabase
import doritos.com.todoapp.data.local.DbRepository
import doritos.com.todoapp.data.local.dao.TaskDao
import doritos.com.todoapp.data.remote.RestDao
import doritos.com.todoapp.data.remote.RestRepository
import doritos.com.todoapp.utils.DATABASE_NAME
import doritos.com.todoapp.workers.SeedDatabaseWorker
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import doritos.com.todoapp.application.App



@Module()
class AppModule {

    //@Binds
    //internal fun application(app: App): Application


    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    /*
     * The method returns the Retrofit object
     * */
    @Provides
    @Singleton
    internal fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://todoapp-dorito.herokuapp.com")
            .addConverterFactory(
                GsonConverterFactory.create(gson)
            ).build()
    }

    @Provides
    @Singleton
    internal fun provideRestDao(retrofit: Retrofit): RestDao {
        return retrofit.create(RestDao::class.java)
    }

    @Provides
    @Singleton
    internal fun provideRestRepository(restDao: RestDao): RestRepository {
        return RestRepository(restDao)
    }


    /*
     * The method returns the Database object
     * */
    @Provides
    @Singleton
    internal fun provideDatabase(context: Context): AppDatabase {
        //TODO: hacer esto bien
        //return AppDatabase.buildDatabase(application)

        return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                    WorkManager.getInstance().enqueue(request)
                }
            })
            .build()


    }


    /*
     * We need the TaskDao module.
     * For this, We need the AppDatabase object
     * So we will define the providers for this here in this module.
     * */
    @Provides
    @Singleton
    internal fun provideTaskDao(appDatabase: AppDatabase): TaskDao {
        return appDatabase.taskDao()
    }

    @Provides
    @Singleton
    internal fun provideDbRepository(taskDao: TaskDao): DbRepository {
        return DbRepository(taskDao)
    }


    @Provides
    @Singleton
    internal fun provideRepository(restRepository: RestRepository, dbRepository: DbRepository): AppRepository {
        return AppRepository(restRepository, dbRepository)
    }


}