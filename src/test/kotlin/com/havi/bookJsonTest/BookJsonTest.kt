package com.havi.bookJsonTest

import com.havi.domain.Book
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.contentOf
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.boot.test.json.JacksonTester
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDateTime

@RunWith(SpringRunner::class)
@JsonTest
class BookJsonTest {
    @Autowired
    private lateinit var json: JacksonTester<Book>

    @Test
    @Throws(Exception::class)
    fun jsonTest() {
        var book = Book("202", LocalDateTime.now())
        var content = "{\"title\":\"테스트\"}"
        assertThat(this.json.parseObject(content).title.equals(book.title))
        assertThat(this.json.parseObject(content).publishedAt === null)
        assertThat(this.json.write(book)).isEqualToJson("/test.json")
        assertThat(this.json.write(book)).hasJsonPathStringValue("title")
        assertThat(this.json.write(book)).extractingJsonPathStringValue("title").isEqualTo("테스트")
    }
}
