package com.example.demohrutvik
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.demohrutvik.databinding.FragmentStudentListBinding

class StudentListFragment : Fragment() {

    private lateinit var binding: FragmentStudentListBinding
    private lateinit var viewModel: StudentViewModel
    private lateinit var adapter: StudentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val repository = StudentRepository()

        binding = FragmentStudentListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, StudentViewmodelFactory(repository))[StudentViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("StudentListFragment", "Fragment view created")

        adapter = StudentAdapter()
        binding.recy.layoutManager = LinearLayoutManager(requireContext())
        binding.recy.adapter = adapter

        viewModel.fetchStudents() // Initial data fetch
        setDataObservers()

        binding.recy.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!viewModel.isLoading && (firstVisibleItemPosition + visibleItemCount >= totalItemCount)) {
                    // Trigger load more
                    viewModel.fetchStudents()
                }
            }
        })
    }

    private fun setDataObservers() {
        viewModel.students.observe(viewLifecycleOwner, Observer { students ->
            students?.let {

                adapter.setData(it)
                Log.d("StudentListFragment", "Data updated in adapter")
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { errorMessage ->
            // Handle error (show a message, log, etc.)
            Log.e("StudentListFragment", "Error: $errorMessage")
        })
    }
}
