package kosta.namtang.talkbook.service;

import kosta.namtang.talkbook.repository.BookRepository;
import kosta.namtang.talkbook.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;

    private final BookRepository bookRepository;

    //유저 repo 추가해야됨
}
