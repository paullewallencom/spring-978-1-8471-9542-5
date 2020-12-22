package com.webflow2book;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Simple Spring MVC Controller to check if method invocation
 * with Spring Security works as expected.
 * @author Sven
 */
@Controller
public class AdminController {

    @Autowired
    @Qualifier(value = "adminService")
    private AdminService adminService;

    @RequestMapping("/admin.htm")
    public String getStrings(ModelMap model) {
        List<String> resultList = new ArrayList<String>();

        // Try to get String-objects from a secured method
        resultList.addAll(this.adminService.getSecretStrings());

        // Get String-objects from a method that
        // is not secured
        resultList.addAll(this.adminService.getPublicStrings());
        model.addAttribute("resultList", resultList);

        return "adminInterface";
    }
}
