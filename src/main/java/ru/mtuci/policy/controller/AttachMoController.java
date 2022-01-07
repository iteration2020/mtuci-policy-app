package ru.mtuci.policy.controller;

import org.springframework.web.bind.annotation.*;
import ru.mtuci.policy.model.AttachMo;
import ru.mtuci.policy.service.AttachMoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/attachments")
public class AttachMoController {

    private final AttachMoService attachMoService;

    public AttachMoController(AttachMoService attachMoService) {
        this.attachMoService = attachMoService;
    }

    @GetMapping("{id}")
    public AttachMo get(@PathVariable("id") Long id) {
        return attachMoService.get(id);
    }

    @GetMapping
    public List<AttachMo> getAll() {
        return attachMoService.getAll();
    }

    @PostMapping
    public void save(@RequestBody AttachMo attachMo) {
        attachMoService.save(attachMo);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        attachMoService.delete(id);
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") Long id, @RequestBody AttachMo attachMo) {
        attachMoService.update(id, attachMo);
    }

}
