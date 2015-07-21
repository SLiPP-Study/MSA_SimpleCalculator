package web;

import dto.Addition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yoon on 15. 7. 20..
 */
@Controller
public class AdditionController {

    private Logger logger = LoggerFactory.getLogger(AdditionController.class);

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public @ResponseBody int add(@RequestBody Addition addition) {
        logger.info("Addition : {}", addition.toString());
        return addition.getOperationResult();
    }
}
