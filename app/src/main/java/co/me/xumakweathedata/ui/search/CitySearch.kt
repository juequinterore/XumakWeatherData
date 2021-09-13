package co.me.xumakweathedata.ui.search

import co.me.domain.entities.City

sealed class CitySearch
object CitySearchError : CitySearch()
object CitySearchInProgress : CitySearch()
object CitySearchInitial : CitySearch()
sealed class CitySearchSuccess(val cities: List<City>) : CitySearch()
class CitySearchResult(_cities: List<City>) : CitySearchSuccess(cities = _cities)
object CitySearchSuccessEmpty : CitySearchSuccess(cities = emptyList())