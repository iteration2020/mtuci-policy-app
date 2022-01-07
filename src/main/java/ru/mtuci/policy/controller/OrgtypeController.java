package ru.mtuci.policy.controller;

import org.springframework.web.bind.annotation.*;
import ru.mtuci.policy.dao.OrgtypeRepository;
import ru.mtuci.policy.model.Orgtype;
import ru.mtuci.policy.service.OrgtypeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orgtype")
public class OrgtypeController {

    private final OrgtypeService orgtypeService;

    public OrgtypeController(OrgtypeService orgtypeService) {
        this.orgtypeService = orgtypeService;
    }

    @GetMapping("{id}")
    public Orgtype get(@PathVariable("id") Long id) {
        return orgtypeService.get(id);
    }

    @GetMapping
    public List<Orgtype> getAll() {
        return orgtypeService.getAll();
    }

    @PostMapping
    public void save(@RequestBody Orgtype orgtype) {
        orgtypeService.save(orgtype);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        orgtypeService.delete(id);
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") Long id, @RequestBody Orgtype orgtype) {
        orgtypeService.update(id, orgtype);
    }
}
