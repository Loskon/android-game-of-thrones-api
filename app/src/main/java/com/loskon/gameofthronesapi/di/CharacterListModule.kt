package com.loskon.gameofthronesapi.di

import com.loskon.gameofthronesapi.data.networkdatasource.NetworkDataSource
import com.loskon.gameofthronesapi.data.repositoryimpl.CharacterListRepositoryImpl
import com.loskon.gameofthronesapi.domain.interactor.CharacterListInteractor
import com.loskon.gameofthronesapi.domain.repository.CharacterListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharacterListModule {

    @Singleton
    @Provides
    fun provideCharacterListRepository(dataSource: NetworkDataSource): CharacterListRepository {
        return CharacterListRepositoryImpl(dataSource)
    }

    @Singleton
    @Provides
    fun provideCharacterListInteractor(charactersRepository: CharacterListRepository): CharacterListInteractor {
        return CharacterListInteractor(charactersRepository)
    }
}