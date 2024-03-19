package com.hackathon.hackathon.model;

public class Offer {
    private String itemName;
    private double amount;
    private Bidder bidder;

    public Offer(String itemName, double amount, Bidder bidder) {
        this.itemName = itemName;
        this.amount = amount;
        this.setBidder(bidder);
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

	public Bidder getBidder() {
		return bidder;
	}

	public void setBidder(Bidder bidder) {
		this.bidder = bidder;
	}

}
