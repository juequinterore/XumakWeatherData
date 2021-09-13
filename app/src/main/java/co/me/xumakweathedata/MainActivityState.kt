package co.me.xumakweathedata

import co.me.domain.entities.City

data class MainActivityState(
    val city: City?,
    val cities: List<City>,
    val currentDayNumber: Int,
    val currentDateText: String,
    val selectedCityIndex: Int,
)