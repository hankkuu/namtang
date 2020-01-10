package kosta.namtang.talkbook.service;

import kosta.namtang.talkbook.model.domain.Review;
import kosta.namtang.talkbook.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repo;


    public void insert(Review dto){

        repo.save(dto);

    }




}
