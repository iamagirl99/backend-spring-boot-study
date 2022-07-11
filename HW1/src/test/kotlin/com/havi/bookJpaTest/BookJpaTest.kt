package com.havi.bookJpaTest

import com.havi.domain.Book
import com.havi.repository.BookRepository
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.hasSize
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsEmptyCollection
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDateTime

@RunWith(SpringRunner::class)
@DataJpaTest
class BookJpaTest {
    companion object {
        const val BOOT_TEST_TITLE = "Spring Boot Test Book"
    }

    @Autowired
    private lateinit var testEntityManager: TestEntityManager

    @Autowired
    private lateinit var bookRepository: BookRepository

    @Test
    fun bookSaveTest() {
        var book = Book(BOOT_TEST_TITLE,  LocalDateTime.now())
        testEntityManager.persist(book)
        assertThat(bookRepository.findById(book.idx).get(), `is`(book))


    }

    @Test
    fun bookListSaveAndSearchTest() {
        var book1 = Book(BOOT_TEST_TITLE + "1",  LocalDateTime.now())
        testEntityManager.persist(book1)
        var book2 = Book(BOOT_TEST_TITLE + "2",  LocalDateTime.now())
        testEntityManager.persist(book2)
        var book3 = Book(BOOT_TEST_TITLE + "3",  LocalDateTime.now())
        testEntityManager.persist(book3)

        var bookList = bookRepository.findAll()
        assertThat(bookList, hasSize(3))
        assertThat(bookList, contains(book1, book2, book3))
    }

    @Test
    fun bookListSaveAndDeleteTest() {
        var book1 = Book(BOOT_TEST_TITLE + "1",  LocalDateTime.now())
        testEntityManager.persist(book1)
        var book2 = Book(BOOT_TEST_TITLE + "2",  LocalDateTime.now())
        testEntityManager.persist(book2)

        bookRepository.deleteAll()
        assertThat(bookRepository.findAll(), IsEmptyCollection.empty())
    }
}
