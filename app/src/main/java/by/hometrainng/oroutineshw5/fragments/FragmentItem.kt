package by.hometrainng.oroutineshw5.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import by.hometrainng.oroutineshw5.databinding.FragmentItemBinding
import by.hometrainng.oroutineshw5.model.Character
import by.hometrainng.oroutineshw5.retrofit.FinalSpaceService
import coil.load
import kotlinx.coroutines.launch

class FragmentItem: Fragment() {

    private var _binding: FragmentItemBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val args by navArgs<FragmentItemArgs>()

    private val characterRepository by lazy(LazyThreadSafetyMode.NONE) {
        FinalSpaceService.provideRepository()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentItemBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val characterId = args.itemId
        with(binding) {
            toolbarCharacter.setupWithNavController(findNavController())
        }
        loadCharacterDetails(characterId)
    }

    private fun loadCharacterDetails(characterId: Int) {
        with(binding) {
            viewLifecycleOwner.lifecycleScope.launch {
                try {
                    val character = characterRepository.getCharacterDetails(characterId)
                    image.load(character.imageURL)
                    name.text = character.name
                    species.text = SPECIES.format(character.species)
                    status.text = STATUS.format(character.status)
                    gender.text = GENDER.format(character.gender)
                    hair.text = HAIR.format(character.hair)
                } catch (e: Throwable) {
                    FAILURE_MESSAGE.showToastMessage()
                }
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
        private const val SPECIES = "Species: %s"
        private const val GENDER = "Gender: %s"
        private const val STATUS = "Status: %s"
        private const val HAIR = "Hair: %s"
        private const val FAILURE_MESSAGE = "Upload failure"
    }
}