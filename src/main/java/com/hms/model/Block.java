package com.hms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "blocks")
public class Block {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer blockCode;
	
	private String blockFloor;
	
	public Block() {}
	
	public Block(String blockFloor) {
		this.setBlockFloor(blockFloor);
	}

	public String getBlockFloor() {
		return blockFloor;
	}

	public void setBlockFloor(String blockFloor) {
		this.blockFloor = blockFloor;
	}
	
	public Integer getBlockCode() {
        return blockCode;
    }

    public void setBlockCode(Integer blockCode) {
        this.blockCode = blockCode;
    }
		
}

