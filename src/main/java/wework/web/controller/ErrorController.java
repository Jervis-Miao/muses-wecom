package wework.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 出错页面控制器
 *
 * @author jervis
 * @date 2021-11-30
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

    @GetMapping(value = "/404")
    public String error404(Model model, HttpServletRequest request) {
        return "400";
    }

    @GetMapping(value = "/500")
    public String error500(Model model, HttpServletRequest request) {
        return "500";
    }
}
