package ru.mtuci.policy.controller;


import org.springframework.web.bind.annotation.*;
import ru.mtuci.policy.model.Organisation;
import ru.mtuci.policy.service.OrganisationService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/organisations")
public class OrganisationController {

    private final OrganisationService organisationService;

    public OrganisationController(OrganisationService organisationService) {
        this.organisationService = organisationService;
    }

    @GetMapping("{id}")
    public Organisation get(@PathVariable("id") Long id) {
        return organisationService.get(id);
    }

    @GetMapping
    public List<Organisation> getAll() {
        return organisationService.getAll();
    }

    @PostMapping
    public void save(@RequestBody Organisation organisation) {
        organisationService.save(organisation);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        organisationService.delete(id);
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") Long id, @RequestBody Organisation organisation) {
        organisationService.update(id, organisation);
    }
}
