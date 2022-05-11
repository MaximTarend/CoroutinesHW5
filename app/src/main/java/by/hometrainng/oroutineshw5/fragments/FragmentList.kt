package by.hometrainng.oroutineshw5.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import by.hometrainng.oroutineshw5.adapter.ItemAdapter
import by.hometrainng.oroutineshw5.databinding.FragmentListBinding
import by.hometrainng.oroutineshw5.repository.CharacterRepository
import by.hometrainng.oroutineshw5.retrofit.FinalSpaceService
import kotlinx.coroutines.launch


class FragmentList : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val characterRepository by lazy(LazyThreadSafetyMode.NONE) {
        FinalSpaceService.provideRepository()
    }

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        ItemAdapter(requireContext()) { character ->
            findNavController()
                .navigate(
                    FragmentListDirections.toCharacter(character.id)
                )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentListBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            val characters = characterRepository.getCharacters()

            adapter.submitList(characters)
        }

        with(binding) {
            recyclerView.adapter = adapter
            recyclerView.addItemDecoration(
                DividerItemDecoration(requireContext(), LinearLayout.VERTICAL)
            )
        }
    }


    private fun showToastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}