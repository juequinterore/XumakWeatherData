package co.me.xumakweathedata.main.use_cases

import co.me.domain.use_cases.IQueryUseCase

data class GetCurrentDayCommand(val currentDay: Int)

interface IGetCurrentDayNumber : IQueryUseCase<GetCurrentDayCommand, Int>

class GetCurrentDayNumber : IGetCurrentDayNumber {
    override suspend fun invoke(command: GetCurrentDayCommand): Int {
        val currentDayStartingOn0 = command.currentDay - 1
        val currentDayStartingOnMonday = (currentDayStartingOn0 + 6) % 7

        return currentDayStartingOnMonday
    }

}