package com.shoestorebackend.controller;

import com.shoestorebackend.dto.CreateSanPhamRequest;
import com.shoestorebackend.dto.SanPhamDTO;
import com.shoestorebackend.entity.SanPham;
import com.shoestorebackend.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/san-pham")
@CrossOrigin(origins = "*")
public class SanPhamController {

    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping
    public ResponseEntity<List<SanPhamDTO>> getAllSanPham() {
        return ResponseEntity.ok(sanPhamService.getAllSanPhamActive());
    }


    @GetMapping("/admin")
    public ResponseEntity<List<SanPham>> getAllForAdmin() {
        return ResponseEntity.ok(sanPhamService.getAllSanPham());
    }


    @PostMapping("/admin")
    public ResponseEntity<SanPham> create(@RequestBody CreateSanPhamRequest request) {
        return ResponseEntity.ok(sanPhamService.createSanPham(request));
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<SanPham> update(@PathVariable Integer id, @RequestBody CreateSanPhamRequest request) {
        return ResponseEntity.ok(sanPhamService.updateSanPham(id, request));
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        sanPhamService.deleteSanPham(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<SanPhamDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(sanPhamService.getSanPhamById(id));
    }

}


