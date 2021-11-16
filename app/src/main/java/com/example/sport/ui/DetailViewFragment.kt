package com.example.sport.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.sport.databinding.FragmentDetailViewBinding
import android.R


class DetailViewFragment : Fragment(com.example.sport.R.layout.fragment_detail_view) {

    private lateinit var binding: FragmentDetailViewBinding
    private val args by navArgs<DetailViewFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailViewBinding.bind(view)

        Glide.with(requireContext())
            .load(args.strTeamBanner).centerCrop().into(binding.imgBackground)
        Glide.with(requireContext())
            .load(args.strTeamJersey).centerCrop().into(binding.imgTshirt)


        binding.titleTeams.text = args.strTeam
        binding.txtDescription.text = args.strDescriptionES
        binding.txtYearFundation.text = " Fundaditon Year:${args.intFormedYear}"

        binding.linkWeb.setOnClickListener {

            requireContext().startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(args.strWebsite)))
        }

        binding.linkIstagram.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(args.strInstagram)))


        }

        binding.linkTwiter.setOnClickListener {

            requireContext().applicationContext.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(args.strTwitter)
                )
            )


        }


    }

}