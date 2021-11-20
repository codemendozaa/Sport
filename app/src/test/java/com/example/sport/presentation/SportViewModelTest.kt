package com.example.sport.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.sport.core.Resource
import com.example.sport.data.model.Teams
import com.example.sport.data.model.TeamsList
import com.example.sport.getOrAwaitValue
import com.example.sport.repository.SportRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SportViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    val testCoroutineDispatcher = TestCoroutineDispatcher()

    @Mock
    lateinit var repository: SportRepository

    @Mock
    private lateinit var observer: Observer<Resource<List<TeamsList>>>

    private lateinit var viewModel: SportViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testCoroutineDispatcher)
        viewModel = SportViewModel(repository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Get meaning by criteria should be success`() {
        testCoroutineDispatcher.runBlockingTest {
            val resultExpect = getListMeaningResponseMock()
            BDDMockito.given(repository.getSpanishLigaTeams()).willReturn(resultExpect)

            viewModel.fetchScreenTeams().observeForever(observer)
            viewModel.fetchScreenTeams().getOrAwaitValue()

            Mockito.verify(observer).onChanged(Resource.Loading())
            viewModel.fetchScreenTeams().getOrAwaitValue()

            Mockito.verify(observer)
                .onChanged(Resource.Success(resultExpect))
        }
    }

    fun getListMeaningResponseMock(): List<Teams> {
        return arrayListOf(
            getMeaningResponseMock() ,

            getMeaningResponseMock().copy(idTeam = 1)
        )
    }

    fun getMeaningResponseMock(): Teams {
        return Teams(

            0,123,2,"test","test","test,","test",2021,"test",
            "test",0,"test",0,"test","test","test","test","test","test",
            "test","tests","test","test","test","test","test","test","test","test","test",
            "test",80,"test","test","test#","test","test","test",
            "test","test","test","test","test","test","test","test",
            "test","test","test","test","test","test","test","test",
            "test","test","test","test","test","test","test","test","test"

        )

    }






    @ExperimentalCoroutinesApi
    @Test(expected = Exception::class)
    fun `Get meanings by criteria should be error`() {
        testCoroutineDispatcher.runBlockingTest {
            val resultExpect = Exception()

            BDDMockito.given(repository.getSpanishLigaTeams()).willThrow(resultExpect)

            viewModel.fetchScreenTeams().observeForever(observer)
            viewModel.fetchScreenTeams().getOrAwaitValue()

            Mockito.verify(observer).onChanged(Resource.Loading())
            viewModel.fetchScreenTeams().getOrAwaitValue()

            Mockito.verify(observer)
                .onChanged(Resource.Failure(resultExpect))
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Get last searches should be success`() {
        testCoroutineDispatcher.runBlockingTest {
            val resultExpect = getListMeaningResponseMock()
            BDDMockito.given(repository.getSpanishLigaTeams()).willReturn(resultExpect)

            viewModel.fetchScreenTeams().observeForever(observer)
            viewModel.fetchScreenTeams().getOrAwaitValue()

            Mockito.verify(observer)
                .onChanged( Resource.Success(resultExpect))
        }
    }

    @ExperimentalCoroutinesApi
    @Test(expected = Exception::class)
    fun `Get last searches should be error`() {
        testCoroutineDispatcher.runBlockingTest {
            val resultExpect = Exception()
            BDDMockito.given(repository.getSpanishLigaTeams()).willThrow(resultExpect)

            viewModel.fetchScreenTeams().observeForever(observer)
            viewModel.fetchScreenTeams().getOrAwaitValue()

            Mockito.verify(observer)
                .onChanged( Resource.Failure(resultExpect))
        }
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }
}

private fun <T> BDDMockito.BDDMyOngoingStubbing<T>.willReturn(resultExpect: List<Teams>) {

}

private fun <T> Observer<T>.onChanged(success: Resource.Success<List<Teams>>) {

}


private fun <T> LiveData<T>.observeForever(observer: Observer<Resource<List<TeamsList>>>) {

}


