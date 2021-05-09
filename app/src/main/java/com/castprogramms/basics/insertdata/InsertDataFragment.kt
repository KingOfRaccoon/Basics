package com.castprogramms.basics.insertdata

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.castprogramms.basics.R
import com.castprogramms.basics.databinding.FragmentInsertDataBinding
import com.castprogramms.karma.tools.User
import com.castprogramms.karma.ui.insertdata.InsertDataViewModel
import org.koin.android.ext.android.inject

class InsertDataFragment: Fragment() {
    private val insertDataViewModel : InsertDataViewModel by inject()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_insert_data, container, false)
        val binding = FragmentInsertDataBinding.bind(view)
        val listCheck = mutableListOf<Boolean>()
        binding.nameContainer.setHelperTextColor(ColorStateList.valueOf(Color.rgb(139, 128, 249)))
        binding.familyContainer.setHelperTextColor(ColorStateList.valueOf(Color.rgb(139, 128, 249)))
        binding.numberContainer.setHelperTextColor(ColorStateList.valueOf(Color.rgb(139, 128, 249)))
        binding.emailContainer.setHelperTextColor(ColorStateList.valueOf(Color.rgb(139, 128, 249)))
        binding.passwordContainer.setHelperTextColor(ColorStateList.valueOf(Color.rgb(139, 128, 249)))
        binding.passwordRepeatContainer.setHelperTextColor(ColorStateList.valueOf(Color.rgb(139, 128, 249)))
        binding.registration.setOnClickListener {
            if (binding.name.text.isNullOrBlank()) {
                binding.nameContainer.helperText = "Введите имя"
                listCheck.add(false)
            }
            if (binding.family.text.isNullOrBlank()) {
                binding.familyContainer.helperText = "Введите фамилию"
                listCheck.add(false)
            }

            if (binding.number.text.isNullOrBlank()) {
                binding.numberContainer.helperText = "Введите номер телефона"
                listCheck.add(false)
            }

            if (binding.email.text.isNullOrBlank()) {
                binding.emailContainer.helperText = "Введите электронную почту"
                listCheck.add(false)
            }

            if (binding.password.text.isNullOrBlank()) {
                binding.passwordContainer.helperText = getString(R.string.invalid_password)
                listCheck.add(false)
            }

            if (binding.passwordRepeat.text.isNullOrBlank()) {
                binding.passwordRepeatContainer.helperText = "Введите пароль"
                listCheck.add(false)
            }
            if (!listCheck.contains(false)) {
                insertDataViewModel.addUser(User(
                    binding.name.text.toString(),
                    binding.family.text.toString(),
                    binding.number.text.toString()
                ), binding.email.text.toString(), binding.password.text.toString())
                findNavController().navigate(R.id.action_loginFragment_to_insertDataFragment)
            }
        }
        return view
    }
}