package com.diplom2.controllers;

import com.diplom2.models.Links;
import com.diplom2.repo.LinksRepository;
import com.diplom2.services.LinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private LinksService linksService;

    private String help;
    private String color;
    private String lol;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<Links> links = linksService.findAll();
        model.addAttribute("links", links);
        model.addAttribute("help", help);
        model.addAttribute("color", color);
        help = "";
        return "home";
    }

    @PostMapping("/")
    public String linksAdd(@RequestParam String link_long, @RequestParam String link_short, Model model) {
        Links check = linksService.getByShortLink(link_short);
        if (link_long.length() > 0) {
            if (link_short.length() > 0) {
                if (check == null) {
                    Links link = new Links(link_long, link_short);
                    linksService.saveLink(link);
                    help = "Готово";
                    color = "text-success";
                } else {
                    help = "Добавить такое сокращение невозможно, используйте другое!";
                    color = "text-danger";
                }
            } else {
                help = "Заполните все поля";
                color = "text-danger";
            }
        } else {
            help = "Заполните все поля";
            color = "text-danger";
        }
        return "redirect:/";
    }

    @GetMapping("/link-{id}")
    public String linkInfo(@PathVariable(value = "id") long linkId, Model model) {
        Optional<Links> links = linksService.findById(linkId);

        ArrayList<Links> result = new ArrayList<>();
        links.ifPresent(result::add);

        Links link = result.get(0);
        model.addAttribute("link", link);
        model.addAttribute("help", help);
        model.addAttribute("color", color);
        help = "";
        lol = link.getShort_link();
        return "link-info";
    }

    @PostMapping("/link-{id}")
    public String linkUpdate(@PathVariable(value = "id") long linkId, @RequestParam String link_long, @RequestParam String link_short, Model model) {
        Links check = linksService.getByShortLink(link_short);
        if (link_long.length() > 0) {
            if (link_short.length() > 0) {
                if (check == null || lol.equals(link_short)) {
                    Optional<Links> links = linksService.findById(linkId);

                    ArrayList<Links> result = new ArrayList<>();
                    links.ifPresent(result::add);

                    Links link = result.get(0);
                    link.setLong_link(link_long);
                    link.setShort_link(link_short);
                    linksService.saveLink(link);
                    help = "Готово";
                    color = "text-success";
                } else {
                    help = "Добавить такое сокращение невозможно, используйте другое!";
                    color = "text-danger";
                }
            } else {
                help = "Заполните все поля";
                color = "text-danger";
            }
        } else {
            help = "Заполните все поля";
            color = "text-danger";
        }
        return "redirect:/link-" + linkId;
    }

    @PostMapping("/link-{id}/delete")
    public String reviewsDelete(@PathVariable(value = "id") long linkId, Model model) {
        Optional<Links> links = linksService.findById(linkId);

        ArrayList<Links> result = new ArrayList<>();
        links.ifPresent(result::add);

        Links link = result.get(0);
        help = "Успешно удалено";
        color = "text-success";
        linksService.delete(link);
        return "redirect:/";
    }

}