package web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yoon on 15. 7. 8..
 */
@Controller
public class IndexController {

    Logger log = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/")
    public @ResponseBody String index() {
        log.info("Request / Path");
        return "Index Page";
    }
}