package co.me.domain.city.use_cases

import co.me.domain.city.ICityRepository
import co.me.domain.entities.City
import co.me.domain.use_cases.ICommandUseCase

data class DeleteCityCommand(val city: City)

interface IDeleteCity : ICommandUseCase<DeleteCityCommand>

class DeleteCity(private val cityRepository: ICityRepository) : IDeleteCity {

    override suspend fun invoke(command: DeleteCityCommand) =
        cityRepository.deleteCity(command.city)

}