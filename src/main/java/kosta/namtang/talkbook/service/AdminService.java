package kosta.namtang.talkbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import kosta.namtang.talkbook.model.domain.Statistics;
import kosta.namtang.talkbook.repository.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	public List<Statistics> selectAll(){
		return Lists.newArrayList(adminRepository.findAll());
	}

}
