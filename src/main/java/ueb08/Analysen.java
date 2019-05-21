package ueb08;

import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.util.*;




class Analysen  {


	/**
	 * Wie viele Tore fallen durchschnittlich in jedem Spiel?
	 */
	static double torstatistikenToreProSpiel() throws IOException {

		Bundesliga bl = Bundesliga.loadFromResource();
		int tore = 0;

		for (Spiel s: bl.spiele) {
			tore += s.getToreHeim() + s.getToreGast();
		}

		return (double) tore / bl.spiele.size();
	}

	/**
	 * Wie viele Tore fallen durchschnittlich in einem Spiel der 1. Liga?
	 */
	static double torstatustikenToreProErstligaspiel() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();

		int tore = 0;
		int count = 0;


		for(Spiel s: bl.spiele){
			for (Verein v: bl.vereine.values()) {
				if(v.getLiga() == 1 && v.getId() == s.getHeim()) {
					tore += s.getToreHeim() + s.getToreGast();
					count++;
				}
			}
		}
		return (double) tore/count;
	}

	/**
	 * Wie viele Tore fallen durchschnittlich an einem Spieltag der 2. Liga?
	 */
	static double torstatistikenToreProSpieltag2teLiga() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();

		double tore = 0.0;


		for(Spiel s: bl.spiele){
			for (Verein v: bl.vereine.values()) {
				if(v.getLiga() == 2 && v.getId() == s.getHeim()) {
					tore += s.getToreHeim() + s.getToreGast();
				}
			}
		}

		double last = 0.0;

		for (Spiel s: bl.spiele) {
			for (Verein v: bl.vereine.values()){
				if(v.getLiga() == 2){
					last = s.getSpieltag();
				}
			}
		}

		System.out.println(last);

		return (tore/last);

	}

	/**
	 * Stimmt es, dass in den Nachmittagsspielen (15:30:00) im Schnitt mehr Tore fallen, wie in den Abendspielen?
	 */
	static boolean torstatistikenToreNachmittagsAbends() throws IOException {

		return false;
	}

	/**
	 * Stimmt es, dass Vereine der 3. Liga zuhause im Schnitt mehr Tore schießen als auswärts?
	 */
	static boolean torstatistikenToreDaheim() throws IOException {

		return false;
	}

	/**
	 * Wie viele Tore hat der FC Bayern München (Verein 1) erzielt?
	 */
	static int vereineToreVerein1erzielt() throws IOException {

		return 0;
	}

	/**
	 * Wie viele Tore hat der FC Schalke 04 (Verein 2) erhalten?
	 */
	static int vereineToreVerein2erhalten() throws IOException {

		return 0;
	}

	/**
	 * Wie viele Punkte hat der 1. FC Nürnberg (Verein 20)?
	 * Ein Sieg zählt 3 Punkte, ein Unentschieden 1, eine Niederlage 0 Punkte.
	 */
	static int vereineToreVerein20punkte() throws IOException {

		return 0;
	}

	/**
	 * Was ist das Torverhältnis des VfL Bochum (Verein 26)?
	 */
	static int vereineTorverhaeltnis26() throws IOException {

		return 0;
	}

	/**
	 * Hilfsklasse.
	 */
	static class VereinTore {
		String verein;
		int tore;

		public VereinTore(String v, int t) {
			this.verein = v;
			this.tore = t;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (!(o instanceof VereinTore))
				return false;
			VereinTore that = (VereinTore) o;
			return tore == that.tore && verein.equals(that.verein);
		}
	}


	/**
	 * Welche drei Vereine haben die meisten Tore zuhause geschossen, und wie viele?
	 */
	static List<VereinTore> vereineMeisteToreZuhause() throws IOException {

		return null;
	}

	/**
	 * Welcher Verein hat die wenigsten Tore auswärts geschossen, und wie viele?
	 */
	static VereinTore vereineWenigsteToreAuswaerts() throws IOException {

		return null;
	}

}