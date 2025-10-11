package com.hms.controller;

import com.hms.model.Block;
import com.hms.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blocks")
public class BlockController {

    @Autowired
    private BlockService blockService;

    @PostMapping
    public ResponseEntity<Block> addBlock(@RequestBody Block block) {
        return ResponseEntity.ok(blockService.addBlock(block));
    }

    @GetMapping
    public ResponseEntity<List<Block>> getAllBlocks() {
        return ResponseEntity.ok(blockService.getAllBlocks());
    }

    @GetMapping("/{blockCode}")
    public ResponseEntity<Block> getBlockByCode(@PathVariable Integer blockCode) {
        return blockService.getBlockByCode(blockCode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{blockCode}")
    public ResponseEntity<Void> deleteBlock(@PathVariable Integer blockCode) {
        blockService.deleteBlock(blockCode);
        return ResponseEntity.noContent().build();
    }
}
