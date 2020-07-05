package com.example.cmtestapp

import com.example.cmtestapp.utils.ResponseDataParser.getIdFromUrl
import com.example.cmtestapp.utils.ResponseDataParser.getLastPageNumber
import com.example.cmtestapp.utils.ResponseDataParser.parseCharacterDetailsResponse
import com.example.cmtestapp.utils.ResponseDataParser.parseCharacterListResponse
import com.example.cmtestapp.utils.ResponseDataParser.parseNetworkErrorResponse
import org.junit.Test

import org.junit.Assert.*

class ResponseDataParseUnitTest {

    private val charactersListJson = """[{"url":"https://www.anapioficeandfire.com/api/characters/1","name":"","gender":"Female","culture":"Braavosi","born":"","died":"","titles":[""],"aliases":["The Daughter of the Dusk"],"father":"","mother":"","spouse":"","allegiances":[],"books":["https://www.anapioficeandfire.com/api/books/5"],"povBooks":[],"tvSeries":[""],"playedBy":[""]},{"url":"https://www.anapioficeandfire.com/api/characters/2","name":"Walder","gender":"Male","culture":"","born":"","died":"","titles":[""],"aliases":["Hodor"],"father":"","mother":"","spouse":"","allegiances":["https://www.anapioficeandfire.com/api/houses/362"],"books":["https://www.anapioficeandfire.com/api/books/1","https://www.anapioficeandfire.com/api/books/2","https://www.anapioficeandfire.com/api/books/3","https://www.anapioficeandfire.com/api/books/5","https://www.anapioficeandfire.com/api/books/8"],"povBooks":[],"tvSeries":["Season 1","Season 2","Season 3","Season 4","Season 6"],"playedBy":["Kristian Nairn"]},{"url":"https://www.anapioficeandfire.com/api/characters/3","name":"","gender":"Male","culture":"","born":"","died":"","titles":[""],"aliases":["Lamprey"],"father":"","mother":"","spouse":"","allegiances":["https://www.anapioficeandfire.com/api/houses/15"],"books":["https://www.anapioficeandfire.com/api/books/3"],"povBooks":[],"tvSeries":[""],"playedBy":[""]},{"url":"https://www.anapioficeandfire.com/api/characters/4","name":"","gender":"Female","culture":"Braavosi","born":"","died":"","titles":[""],"aliases":["The Merling Queen"],"father":"","mother":"","spouse":"","allegiances":[],"books":["https://www.anapioficeandfire.com/api/books/5","https://www.anapioficeandfire.com/api/books/8"],"povBooks":[],"tvSeries":[""],"playedBy":[""]},{"url":"https://www.anapioficeandfire.com/api/characters/5","name":"","gender":"Male","culture":"","born":"","died":"","titles":[""],"aliases":["Old Crackbones"],"father":"","mother":"","spouse":"","allegiances":[],"books":["https://www.anapioficeandfire.com/api/books/5"],"povBooks":[],"tvSeries":[""],"playedBy":[""]},{"url":"https://www.anapioficeandfire.com/api/characters/6","name":"","gender":"Female","culture":"Braavosi","born":"","died":"","titles":[""],"aliases":["The Poetess"],"father":"","mother":"","spouse":"","allegiances":[],"books":["https://www.anapioficeandfire.com/api/books/5"],"povBooks":[],"tvSeries":[""],"playedBy":[""]},{"url":"https://www.anapioficeandfire.com/api/characters/7","name":"","gender":"Female","culture":"","born":"","died":"","titles":[""],"aliases":["Porridge"],"father":"","mother":"","spouse":"","allegiances":["https://www.anapioficeandfire.com/api/houses/15"],"books":["https://www.anapioficeandfire.com/api/books/3"],"povBooks":[],"tvSeries":[""],"playedBy":[""]},{"url":"https://www.anapioficeandfire.com/api/characters/8","name":"","gender":"Male","culture":"","born":"","died":"","titles":[""],"aliases":["Quickfinger"],"father":"","mother":"","spouse":"","allegiances":["https://www.anapioficeandfire.com/api/houses/23"],"books":["https://www.anapioficeandfire.com/api/books/6"],"povBooks":[],"tvSeries":[""],"playedBy":[""]},{"url":"https://www.anapioficeandfire.com/api/characters/9","name":"","gender":"Female","culture":"","born":"","died":"","titles":[""],"aliases":["the Sailor's Wife"],"father":"","mother":"","spouse":"","allegiances":[],"books":["https://www.anapioficeandfire.com/api/books/5"],"povBooks":[],"tvSeries":[""],"playedBy":[""]},{"url":"https://www.anapioficeandfire.com/api/characters/10","name":"","gender":"Female","culture":"Braavosi","born":"","died":"","titles":[""],"aliases":["The Veiled Lady"],"father":"","mother":"","spouse":"","allegiances":[],"books":["https://www.anapioficeandfire.com/api/books/5"],"povBooks":[],"tvSeries":[""],"playedBy":[""]}]"""
    private val characterDetailsJson = """{"url":"https://www.anapioficeandfire.com/api/characters/823","name":"Petyr Baelish","gender":"Male","culture":"Valemen","born":"In 268 AC, at the Fingers","died":"","titles":["Master of coin (formerly)","Lord Paramount of the Trident","Lord of Harrenhal","Lord Protector of the Vale"],"aliases":["Littlefinger"],"father":"","mother":"","spouse":"https://www.anapioficeandfire.com/api/characters/688","allegiances":["https://www.anapioficeandfire.com/api/houses/10","https://www.anapioficeandfire.com/api/houses/11"],"books":["https://www.anapioficeandfire.com/api/books/1","https://www.anapioficeandfire.com/api/books/2","https://www.anapioficeandfire.com/api/books/3","https://www.anapioficeandfire.com/api/books/5","https://www.anapioficeandfire.com/api/books/8"],"povBooks":[],"tvSeries":["Season 1","Season 2","Season 3","Season 4","Season 5","Season 6"],"playedBy":["Aidan Gillen"]}"""
    private val links = """<https://www.anapioficeandfire.com/api/characters?page=2&pageSize=10>; rel="next", <https://www.anapioficeandfire.com/api/characters?page=1&pageSize=10>; rel="first", <https://www.anapioficeandfire.com/api/characters?page=214&pageSize=10>; rel="last""""
    private val linksMock = """fire.com/api/characters?page=2&pageSize=10"""
    private val characterRealLink = "https://www.anapioficeandfire.com/api/books/1"
    private val characterInvalidLink = "https://www.anapioficeandfire.com/api/books"
    private val invalidJson = "url = 1"

    @Test
    fun `parseCharacterListResponse positive test`() {
        val result = parseCharacterListResponse(charactersListJson, null)
        assertEquals(result.items[0].culture, "Braavosi")
        assertEquals(10, result.items.size)
    }

    @Test
    fun `parseCharacterListResponse negative test with exception, must return empty object`() {
        val result = parseCharacterListResponse(invalidJson, null)
        assertEquals(0, result.items.size)
    }

    @Test
    fun `parseCharacterDetailsResponse positive test`() {
        val result = parseCharacterDetailsResponse(characterDetailsJson)
        assertEquals("Petyr Baelish", result.name)
        assertEquals(6, result.tvSeries.size)
    }

    @Test
    fun `parseCharacterDetailsResponse negative test with json parser exception, must return empty object`() {
        val result = parseCharacterDetailsResponse(invalidJson)
        assertEquals("", result.name)
    }

    @Test
    fun `parseNetworkErrorResponse positive test`() {
        val result = parseNetworkErrorResponse("Error")
        assertEquals("Error", result.error)
    }

    @Test
    fun `parseNetworkErrorResponse positive test 2 with null error text`() {
        val result = parseNetworkErrorResponse(null)
        assertEquals("Unknown Error", result.error)
    }

    @Test
    fun `parseNetworkErrorResponse positive test 2 with empty error text`() {
        val result = parseNetworkErrorResponse("")
        assertEquals("Unknown Error", result.error)
    }

    @Test
    fun `getLastPageNumber positive test with real data`() {
        val result = getLastPageNumber(arrayListOf(links))
        assertEquals("214", result)
    }

    @Test
    fun `getLastPageNumber positive test with mock data`() {
        val result = getLastPageNumber(arrayListOf(linksMock))
        assertEquals("2", result)
    }

    @Test
    fun `getIdFromUrl positive test`() {
        val result = getIdFromUrl(characterRealLink, 0)
        assertEquals(1, result)
    }

    @Test
    fun `getIdFromUrl positive test with invalid data`() {
        val result = getIdFromUrl(characterInvalidLink, 0)
        assertEquals(0, result)
    }

    @Test
    fun `getIdFromUrl positive test with empty data`() {
        val result = getIdFromUrl("", 5)
        assertEquals(5, result)
    }

}