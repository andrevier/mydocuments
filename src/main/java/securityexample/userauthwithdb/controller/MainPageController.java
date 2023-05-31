package securityexample.userauthwithdb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(MainPageController.class);
    @GetMapping({"/home", "/"})
    public String homePage(Authentication a, Model model) {
        model.addAttribute("username", a.getName());
        model.addAttribute("documents", documentService.getDocuments());
        return "home";
    }

    @GetMapping("/edit/{documentId}")
    public String editDocumentById(
        Authentication a, 
        Model model, 
        @PathVariable Long documentId) {
        // Get the document with the id documentId and set the content in 
        // model attributes.
        DocumentDto docDto = this.documentService.getDocumentById(documentId);
        
        logger.info("---- edit document ----");
        logger.info("document id: " + docDto.getDocumentId());
        logger.info("document title: " + docDto.getTitle());
        logger.info("content : " + docDto.getContent());

        model.addAttribute("document", docDto);
        model.addAttribute("username", a.getName());
        model.addAttribute("updatedDocument", docDto);
        return "edit";
    }

    @PostMapping(value = "/edit/{documentId}", params = "save")
    public String updateDocument(
        Authentication auth, 
        Model model, 
        @ModelAttribute DocumentRequest updatedDocument,
        @PathVariable Long documentId){
        // Save the document content edited by the user.
        updatedDocument.setDocumentId(documentId);
        updatedDocument.setUsername(auth.getName());
        
        logger.info("---- update document ----");
        logger.info("document id: " + updatedDocument.getDocumentId());
        logger.info("document title: " + updatedDocument.getTitle());
        logger.info("content : " + updatedDocument.getContent());
        
        this.documentService.updateDocument(updatedDocument);

        // model.addAttribute("username", auth.getName());
        // model.addAttribute("documents", documentService.getDocuments());
        return "redirect:/home";
    }

    @PostMapping(value = "/edit/{documentId}", params = "cancel")
    public String returnToHomeWithoutSave() {
        return "redirect:/home";
    }

    @GetMapping("/create-document")
    public String createDocument(Authentication auth, Model model) {
        // Create an empty document for the user.
        this.documentService.createDocument(auth.getName(), auth);
        return "redirect:/home";
    }
}
