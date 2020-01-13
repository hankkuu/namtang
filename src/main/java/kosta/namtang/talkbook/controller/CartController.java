package kosta.namtang.talkbook.controller;

import kosta.namtang.talkbook.model.domain.Cart;
import kosta.namtang.talkbook.model.domain.CartId;
//import kosta.namtang.talkbook.model.domain.User;
import kosta.namtang.talkbook.model.domain.account.Account;
import kosta.namtang.talkbook.model.dto.cart.CartSetRequest;
import kosta.namtang.talkbook.service.CartService;
import kosta.namtang.talkbook.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("cart")
public class CartController {

    private final CartService cartService;

    @RequestMapping("")
    public ModelAndView index() {
        List<Cart> list = cartService.selectByUserIdx(1L);

        return new ModelAndView("user/cart", "cartList", list);
    }

    @RequestMapping("/insert")
    public void insert(Cart cart) {
        CartId cartId = new CartId();
        cartId.setBookIdx(1L);
        cartId.setUserIdx(3L);      ///

        cart.setBookPrice(114000);
        cart.setBookTitle("테스트");

        cartService.insert(cart);

    }

    @RequestMapping("/delete")
    public String delete(@RequestParam Long userIdx, @RequestParam Long bookIdx) {

        cartService.delete(userIdx, bookIdx);


        return "redirect:/cart";
    }

    @RequestMapping(value = "/update",method = RequestMethod.GET)
    @ResponseBody
    public String update(CartSetRequest cart) throws Exception {
        Account account = new Account();
        account.setAccountIdx(1L);
        Cart c = cartService.update(cart, account);
        String s = JsonUtil.toJson(c);
        return s;

    }
}
