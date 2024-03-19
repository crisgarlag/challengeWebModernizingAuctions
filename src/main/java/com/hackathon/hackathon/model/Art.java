package com.hackathon.hackathon.model;

/**
 * Para el desarrollo de la prueba:
 * - Se debe incluir la clase Art como hija de la clase Item
 * - Se debe incluir la implementación del constructor public Art(String name, double initialPrice, Bidder currentBidder, String type, String artist, int yearCreated)
 * - Se deben incluir los métodos getter y setter de la clase Art
 */


public class Art extends Item {
    private String artist;
    private int yearCreated;

    
    public Art(String name, double initialPrice, Bidder currentBidder, String type, String artist, int yearCreated) {
    	//Llama al constructor de la clase Padre Item
    	super(name, initialPrice, currentBidder, type);
    	this.artist = artist;
    	this.yearCreated = yearCreated;
    }

    /**
     * 
     * @return devuelve la propiedad artist
     */
	public String getArtist() {
		return artist;
	}

	/**
	 * modifica la propiedad artist con el valor pasado como parametro
	 * @param artist
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	/**
     * 
     * @return devuelve la propiedad yearCreated
     */
	public int getYearCreated() {
		return yearCreated;
	}

	/**
	 * modifica la propiedad yearCreated con el valor pasado como parametro
	 * @param artist
	 */
	public void setYearCreated(int yearCreated) {
		this.yearCreated = yearCreated;
	}
    
    
    
}
