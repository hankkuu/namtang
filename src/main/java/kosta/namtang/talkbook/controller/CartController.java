package kosta.namtang.talkbook.controller;

import kosta.namtang.talkbook.model.domain.Cart;
import kosta.namtang.talkbook.model.domain.CartId;
import kosta.namtang.talkbook.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @RequestMapping("cart")
    public ModelAndView index() {
        List<Cart> list = cartService.selectByUserIdx(1L);

        return new ModelAndView("user/cart", "cartList", list);
    }

    @RequestMapping("cart/insert")
    public void insert(Cart cart) {
        CartId cartId = new CartId();
        cartId.setBookIdx(1L);
        cartId.setUserIdx(3L);

        cart.setBookPrice(114000);
        cart.setBookTitle("테스트");

        cartService.insert(cart);

    }

    @RequestMapping("cart/delete")
    public String delete(@RequestParam Long userIdx, @RequestParam Long bookIdx) {

        cartService.delete(userIdx, bookIdx);


        return "redirect:/cart";
    }

}
