package com.example.cmtestapp

import com.example.cmtestapp.config.Config
import com.example.cmtestapp.models.dto.CharacterDetailsDTO
import com.example.cmtestapp.models.dto.CharactersListDTO
import com.example.cmtestapp.models.dto.CharactersListDTOitem
import com.example.cmtestapp.models.viewModels.CharactersViewModelTypes
import com.example.cmtestapp.utils.DtoParser.characterDetailsDtoToViewModel
import com.example.cmtestapp.utils.DtoParser.characterListDtoToViewModel
import org.junit.Test

class DtoParserUnitTest {

    private val charactersListDTOItem = listOf(CharactersListDTOitem(
        name = "Test Name",
        born = "Born",
        died = "Died",
        tvSeries = listOf("1", "2"),
        playedBy = listOf("character1", "character2")
    ))
    private val charactersListDTO = CharactersListDTO(0, charactersListDTOItem)

    private val characterDetailsDTO = CharacterDetailsDTO(
        name = "Test Name",
        born = "Born",
        died = "Died",
        tvSeries = listOf("1", "2"),
        playedBy = listOf("character1", "character2")
    )
    private val characterDetailsEmptyDTO = CharacterDetailsDTO()


    @Test
    fun `characterListDtoToViewModel positive test with not last page`() {
        val result = characterListDtoToViewModel(charactersListDTO, false)
        assert(result.size == 2)
        assert(result.last().type == CharactersViewModelTypes.LOADING.ordinal)
    }

    @Test
    fun `characterListDtoToViewModel positive test with last page`() {
        val result = characterListDtoToViewModel(charactersListDTO, true)
        assert(result.size == 1)
        assert(result.last().type == CharactersViewModelTypes.DATA.ordinal)
    }

    @Test
    fun `characterListDtoToViewModel positive test with null dto`() {
        val result = characterListDtoToViewModel(null, true)
        assert(result.size == 0)
    }

    @Test
    fun `characterDetailsDtoToViewModel positive test`() {
        val result = characterDetailsDtoToViewModel(characterDetailsDTO)
        assert(result.playedBy == "character1, character2")
        assert(result.tvSeries == "1, 2")
    }

    @Test
    fun `characterDetailsDtoToViewModel test with empty dto`() {
        val result = characterDetailsDtoToViewModel(characterDetailsEmptyDTO)
        assert(result.name == Config.EMPTY_STRING_HOLDER)
    }
}