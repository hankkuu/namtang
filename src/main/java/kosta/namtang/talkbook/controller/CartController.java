package kosta.namtang.talkbook.controller;

import kosta.namtang.talkbook.model.domain.Cart;
import kosta.namtang.talkbook.model.dto.cart.CartDelRequestDto;
import kosta.namtang.talkbook.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @RequestMapping("cart/delete")
    public String delete(@RequestParam Long userIdx,@RequestParam Long bookIdx) {

        cartService.delete(userIdx,bookIdx);


        return "redirect:/cart";
    }
}
