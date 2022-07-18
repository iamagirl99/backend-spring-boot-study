package com.web.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idx: Long = 0
    lateinit var name: String
    lateinit var password: String
    lateinit var email: String
    lateinit var createdDate: LocalDateTime
    lateinit var updatedDate: LocalDateTime

    constructor(name : String, password : String, email : String, createDate : LocalDateTime, updateDate : LocalDateTime){
        this.name = name
        this.password = password
        this.email = email
        this.createdDate = createDate
        this.updatedDate = updateDate
    }
}
