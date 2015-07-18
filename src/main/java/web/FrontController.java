package web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import util.PostFixConverter;

/**
 * Created by yoon on 15. 7. 8..
 */
@Controller
public class FrontController {

    Logger log = LoggerFactory.getLogger(FrontController.class);

    @RequestMapping("/**")
    public @ResponseBody String httpRequest(String requestCalculation) {
        log.info("Request Calculation = {}", requestCalculation);


        PostFixConverter converter = new PostFixConverter(requestCalculation);

        return "Index Page";
    }
}