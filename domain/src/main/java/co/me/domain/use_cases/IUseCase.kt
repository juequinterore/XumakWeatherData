package co.me.domain.use_cases

interface IUseCase<T, R> {
    suspend operator fun invoke(command: T): R
}