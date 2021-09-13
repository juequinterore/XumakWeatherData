package co.me.xumakweathedata

import co.me.domain.entities.City

data class MainActivityState(
    val cities: List<City>,
    val currentDayNumber: Int,
    val currentDateText: String
)