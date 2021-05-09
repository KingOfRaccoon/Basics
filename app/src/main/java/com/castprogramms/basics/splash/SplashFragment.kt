package com.castprogramms.basics.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.castprogramms.basics.R
import com.castprogramms.basics.databinding.SplashFragmentBinding

class SplashFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view = inflater.inflate(R.layout.splash_fragment, container, false)
        val binding = SplashFragmentBinding.bind(view)
        val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.show_anim)
        anim.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {
                }

            override fun onAnimationEnd(animation: Animation?) {
                binding.goToEnter.visibility = View.VISIBLE
                binding.goToRegistration.visibility = View.VISIBLE
            }

            override fun onAnimationStart(animation: Animation?) {

            }
        })
        binding.goToEnter.startAnimation(anim)
        binding.goToRegistration.startAnimation(anim)

        binding.goToRegistration.setOnClickListener {
            findNavController().navigate(R.id.action_splashFragment_to_insertDataFragment)
        }
        binding.goToEnter.setOnClickListener {
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }
        return view
    }

}