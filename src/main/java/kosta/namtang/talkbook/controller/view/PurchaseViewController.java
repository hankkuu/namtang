package kosta.namtang.talkbook.controller.view;

import kosta.namtang.talkbook.service.bill.PurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping("user")
public class PurchaseViewController {


    @RequestMapping("/purchase")
    public ModelAndView checkout() {
        log.debug("purchase");
        ModelAndView view = new ModelAndView("/user/checkout");
        return view;
    }

    @RequestMapping("/confirmation")
    public ModelAndView confirmation() {
        log.debug("confirmation");
        ModelAndView view = new ModelAndView("/user/confirmation");
        return view;
    }

    @RequestMapping("/myPurchaseList")
    public ModelAndView myPurchase() {
        log.debug("myPurchase");

        ModelAndView view = new ModelAndView("/user/myPurchase");
        return view;
    }

    @RequestMapping("/myPurchaseDetail")
    public ModelAndView myPurchaseDetail() {
        log.debug("myPurchaseDetail");

        ModelAndView view = new ModelAndView("/user/myPurchaseDetail");
        return view;
    }

    @RequestMapping("/purchase/popupStatus")
    public ModelAndView popupStatus() {
        log.debug("popupStatus");

        ModelAndView view = new ModelAndView("/popup/popupStatus");
        return view;
    }

}
