package com.hms.service;

import com.hms.model.Block;
import com.hms.repository.BlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlockService {

    @Autowired
    private BlockRepository blockRepository;

    public Block addBlock(Block block) {
        return blockRepository.save(block);
    }

    public List<Block> getAllBlocks() {
        return blockRepository.findAll();
    }

    public Optional<Block> getBlockByCode(Integer blockCode) {
        return blockRepository.findById(blockCode);
    }

    public void deleteBlock(Integer blockCode) {
        blockRepository.deleteById(blockCode);
    }
}