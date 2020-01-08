package kosta.namtang.talkbook.service;

import kosta.namtang.talkbook.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    //유저, 책 repo 추가해야됨

}
