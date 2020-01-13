package kosta.namtang.talkbook.model.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;

@ToString
@Getter
@Setter
public class UserSetRequest {

	@Email
	private String email;
	private String password;
	private String userName;
	private String sex;
	private String age;
	private String phone;
	private String address;
	
}
