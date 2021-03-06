package doritos.com.todoapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import doritos.com.todoapp.R
import kotlinx.android.synthetic.main.main_activity.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        navController = Navigation.findNavController(this, R.id.nav_fragment)
        bottom_navigation.setupWithNavController(navController)
    }

}
