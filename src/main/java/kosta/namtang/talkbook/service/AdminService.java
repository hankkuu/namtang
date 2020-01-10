package kosta.namtang.talkbook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import kosta.namtang.talkbook.model.domain.Admin;
import kosta.namtang.talkbook.model.domain.Statistics;
import kosta.namtang.talkbook.repository.AdminRepository;
import kosta.namtang.talkbook.repository.StatisticsRepository;

@Service
public class AdminService {
	 
	@Autowired
	private StatisticsRepository statisticsRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	public List<Statistics> selectAll(){
		return Lists.newArrayList(statisticsRepository.findAll());
	}
	
	public Optional<Admin> selectById(Admin admin) {
		Optional<Admin> result = adminRepository.findById(admin.getAdminId());
		System.out.println(admin + "ddddddddd");
		return null;
	}
	
	public String insertStatistics(Statistics statistics) {
		Statistics result= statisticsRepository.save(statistics);
		System.out.println(result);
		return null;
	}
	

}
