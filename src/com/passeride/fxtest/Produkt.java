package com.passeride.fxtest;

public class Produkt {
	private String navn; 
	private String merke; 
	private double pris; 
	
	public Produkt(String n, String m, double p) {
		this.navn = n;
		this.merke = m;
		this.pris = p;
	}
	
	public String getName() {
		return this.navn;
	}
	
	public String getMerke() {
		return this.merke; 
	}
	
	public double getPris() {
		return this.pris; 
	}
}