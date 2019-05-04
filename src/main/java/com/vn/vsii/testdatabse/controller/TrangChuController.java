package com.vn.vsii.testdatabse.controller;

import com.vn.vsii.testdatabse.model.Contact;
import com.vn.vsii.testdatabse.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.context.annotation.Bean;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class TrangChuController {
    @Autowired
    ContactService contactService;
    @GetMapping
    public String View(Model model){
        model.addAttribute("contacts",contactService.findAll());
        return "index";
    }
   @RequestMapping("/addlist")
    public String add(Model model){
        model.addAttribute("contact", new Contact());
        return  "add";
   }
   @PostMapping("/contact/save")
    public String save(@Valid Contact contact, BindingResult result, RedirectAttributes redirect){
        if(result.hasErrors()){
            return "add" ;
        }
        contactService.save(contact);
        redirect.addFlashAttribute("successMessage","Save contact success");
        return "redirect:/";
    }
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, RedirectAttributes redirect){
        contactService.delete(id);
        redirect.addFlashAttribute("successMessage", "Deleted");
        return "redirect:/";
    }
    @GetMapping("/{id}/view")
    public String views(@PathVariable int id, Model model){
        model.addAttribute("customer", contactService.findById(id));
        return "view";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model){
        model.addAttribute("customer", contactService.findById(id));
        return "edit";
    }

    @PostMapping("customer/update")
    public String update(Contact contact, RedirectAttributes redirect){
        contactService.save(contact);
        redirect.addFlashAttribute("successMesage", "Modified");
        return"redirect:/";
    }
    @GetMapping("/contact/search")
    public String search(@RequestParam("term") String term, Model model) {
        if (StringUtils.isEmpty(term)) {
            return "redirect:/";
        }

        model.addAttribute("contacts", contactService.search(term));
        return "index";
    }


}
