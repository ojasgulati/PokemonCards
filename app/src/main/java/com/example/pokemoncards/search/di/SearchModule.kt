package com.example.pokemoncards.search.di

import com.example.pokemoncards.search.domain.SearchRepository
import com.example.pokemoncards.search.domain.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SearchModule {

    @Binds
    abstract fun repo(impl: SearchRepositoryImpl): SearchRepository
}