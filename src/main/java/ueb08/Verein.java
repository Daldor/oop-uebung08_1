package ueb08;

import javax.persistence.Column;

 public class Verein {

//	public int compareTo(Verein v){
//		if(this.getId() < v.getId()){
//			return 1;
//		}
//		else if(this.getId() == v.getId()){
//			return 0;
//		}
//		else {
//			return -1;
//		}
//	}

	public Verein(int V_ID, String name, int liga) {
		this.id = V_ID;
		this.name = name;
		this.liga = liga;
	}

	@Column(name="V_ID")
	private int id;

	@Column(name="Name")
	private String name;

	@Column(name="Liga")
	private int liga;

	public int getId() { return id; }
	public String getName() { return name; }
	public int getLiga() { return liga; }

	public String toString() {
		return id + " " + name + " (" + liga + ")";
	}

}
