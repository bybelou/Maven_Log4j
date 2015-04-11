package paczka;

import java.util.Date;
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
		pracownicy[1] = new Pracownik("Kamil Kuæ", 8500, 2013, 04, 15);
		pracownicy[2] = new Pracownik("Agnieszka Tomczuk", 12500, 2014, 01, 19);
		
		for (int i=0; i<pracownicy.length; i++)
			pracownicy[i].podniesPensje(5);
		
		for (int i=0; i<pracownicy.length; i++)
			//obiekt ma metode toString i sobie pobiera - jest przeciazona i domyslnie bierze
			System.out.println(pracownicy[i]);

		//inne poprawne pobrania wartoœci
		//pobierz obiekty typu Date
		System.out.println("---------------------------------------------------------------");
		for (int i=0; i<pracownicy.length; i++)
			System.out.println(pracownicy[i].pobierzDzienZatrudnieniaInDate().toString());
		//pobierz obiekty typu GregorianCalendar
		System.out.println("---------------------------------------------------------------");
		for (int i=0; i<pracownicy.length; i++)
			System.out.println(pracownicy[i].pobierzDzienZatrudnieniaInGregorianCalendar().getTime().toString());
		
	}
}


class Pracownik{
	private final int id;//identyfikator pracownika
	private static int nastepnyId = 1;//nastepny identyfikator, startowo=1 pole dostepne z poziomu kazdego obiektu
	private final String nazwa;//nazwa pracownika
	private double pensja;//pensja pracownika
	private final GregorianCalendar dataZatrudnienia;//data zatrudnienia pracownika, GregorianCalendar
	private final Date dataZatrudnieniaTypuData;//data zatrudnienia pracownika, Date
	
	private static Logger logger = Logger.getLogger(Pracownik.class);
	
	Pracownik (String nazwa, double pensja, int rok, int miesiac, int dzien){
		this.nazwa = nazwa;
		this.pensja = pensja;
		//this.id = Pracownik.nastepnyId;
		this.id = Pracownik.pobierzNastepnyId();
		Pracownik.nastepnyId++;
		
		this.dataZatrudnienia = new GregorianCalendar(rok, miesiac - 1, dzien);
		//tylko kopia zatrudnienia, ale o typie DATE
		dataZatrudnieniaTypuData = this.dataZatrudnienia.getTime();
		
		Pracownik.logger.info("NOWA OSOBA - Id: " + this.id 
							+ "Nazwa: "+this.nazwa
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
	public GregorianCalendar pobierzDzienZatrudnieniaInGregorianCalendar(){
		Pracownik.logger.info("POBIERZ DZIEN ZATRUDNIENIA in GregorianCalendar - Data zatrudnienia: " + this.dataZatrudnienia.getTime().toString());
		return (GregorianCalendar)this.dataZatrudnienia.clone();
	}
	public Date pobierzDzienZatrudnieniaInDate(){
		Pracownik.logger.info("POBIERZ DZIEN ZATRUDNIENIA in Date - Data zatrudnienia: " + this.dataZatrudnieniaTypuData.toString());
		return this.dataZatrudnieniaTypuData;
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
	
	public int pobierzId(){
		return this.id;
	}
	public static int pobierzNastepnyId(){
		return Pracownik.nastepnyId;
	}
	
	@Override
	public String toString(){
		return	"Osoba: " + pobierzNazwe()
				+ "\n\tId: " + pobierzId()
				+ "\n\tData zatrudnienia: " + pobierzDzienZatrudnienia()
				+ "\n\tPensja: " + pobierzPensje();
	}
}