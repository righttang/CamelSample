package org.tom.camel;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tangh2 on 26/09/2014.
 */

@RestController
public class SampleRESTController {
    @RequestMapping("/test")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name) {
        return "hello " + name;
    }
}
