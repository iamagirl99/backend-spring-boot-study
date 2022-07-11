package com.havi.bookRestTest

import com.havi.service.BookRestService
import com.sun.net.httpserver.HttpServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.jupiter.api.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import org.springframework.test.web.client.response.MockRestResponseCreators.withServerError
import org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess
import org.springframework.web.client.HttpServerErrorException
import sun.security.x509.OIDMap.getClass

@RunWith(SpringRunner::class)
@RestClientTest(BookRestService::class)
class BookRestTest {
    @Rule
    var thrown: ExpectedException = ExpectedException.none()

    @Autowired
    private lateinit var bookRestService: BookRestService

    @Autowired
    private lateinit var server: MockRestServiceServer

    @Test
    fun restTest() {

        // this.server.expect(requestTo("/rest/test")).andRespond(withSuccess(ClassPathResource("/test.json").classLoader), MediaType.APPLICATION_JSON)
        var book = this.bookRestService.getRestBook()
        assertThat(book.title).isEqualTo("테스트")
    }

    @Test
    fun restErrorTest() {
        this.server.expect(requestTo("/rest/test")).andRespond(withServerError())
        this.thrown.expect(HttpServerErrorException::class.java)
        this.bookRestService.getRestBook()
    }
}
