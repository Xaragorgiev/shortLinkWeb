package com.diplom2.services;

import com.diplom2.models.Links;
import com.diplom2.repo.LinksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LinksService {

    @Autowired
    private LinksRepository linksRepository;

    public Links getByShortLink(String link) {
        return linksRepository.findByShort_link(link);
    }

    public List<Links> findAll() {
        return linksRepository.findAll();
    }

    public Links saveLink(Links links) {
        Links savedLink = linksRepository.saveAndFlush(links);

        return savedLink;
    }

    public void delete(Links links) {
        linksRepository.delete(links);
    }

    public Optional<Links> findById(Long id) {
        return linksRepository.findById(id);
    }

}