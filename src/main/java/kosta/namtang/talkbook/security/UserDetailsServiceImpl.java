package kosta.namtang.talkbook.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kosta.namtang.talkbook.model.domain.Admin;
import kosta.namtang.talkbook.repository.AdminRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private PasswordEncoder passwordEncoder;

	@Autowired
	private AdminRepository adminRepository;

    public UserDetailsServiceImpl() {
    	System.out.println("UserDetailsServiceImpl 생성자............");
    }
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername(String username)  : username : " + username);

//		  Optional<AccountModel> accountModelOptional = repository.findByUsername(username);
//		  if(accountModelOptional.isPresent()) {
//		  	AccountModel m = accountModelOptional.get(); String[] roles = m.getRoles().stream().map(
//		  			RoleModel::getName).map(x -> x.name()).toArray(String[]::new);
//		  			// test 로 진행하느라 password encorder를 여기서 사용. NoOpPasswordEncoder으로 변경
//			  // return User.builder().username(m.getUsername()).password(passwordEncoder.encode(m.getPassword())).roles(roles).build();
//			  return User.builder().username(m.getUsername()).password(m.getPassword()).roles(roles).build();
//		  } else {
//		  	throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
//		  }

		Admin admin = adminRepository.findByAdminId(username);
		System.out.println("admin : " + admin);

		return User.builder().username(admin.getAdminId()).password(admin.getAdminPassword()).roles(admin.getAdminRoleName()).build();
		//return null;
	}

}
