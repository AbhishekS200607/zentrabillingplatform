package com.zentra.api.controller;

import com.zentra.api.entity.Settings;
import com.zentra.api.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/settings")
@CrossOrigin(origins = "*")
public class SettingsController {
    
    @Autowired
    private SettingsRepository settingsRepository;
    
    @GetMapping
    public List<Settings> getAllSettings() {
        return settingsRepository.findAll();
    }
    
    @PostMapping
    public ResponseEntity<?> createSettings(@RequestBody Settings settings) {
        try {
            return ResponseEntity.ok(settingsRepository.save(settings));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSettings(@PathVariable Long id, @RequestBody Settings settings) {
        try {
            if (!settingsRepository.existsById(id)) {
                return ResponseEntity.notFound().build();
            }
            settings.setId(id);
            return ResponseEntity.ok(settingsRepository.save(settings));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}