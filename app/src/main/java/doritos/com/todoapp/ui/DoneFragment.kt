package doritos.com.todoapp.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import doritos.com.todoapp.databinding.DoneFragmentBinding
import doritos.com.todoapp.viewmodels.DoneViewModel


class DoneFragment : Fragment() {

    companion object {
        fun newInstance() = DoneFragment()
    }

    private lateinit var viewModel: DoneViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DoneFragmentBinding.inflate(inflater, container, false)

        //val context = context ?: return binding.root
        viewModel = ViewModelProviders.of(this).get(DoneViewModel::class.java)

        return binding.root
    }

}
