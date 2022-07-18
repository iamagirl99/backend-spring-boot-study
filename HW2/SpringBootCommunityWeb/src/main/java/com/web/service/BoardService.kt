package com.web.service

import com.web.domain.Board
import com.web.repository.BoardRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BoardService(
    val boardRepository: BoardRepository
) {
    fun findBoardList(pageable: Pageable): Page<Board> {
        // 코틀린은 삼항연산자가 없음.
        var pageable = PageRequest.of(if(pageable.pageNumber <= 0) 0 else pageable.pageNumber - 1, pageable.pageSize)
        return boardRepository.findAll(pageable)
    }

    fun findBoardByIdx(idx: Long): Board {
        return boardRepository.findById(idx).orElse(Board())
    }
}