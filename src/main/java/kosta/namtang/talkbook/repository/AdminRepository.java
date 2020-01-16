package kosta.namtang.talkbook.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import kosta.namtang.talkbook.model.domain.Admin;

public interface AdminRepository extends CrudRepository<Admin, String> {

    Admin findByAdminId(String username);

}
