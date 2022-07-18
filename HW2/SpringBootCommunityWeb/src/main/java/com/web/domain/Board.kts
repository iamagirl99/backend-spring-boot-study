package com.web.domain

import com.web.domain.enums.BoardType
import org.apache.catalina.User
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table
class Board(){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idx: Long = 0

    lateinit var title: String
    lateinit var subTitle: String
    lateinit var content: String
    @Enumerated(EnumType.STRING)
    lateinit var boardType: BoardType
    lateinit var createdDate: LocalDateTime
    lateinit var updatedDate: LocalDateTime
    @OneToOne(fetch = FetchType.LAZY)
    lateinit var user: User

    constructor(title : String, subTitle : String, content : String, boardType : BoardType, createDate : LocalDateTime, updateDate : LocalDateTime, user : User) : this() {
        this. title = title
        this. subTitle = subTitle
        this. content = content
        this.boardType = boardType
        this.createdDate = createDate
        this.updatedDate = updateDate
        this.user = user
    }
}