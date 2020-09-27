package com.example.demo.model;

import java.util.List;

public class FinalOutput {

	private Item item;
	
	private List<Change> change;

	
	public FinalOutput() {
	}

	public FinalOutput(Item item, List<Change> change) {
		this.item = item;
		this.change = change;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<Change> getChange() {
		return change;
	}

	public void setChange(List<Change> change) {
		this.change = change;
	}

}
