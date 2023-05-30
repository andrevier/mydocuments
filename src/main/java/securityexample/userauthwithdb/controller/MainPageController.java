package securityexample.userauthwithdb.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import securityexample.userauthwithdb.dto.DocumentDto;
import securityexample.userauthwithdb.dto.DocumentRequest;
import securityexample.userauthwithdb.service.DocumentService;


@Controller
public class MainPageController {
    @Autowired
    private DocumentService documentService;

    @GetMapping("/home")
    public void homePage(Authentication a, Model model) {
        model.addAttribute("username", a.getName());
        model.addAttribute("documents", documentService.getDocuments());
    }

    @GetMapping("/edit")
    public String editDocument(Authentication a, Model model) {
        model.addAttribute("username", a.getName());
        return "edit";
    }

    @GetMapping("/edit/{documentId}")
    public String editDocumentById(
        Authentication a, 
        Model model, 
        @PathVariable Long documentId) {
        // Get the document with the id documentId and set the content in 
        // model attributes.
        DocumentDto docDto = this.documentService.getDocumentById(documentId);
        model.addAttribute("document", docDto);
        model.addAttribute("username", a.getName());

        return "edit";
    }

    @PostMapping("/update-document/{documentId}")
    public String updateDocument(
        Authentication auth, 
        Model model, 
        @ModelAttribute DocumentRequest document,
        @PathVariable Long documentId){
        // Save the document content edited by the user.
        document.setDocumentId(documentId);
        this.documentService.updateDocument(document);

        model.addAttribute("username", auth.getName());
        model.addAttribute("documents", documentService.getDocuments());
        return "home";
    }
}
