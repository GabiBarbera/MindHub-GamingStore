package com.wellplayed.gaming.store.controllers;

import com.wellplayed.gaming.store.dtos.ComponentCreateDTO;
import com.wellplayed.gaming.store.dtos.ComponentDTO;
import com.wellplayed.gaming.store.models.Component;
import com.wellplayed.gaming.store.repositories.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ComponentController {
    @Autowired
    private ComponentRepository componentRepository;

    @GetMapping("/components")
    public List<ComponentDTO> getComponents() {
        return componentRepository.findAll().stream().map(ComponentDTO::new).collect(Collectors.toList());
    }

    @PostMapping("/components/create") //ADMIN ADMIN ADMIN
    public ResponseEntity<Object> createComponent(@RequestBody ComponentCreateDTO componentCreateDTO) {
        if(componentCreateDTO.getCategory().isBlank()){
            return new ResponseEntity<>("Category is required", HttpStatus.BAD_REQUEST);
        }
        if(componentCreateDTO.getBrand().isBlank()){
            return new ResponseEntity<>("Brand is required", HttpStatus.BAD_REQUEST);
        }
        if(componentCreateDTO.getName().isBlank()){
            return new ResponseEntity<>("Name is required", HttpStatus.BAD_REQUEST);
        }
        if(componentCreateDTO.getDescription().isBlank()){
            return new ResponseEntity<>("Description is required", HttpStatus.BAD_REQUEST);
        }

        if(componentCreateDTO.getSnapshot().isEmpty()){
            return new ResponseEntity<>("Snapshot is required", HttpStatus.BAD_REQUEST);
        }

        if(componentCreateDTO.getColors().isEmpty()){
            return new ResponseEntity<>("Colors are required", HttpStatus.BAD_REQUEST);
        }

        if(componentCreateDTO.getPhotos().isEmpty()){
            return new ResponseEntity<>("Photos are required", HttpStatus.BAD_REQUEST);
        }

        if(componentCreateDTO.getPrice() <= 0){
            return new ResponseEntity<>("Price must be greater than 0", HttpStatus.BAD_REQUEST);
        }
        if(componentCreateDTO.getStock() <= 0){
            return new ResponseEntity<>("Stock must be greater than 0", HttpStatus.BAD_REQUEST);
        }
        Component component = new Component(componentCreateDTO.getCategory(), componentCreateDTO.getBrand(), componentCreateDTO.getName(), componentCreateDTO.getDescription(), componentCreateDTO.getSnapshot(), componentCreateDTO.getColors(), componentCreateDTO.getPhotos(), componentCreateDTO.getPrice(), componentCreateDTO.getStock());
        componentRepository.save(component);
        return new ResponseEntity<>("Component created successfully",HttpStatus.CREATED);
    }
}