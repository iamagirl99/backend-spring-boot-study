package com.havi.bookControllerTest

import com.havi.controller.BookController
import com.havi.domain.Book
import com.havi.service.BookService
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean

import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc

import java.time.LocalDateTime
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

import java.util.*
import org.hamcrest.Matchers.contains


@RunWith(SpringRunner::class)
@WebMvcTest(BookController::class)
class BookControllerTest {
    @MockBean
    private lateinit var bookService: BookService

    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    fun Book_MVC_test() {
        var book = Book("김20", LocalDateTime.now())

        given(bookService.getBookList()).willReturn(Collections.singletonList(book))
        mvc.perform(get("/books"))
            .andExpect(status().isOk)
            .andExpect(view().name("book"))
            .andExpect(model().attributeExists("bookList"))
            .andExpect(model().attribute("bookList", contains(book)))
    }
}
