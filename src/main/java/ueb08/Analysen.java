package ueb08;

import org.apache.commons.lang3.tuple.Pair;
//import sun.text.normalizer.VersionInfo;

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

		int tore = 0;


		for(Spiel s: bl.spiele){
			for (Verein v: bl.vereine.values()) {
				if(v.getLiga() == 2 && v.getId() == s.getHeim()) {
					tore += s.getToreHeim() + s.getToreGast();
				}
			}
		}

		int last = 0;

		for (Spiel s: bl.spiele) {
			for (Verein v: bl.vereine.values()){
				if(v.getLiga() == 2){
					last = s.getSpieltag();
				}
			}
		}

		System.out.println(last);

		return (double) tore / (double) last;

	}

	/**
	 * Stimmt es, dass in den Nachmittagsspielen (15:30:00) im Schnitt mehr Tore fallen, wie in den Abendspielen?
	 */
	static boolean torstatistikenToreNachmittagsAbends() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();

		int toreMittags = 0;
		int counterMittags = 0;
		int toreAbends = 0;
		int counterAbends = 0;

		for (Spiel s:bl.spiele) {
			String [] zeitZahlen = s.getUhrzeit().split(":");
			int result = Integer.parseInt(zeitZahlen[0]);
			if( result > 15){
				toreAbends += s.getToreHeim() + s.getToreGast();
				counterAbends++;
			} else {
				toreMittags += s.getToreGast() + s.getToreGast();
				counterMittags++;
			}
		}

		if((double)toreAbends / (double)counterAbends > (double)toreMittags / (double)counterMittags){
			return true;
		}
		return false;
	}

	/**
	 * Stimmt es, dass Vereine der 3. Liga zuhause im Schnitt mehr Tore schießen als auswärts?
	 */
	static boolean torstatistikenToreDaheim() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();

		int heimTore = 0;
		int gastTore = 0;

		for(Verein v: bl.vereine.values()){
			for(Spiel s: bl.spiele){
				if(v.getLiga() == 3 && v.getId() == s.getHeim()){
					heimTore += s.getToreHeim();
					gastTore += s.getToreGast();
				}
			}
		}

		if(gastTore > heimTore){
			return true;
		}

		return false;
	}

	/**
	 * Wie viele Tore hat der FC Bayern München (Verein 1) erzielt?
	 */
	static int vereineToreVerein1erzielt() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();
		int toreVerein = 0;

		for(Spiel s: bl.spiele){
			if(s.getGast() == 1){
				toreVerein += s.getToreGast();
			}
			if(s.getHeim() == 1){
				toreVerein += s.getToreHeim();
			}
		}
		return toreVerein;
	}

	/**
	 * Wie viele Tore hat der FC Schalke 04 (Verein 2) erhalten?
	 */
	static int vereineToreVerein2erhalten() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();
		int erhalteneToreVerein = 0;

		for(Spiel s: bl.spiele){
			if(s.getGast() == 2){
				erhalteneToreVerein += s.getToreHeim();
			}
			if(s.getHeim() == 2){
				erhalteneToreVerein += s.getToreGast();
			}
		}
		return erhalteneToreVerein;
	}

	/**
	 * Wie viele Punkte hat der 1. FC Nürnberg (Verein 20)?
	 * Ein Sieg zählt 3 Punkte, ein Unentschieden 1, eine Niederlage 0 Punkte.
	 */
	static int vereineToreVerein20punkte() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();

		int punkte = 0;

		for(Verein v: bl.vereine.values()){
			for(Spiel s: bl.spiele){
				if(v.getId() == 20 && v.getId() == s.getHeim()){
					if(s.getToreGast() == s.getToreHeim()){
						punkte += 1;
					}
					if(s.getToreHeim() > s.getToreGast()){
						punkte += 3;
					}
				}
				if(v.getId() == 20 && v.getId() == s.getGast()){
					if(s.getToreGast() == s.getToreHeim()){
						punkte += 1;
					}
					if(s.getToreGast() > s.getToreHeim()){
						punkte += 3;
					}
				}
			}
		}

		return punkte;
	}

	/**
	 * Was ist das Torverhältnis des VfL Bochum (Verein 26)?
	 */
	static int vereineTorverhaeltnis26() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();

		int tore = 0;
		int gegentore = 0;

		for (Verein v : bl.vereine.values()) {
			for (Spiel s : bl.spiele) {
				if (v.getId() == 26 && v.getId() == s.getHeim()) {
					tore += s.getToreHeim();
					gegentore += s.getToreGast();
				}
				if (v.getId() == 26 && v.getId() == s.getGast()) {
					tore += s.getToreGast();
					gegentore += s.getToreHeim();
				}
			}
		}
		return tore - gegentore;
	}

			/**
			 * Hilfsklasse.
			 */
		static  class VereinTore implements Comparator<VereinTore> {
				String verein;
				int tore;

				public VereinTore(String v, int t) {
					this.verein = v;
					this.tore = t;
				}

				public String getVerein(){
					return this.verein;
				}

				public int getTore(){
					return this.tore;
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

				public int compare(VereinTore a, VereinTore b){
					if(a.getTore() > b.getTore()){
						return 1;
					}
					if(a.getTore() == b.getTore()){
						return 0;
					} else {
						return -1;
					}
				}

			}




			/**
			 * Welche drei Vereine haben die meisten Tore zuhause geschossen, und wie viele?
			 */
			static List<VereinTore> vereineMeisteToreZuhause () throws IOException {
				Bundesliga bl = Bundesliga.loadFromResource();

				List<VereinTore> toreDerVereine = new LinkedList<>();

				VereinTore [] arr = new VereinTore[bl.vereine.values().size()];

				for (int i = 0; i < arr.length; i++){
					for(Verein v: bl.vereine.values()){
						int tore = 0;
						for(Spiel s: bl.spiele){
							if(v.getId() == s.getHeim()){
								tore += s.getToreHeim();
							}
						}
						arr[i] = new VereinTore(v.getName(), tore);
					}
				}

				VereinTore eins = null;
				VereinTore zwei = null;
				VereinTore drei = null;


				for(int i = 0; i < arr.length -1 ; i++){
					if(arr[i].compare(arr[i],arr[i+1]) == 1){
						eins = arr[i];
					}
					else if(arr[i].compare(arr[i],arr[i+1]) == 1 && arr[i].getTore() < eins.getTore()){
						zwei = arr[i];
					}
					else if(arr[i].compare(arr[i],arr[i+1]) == 1 && arr[i].getTore() < zwei.getTore()){
						drei = arr[i];
					}
				}

				List<VereinTore> output = null;

				output.add(eins);
				output.add(zwei);
				output.add(drei);

				return output;
			}

			/**
			 * Welcher Verein hat die wenigsten Tore auswärts geschossen, und wie viele?
			 */
			static VereinTore vereineWenigsteToreAuswaerts () throws IOException {

				return null;
			}

		}