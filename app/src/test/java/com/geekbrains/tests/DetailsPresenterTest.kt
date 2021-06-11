package com.geekbrains.tests

import com.geekbrains.tests.presenter.details.DetailsPresenter
import com.geekbrains.tests.presenter.search.SearchPresenter
import com.geekbrains.tests.repository.GitHubRepository
import com.geekbrains.tests.view.details.ViewDetailsContract
import com.geekbrains.tests.view.search.ViewSearchContract
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DetailsPresenterTest {

    private var count = 5

    private lateinit var presenter: DetailsPresenter

    @Mock
    private lateinit var viewContract: ViewDetailsContract

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailsPresenter()
        presenter.onAttach(viewContract)
    }

    @Test
    fun presenter_ViewIsAttached() {
        assertNotNull(presenter.viewContract)
    }

    @Test
    fun presenter_ViewIsDetached() {
        presenter.onDetach()
        assertNull(presenter.viewContract)
    }

    @Test
    fun presenter_SetsCounter() {
        presenter.setCounter(count)
        assertEquals(count, presenter.count)
    }

    @Test
    fun presenter_IncrementsCount() {
        presenter.setCounter(count)
        presenter.onIncrement()
        assertEquals(count + 1, presenter.count)
    }

    @Test
    fun presenter_DecrementsCount() {
        presenter.setCounter(count)
        presenter.onDecrement()
        assertEquals(count - 1, presenter.count)
    }

}

