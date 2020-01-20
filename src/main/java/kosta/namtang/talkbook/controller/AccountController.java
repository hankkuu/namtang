package kosta.namtang.talkbook.controller;

import kosta.namtang.talkbook.common.ShopResponse;
import kosta.namtang.talkbook.common.StatusCode;
import kosta.namtang.talkbook.model.domain.account.Account;
import kosta.namtang.talkbook.model.domain.account.UserAddress;
import kosta.namtang.talkbook.model.domain.account.Users;
import kosta.namtang.talkbook.model.domain.bill.BillKey;
import kosta.namtang.talkbook.model.dto.request.PurchaseSetRequest;
import kosta.namtang.talkbook.model.dto.request.UserSetRequest;
import kosta.namtang.talkbook.service.account.AccountService;
import kosta.namtang.talkbook.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("")
    public ShopResponse signUp(@RequestBody UserSetRequest user) throws Exception {
        ShopResponse result = null;
        log.debug(user.toString());
        System.out.println("1" + user.getEmail());
        Account account = accountService.createAccount(user);

        if (account != null) {
            // 구매 DB 입력 완료
            result = new ShopResponse(StatusCode.Success, JsonUtil.toJson(account));
        } else {
            throw new Exception();
        }

        return result;
    }

    @GetMapping("/checkId")
    public ShopResponse checkId(@RequestParam String id) {
        log.debug(id);

        ShopResponse result = accountService.checkNewUser(id);
        return result;
    }

    @PostMapping("/checkPassword")
    public ShopResponse checkId(@RequestBody String password, HttpServletRequest req) {
        log.debug(password);
        long idx = Long.valueOf(String.valueOf(req.getSession().getAttribute("userIdx")));

        boolean result = accountService.checkPassword( idx, password);
        if (result == true)
            return new ShopResponse(StatusCode.Success, "패스워드 일치");
        else
            return new ShopResponse(StatusCode.Fail, "패스워드 불일치");

    }

    @GetMapping("/updateAccount")
    public ShopResponse updateAccount(HttpServletRequest request) {
        log.debug("updateAccount");
        long userIdx = Long.valueOf(String.valueOf(request.getSession().getAttribute("userIdx")));

        Users user = accountService.updateAccount(userIdx);
        if (user != null)
            return new ShopResponse(StatusCode.Success, JsonUtil.toJson(user));
        else
            return new ShopResponse(StatusCode.Fail, "user 검색 실패");
    }

    @PostMapping("/updateUser")
    public void updateUser(@RequestBody UserSetRequest user, HttpServletRequest request) {
        log.debug("updateUser");
        long userIdx = Long.valueOf(String.valueOf(request.getSession().getAttribute("userIdx")));
        user.setUserIdx(userIdx);
        accountService.updateUser(user);
//        if (user != null)
//            return new ShopResponse(StatusCode.Success, "user 정보 업데이트");
//        else
//            return new ShopResponse(StatusCode.Fail, "user 검색 실패");

    }

    @GetMapping("/userAddress")
    public ShopResponse userAddress(HttpServletRequest request) {
        log.debug("userAddress");
        long userIdx = Long.valueOf(String.valueOf(request.getSession().getAttribute("userIdx")));

        List<UserAddress> list = accountService.selectAddress(userIdx);
        return new ShopResponse(StatusCode.Success, JsonUtil.toJson(list));
    }

    @PostMapping("/deleteUser")
    public void deleteUser(@RequestBody UserSetRequest user, HttpServletRequest request) {
        log.debug("deleteUser");
        long userIdx = Long.valueOf(String.valueOf(request.getSession().getAttribute("userIdx")));

        accountService.deleteAccount(userIdx, user.getPassword());
//        if (user != null)
//            return new ShopResponse(StatusCode.Success, "user 정보 업데이트");
//        else
//            return new ShopResponse(StatusCode.Fail, "user 검색 실패");
    }

    @PostMapping("/findId")
    public ShopResponse findId(@RequestBody UserSetRequest user) {

        Account acc = accountService.selectByEmail(user.getEmail());
        if (acc != null)
            return new ShopResponse(StatusCode.Success, acc.getUserId());
        else
            return new ShopResponse(StatusCode.Fail, "email 검색 실패");
    }

    @PostMapping("/findPassword")
    public ShopResponse findPassword(@RequestBody UserSetRequest user) {

        Account acc = accountService.selectByEmailAndId(user.getEmail(), user.getUserId());
        // 패스워드 초기화가 필요하다면 해서 보내야 한다
        if (acc != null)
            return new ShopResponse(StatusCode.Success, acc.getUserPassword());
        else
            return new ShopResponse(StatusCode.Fail, "password 검색 실패");
    }

    // Security로 대체
//    @PostMapping("/login")
//    public ShopResponse login(@RequestBody UserSetRequest user) throws Exception {
//        ShopResponse result = null;
//        log.debug(user.toString());
//
//        Account acc = new Account();
//        acc.setUserId(user.getEmail());
//        acc.setUserPassword(user.getPassword());
//
//        Account account = accountService.login(acc);
//
//        if(account != null) {
//            // 구매 DB 입력 완료
//            result = new ShopResponse(StatusCode.Success, JsonUtil.toJson(account));
//        } else {
//            throw new Exception();
//        }
//
//        return result;
//    }

}
