package paczka;

import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

public class Maven_Log4j {
	private static Logger piotrusPan = Logger.getLogger(Maven_Log4j.class);
	
	public static void main(String[] args) {
		piotrusPan.info("przykladowe-info");
		piotrusPan.warn("przykladowe-warn");
		piotrusPan.error("przykladowe-error");
		
		Pracownik[] pracownicy = new Pracownik[3];
		pracownicy[0] = new Pracownik("Piotr Kulma", 6500, 2015, 05, 01);
		pracownicy[1] = new Pracownik("Kamil Kuæ", 8500, 2013, 04, 01);
		pracownicy[2] = new Pracownik("Agnieszka Tomczuk", 12500, 2014, 01, 01);
		
		for (int i=0; i<pracownicy.length; i++)
			pracownicy[i].podniesPensje(5);
		
		for (int i=0; i<pracownicy.length; i++)
			//obiekt ma metode toString i sobie pobiera - jest przeciazona i domyslnie bierze
			System.out.println(pracownicy[i]);
	}
}



class Pracownik{
	String nazwa;
	double pensja;
	int rok;
	int miesiac;
	int dzien;
	
	GregorianCalendar dataZatrudnienia;
	
	private static Logger logger = Logger.getLogger(Pracownik.class);
	
	Pracownik (String nazwa, double pensja, int rok, int miesiac,  int dzien){
		this.nazwa = nazwa;
		this.pensja = pensja;
		this.dataZatrudnienia = new GregorianCalendar(this.rok, this.miesiac, this.dzien);
		Pracownik.logger.info("NOWA OSOBA - Nazwa: "+this.nazwa
							+ " Pensja: " + this.pensja
							+ ", data zatrudnienia: " + this.dataZatrudnienia.getTime().toString());
	}
	
	public void podniesPensje(double procenty){
		this.pensja += this.pensja * (procenty/100);
		Pracownik.logger.info("PODNIES PENSJE - Nowa pensja: " + this.pensja
							+ " dla: " + this.nazwa);
	}
	
	public String pobierzDzienZatrudnienia(){
		Pracownik.logger.info("POBIERZ DZIEN ZATRUDNIENIA - Data zatrudnienia: " + this.dataZatrudnienia.getTime().toString());
		return this.dataZatrudnienia.getTime().toString();
	}
	
	public double pobierzPensje(){
		Pracownik.logger.info("POBIERZ PENSJE - Pensja: " + this.pensja);
		if (this.pensja>10000)
			Pracownik.logger.warn("OSTRZERZNIE, osoba zarabia za duzo");
		return this.pensja;
	}
	
	public String pobierzNazwe(){
		Pracownik.logger.info("POBIERZ NAZWE OSOBY - Osoba: " + this.nazwa);
		return this.nazwa;
	}
	
	@Override
	public String toString(){
		return	"Osoba: " + pobierzNazwe()
				+ "\n\tData zatrudnienia: " + pobierzDzienZatrudnienia()
				+ "\n\tPensja: " + pobierzPensje();
	}
}