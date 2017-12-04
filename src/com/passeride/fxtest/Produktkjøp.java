package com.passeride.fxtest;

public class Produktkjøp { 
	//protected int antall; 
	//protected Produkt p;
	
	protected Produkt[] produkter;
	protected int[] antall;
	
	public Produktkjøp(Produkt prod, int ant) {
		produkter = new Produkt[0];
		antall = new int[0];
		
		addProdukt(prod, ant);
	}
	
	public void addProdukt(Produkt nyttprod, int ant) {
		// Sjekker om det er mer enn 10 produkter og stopper
		if(produkter.length >= 10)
			return;
		
		// Her er det mindre enn 10 prdukter
		
		// Lager en ny array som er 1 større enn produkter array
		Produkt[] tmpArr = new Produkt[produkter.length + 1];
		
		// Det er en funksjon som gjør dette men jeg husker den ikke.... BOOTSTRAPMODE!!!!! ERMEGHERD
		for(int i = 0; i < produkter.length; i++) {
			tmpArr[i] = produkter[i]; // Populates tmp arry with old values
		}
		
		// Sett inn new value
		tmpArr[produkter.length] = nyttprod;
		
		// Setter tmp arr som nye Produkter 
		produkter = tmpArr;
		
		// Gjør det samme med antall
		// Lager en ny array som er 1 større enn produkter array
		int[] tmpArr2 = new int[antall.length + 1];
		
		// Det er en funksjon som gjør dette men jeg husker den ikke.... BOOTSTRAPMODE!!!!! ERMEGHERD
		for(int i = 0; i < antall.length; i++) {
			tmpArr2[i] = antall[i]; // Populates tmp arry with old values
		}
		
		// Sett inn new value
		tmpArr2[antall.length] = ant;
		
		// Setter tmparr som nye antall 
		antall = tmpArr2;
	}
	
	public void printAll() {
		for(int i = 0; i < produkter.length; i++) {
			System.out.println(produkter[i].getName() + " : " + antall[i]);
		}
	}
	
	public static void main(String[] args) {
		Produkt p1 = new Produkt("Snuff", "puff", 100.4);
		Produktkjøp p = new Produktkjøp(p1, 23);
		p.printAll();
		System.out.println("");
		Produkt p2 = new Produkt("Snerk", "Blerk", 28.0);
		p.addProdukt(p2, 10);
		p.printAll();
	}
	
}