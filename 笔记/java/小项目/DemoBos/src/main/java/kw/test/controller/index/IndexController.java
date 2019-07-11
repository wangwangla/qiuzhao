package kw.test.controller.index;

import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;

/**
 * auther   kangwang
 */
@RequestMapping("/")
public class IndexController extends JbootController {
    public void index(){
        render("index.html");
    }
}
