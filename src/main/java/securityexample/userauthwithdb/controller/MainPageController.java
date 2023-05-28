package securityexample.userauthwithdb.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import securityexample.userauthwithdb.service.DocumentService;


@Controller
public class MainPageController {
    @Autowired
    private DocumentService documentService;

    @GetMapping("/home")
    public String homePage(Authentication a, Model model) {
        model.addAttribute("username", a.getName());
        model.addAttribute("documents", documentService.getDocuments());
        return "home.html";
    }
}
