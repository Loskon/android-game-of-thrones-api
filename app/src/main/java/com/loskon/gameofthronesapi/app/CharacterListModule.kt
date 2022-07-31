package com.loskon.gameofthronesapi.app

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class CharacterListModule {

    @Singleton
    @Provides
    abstract fun characterListViewModel(viewModel: CharacterListViewModel): ViewModel
}