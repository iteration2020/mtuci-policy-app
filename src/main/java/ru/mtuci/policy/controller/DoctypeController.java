package ru.mtuci.policy.controller;

import org.springframework.web.bind.annotation.*;
import ru.mtuci.policy.dto.DoctypeDto;
import ru.mtuci.policy.model.Doctype;
import ru.mtuci.policy.service.DoctypeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctype")
public class DoctypeController {

    private final DoctypeService doctypeService;

    public DoctypeController(DoctypeService doctypeService) {
        this.doctypeService = doctypeService;
    }

    @GetMapping("{id}")
    public Doctype get(@PathVariable("id") Long id) {
        return doctypeService.get(id);
    }

    @GetMapping
    public List<Doctype> getAll() {
        return doctypeService.getAll();
    }

    @PostMapping
    public void save(@RequestBody DoctypeDto doctypeDto) {
        doctypeService.save(doctypeDto.toDoctype());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        doctypeService.delete(id);
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") Long id, @RequestBody DoctypeDto doctypeDto) {
        doctypeService.update(id, doctypeDto.toDoctype());

    }
}
