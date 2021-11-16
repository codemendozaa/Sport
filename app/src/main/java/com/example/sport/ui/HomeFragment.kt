package com.example.sport.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.sport.R
import com.example.sport.core.Resource
import com.example.sport.data.model.Teams
import com.example.sport.data.remote.TeamsDataSource
import com.example.sport.databinding.FragmentHomeBinding
import com.example.sport.presentation.SportViewModel
import com.example.sport.presentation.SportViewModelFactory
import com.example.sport.repository.RetrofitClient
import com.example.sport.repository.SportRepositoryImpl
import com.example.sport.ui.adapter.concat.SpanishLigaConcatAdapter
import com.example.sport.ui.adapter.SportAdapter as SportAdapter


class HomeFragment : Fragment(R.layout.fragment_home), SportAdapter.OnMovieClickListener {
    private lateinit var binding: FragmentHomeBinding

    private val viemodel by viewModels<SportViewModel> {
        SportViewModelFactory(
            SportRepositoryImpl(
                TeamsDataSource(RetrofitClient.webservice)
            )
        )
    }

    private lateinit var concatAdapter:ConcatAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        concatAdapter = ConcatAdapter()

        viemodel.fetchScreenTeams().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("Service","${result.data}")

                   concatAdapter.apply {
                       addAdapter(0,SpanishLigaConcatAdapter(SportAdapter(result.data.teams,this@HomeFragment)))

                   }
                    binding.rvSport.adapter =concatAdapter

                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("Error", "${result.excepcion}")
                }


            }

        })

    }

    override fun onMovieClick(team: Teams) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailViewFragment(
            team.strTeam,
            team.strTeamBadge,
            team.strWebsite,
            team.strTwitter,
            team.strTeamJersey,
            team.strDescriptionES,
            team.strTeamLogo,
            team.strTeamBanner,
            team.strInstagram,
            team.intFormedYear.toString()

        )
        findNavController().navigate(action)
    }

}