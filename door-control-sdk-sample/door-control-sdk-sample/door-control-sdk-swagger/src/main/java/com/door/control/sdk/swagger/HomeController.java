package com.door.control.sdk.swagger;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 屠科钻
 * @since 2016年6月7日
 */
@Controller
@Api(hidden = true)
public class HomeController {
    @RequestMapping("/")
    public String home() {
        return "redirect:swagger-ui.html";
    }
}
