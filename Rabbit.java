package Project;

public class Rabbit {
	
	private int directie_linie;
	private int directie_coloana;
	private int valoare_cos = 0;
	private String directie;
	private String next_move;
	private boolean is_in_parcel = true;
	
	public Rabbit(int directie_linie , int directie_coloana) {
		this.directie_linie = directie_linie;
		this.directie_coloana = directie_coloana;
		}
	
	public boolean is_in_game() {
		return this.is_in_parcel;
	}
	
	public void setIs_in_game(boolean is_in_parcel) {
		this.is_in_parcel = is_in_parcel;
	}
	
	public void changeValoare_cos(int value) {
		this.valoare_cos += value;
	}
	
	public int getValoare_cos() {
		return this.valoare_cos;
	}
	
	public void move_Forward() {
		switch(directie) {
		case "nord" :
			this.directie_linie -= 1;
			break;
		case "sud" :
			this.directie_linie += 1;
			break;
		case "est" :
			this.directie_coloana -= 1;
			break;
		case "vest" :
			this.directie_coloana += 1;
			break;
		}
	}
	
	public void change_Direction() {
		switch(directie) {
		case "nord" :
			this.directie_coloana += 1;
			this.directie = "vest";
			break;
		case "sud" :
			this.directie_coloana -= 1;
			this.directie = "est";
			break;
		case "est" :
			this.directie_linie -= 1;
			this.directie = "nord";
			break;
		case "vest" :
			this.directie_linie += 1;
			this.directie = "sud";
			break;
		}
	}
	
	public void setNext_move(String move) {
		this.next_move = move;
	}
	
	public String getNext_move() {
		return this.next_move;
	}
	
	public void setDirection(String directie) {
	    this.directie = directie;
	}
	
	public int getDirectie_linie() {
		return this.directie_linie;
	}
	
	public int getDirectie_coloana() {
		return this.directie_coloana;
	}
}
