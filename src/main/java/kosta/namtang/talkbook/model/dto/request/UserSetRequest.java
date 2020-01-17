package kosta.namtang.talkbook.model.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;

@ToString
@Getter
@Setter
public class UserSetRequest {

    private long userIdx;
    private String userId;
    private String password;
    private String userName;
    private String sex;
    @Email
    private String email;
    private String phone;
    private String userPost;
    private String userAddress;
    private String userAddressDetail;

}
