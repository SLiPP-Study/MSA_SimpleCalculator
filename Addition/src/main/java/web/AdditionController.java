package web;

import dto.Addition;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by yoon on 15. 7. 20..
 */
@Controller
public class AdditionController {

    @RequestMapping("/")
    public @ResponseBody int add(@RequestBody Addition addition, HttpServletResponse response) {
        return addition.getOperationResult();
    }
}
