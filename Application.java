package Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Application {
	public static void main(String[] args) throws FileNotFoundException {
		
		File file = new File("text");
		
		Scanner scanner = new Scanner(file);
		
		int L = Integer.parseInt(scanner.next());
        int P = Integer.parseInt(scanner.next());
		
        int [][]matrice = new int[L][L];
		for(int i = 0 ; i < L ; i++)
			for(int j = 0 ; j < L ; j++)
				matrice[i][j] = 0;
		
		for(int count = 0 ; count < P ; count++) {
			int indice_linie_ou = Integer.parseInt(scanner.next());
			int indice_coloana_ou = Integer.parseInt(scanner.next());
			int valoare_ou = Integer.parseInt(scanner.next());
			matrice[indice_linie_ou][indice_coloana_ou] = valoare_ou;
		}
		
		List<Rabbit> list = new ArrayList<>();
		int N = Integer.parseInt(scanner.next());
		for(int count = 0 ; count < N ; count++) {
			int indice_linie = Integer.parseInt(scanner.next());
			int indice_coloana = Integer.parseInt(scanner.next());
			
			Rabbit rabbit = new Rabbit(indice_linie , indice_coloana);
			if(indice_linie == 0)
			     rabbit.setDirection("sud");
			if(indice_linie == L - 1)
				 rabbit.setDirection("nord");
			if(indice_coloana == 0)
				 rabbit.setDirection("vest");
			if(indice_coloana == L - 1)
				 rabbit.setDirection("est");
			list.add(rabbit);			
		}
		
		int count_minutes = 0;
		int count_eggs = 0;
		while(is_on(list) == true) {
			for(int i = 0 ; i < L ; i++)
				for(int j = 0 ; j < L ; j++)
					if(matrice[i][j] != 0) {
						if(getEgg(list , matrice , i , j) == true)
						   count_eggs += 1;
					}
					else 
						Empty_parcel(list , matrice , i , j);
		    
			for(int i = 0 ; i < list.size() ; i++) {
				if(list.get(i).is_in_game() == true) {
				    if(list.get(i).getNext_move() == "forward")
					    list.get(i).move_Forward();
				    if(list.get(i).getNext_move() == "change")
					    list.get(i).change_Direction();
				}
			}
			
			for(int i = 0 ; i < list.size() ; i++) 
		    	if(list.get(i).getDirectie_linie() < 0 || list.get(i).getDirectie_linie() >= L || 
		    	       list.get(i).getDirectie_coloana() < 0 || list.get(i).getDirectie_coloana() >= L)
		                    list.get(i).setIs_in_game(false);
			count_minutes += 1;
		}
		
		int max = 0;
		for(int i = 0 ; i < list.size() ; i++)
			if(max < list.get(i).getValoare_cos())
				max = list.get(i).getValoare_cos();	
		
		System.out.println("Numarul de oua  : " + count_eggs);
		System.out.println("Cel mai valoros cos : " + max);
		System.out.println("Durata concursului  : " + count_minutes);
	 
}
	public static boolean is_on(List<Rabbit> list) {
	     boolean stare = false;
	     for(int i = 0 ; i < list.size() ; i++) 
	    	 if(list.get(i).is_in_game() == true) {
	    		 stare = true;
	    		 return stare;
	    	 }
	    return stare;
	}
	
	public static boolean getEgg(List<Rabbit> list , int[][] matrice , int index_1 , int index_2) {
		int poz = 0 , count = 0;
		for(int i = 0 ; i < list.size(); i++)
			if(list.get(i).getDirectie_linie() == index_1 && list.get(i).getDirectie_coloana() == index_2) {
				count += 1;
		        poz = i;
			}
		
		if(count == 1) {
			list.get(poz).changeValoare_cos(matrice[index_1][index_2]);
			matrice[index_1][index_2] = 0;
			list.get(poz).setNext_move("change");
			return true;
		}
		if(count > 1) {
			int min = list.size();
			for(int i = 0; i < list.size() ; i++)
				if(list.get(i).getDirectie_linie() == index_1 && list.get(i).getDirectie_coloana() == index_2) 
					if(min > i)
						min = i;
		
		    list.get(min).changeValoare_cos(matrice[index_1][index_2]);
		    matrice[index_1][index_2] = 0;	
	    	list.get(min).setNext_move("change");
	    	
	    	for(int i = min + 1 ; i < list.size() ; i++)
	    		if(list.get(i).getDirectie_linie() == index_1 && list.get(i).getDirectie_coloana() ==index_2)
	    			list.get(i).setNext_move("forward");
	    	return true;
		}
	return false;
		}
	
	public static void Empty_parcel(List<Rabbit> list , int[][] matrice , int index_1 , int index_2) {
		for(int i = 0 ; i < list.size() ; i++)
			if(list.get(i).getDirectie_linie() == index_1 && list.get(i).getDirectie_coloana() == index_2)
				list.get(i).setNext_move("forward");
	}
}
