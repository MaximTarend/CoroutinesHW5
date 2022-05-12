package by.hometrainng.oroutineshw5.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController

import by.hometrainng.oroutineshw5.adapter.ItemAdapter
import by.hometrainng.oroutineshw5.databinding.FragmentListBinding
import by.hometrainng.oroutineshw5.extentions.addSpaceDecoration
import by.hometrainng.oroutineshw5.retrofit.FinalSpaceService
import by.hometrainng.oroutineshw5.roomDB.appDatabase
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

    private val characterDao by lazy(LazyThreadSafetyMode.NONE) {
        requireContext().appDatabase.characterDao()
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

        getCharactersFromDB()
        loadCharactersToListAndBD()

        with(binding) {
            recyclerView.adapter = adapter
            recyclerView.addSpaceDecoration(SPACE)
            swipeLayout.setOnRefreshListener {
                loadCharactersToListAndBD()
                swipeLayout.isRefreshing = false
            }
        }
    }

    private fun getCharactersFromDB() {
        viewLifecycleOwner.lifecycleScope.launch {
            adapter.submitList(characterDao.getCharacters())
        }
    }

    private fun loadCharactersToListAndBD() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val characters = characterRepository.getCharacters()
                characterDao.insertCharacters(characters)
                adapter.submitList(characters)
            } catch (e: Throwable) {
                FAILURE_MESSAGE.showToastMessage()
            }
        }
    }

    private fun String.showToastMessage() {
        Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val SPACE = 16
        private const val FAILURE_MESSAGE = "Upload failure"
    }
}