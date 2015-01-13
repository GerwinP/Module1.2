package ss.week5;

import java.util.LinkedList;

public class SmartStrategy implements Strategy{

	public String getName() {
		return "Smart";
	}

	public int determineMove(Board b, Mark m) {
		LinkedList<Integer> winnendeSet = new LinkedList<Integer>();
		LinkedList<Integer> blokkerendeSet = new LinkedList<Integer>();
		LinkedList<Integer> leegveld = new LinkedList<Integer>();
		int x = 0;
		int y = 0;
		int set = 0;
		int aantalEmpty = 0;
		int aantalMark = 0;
		//als het middelste veld leeg is, speel middelste veld
		if(b.isEmptyField(1,1)){
			return b.index(1, 1);
		}
		//winnende set
		else{
			//voeg alle gegarandeerd winnende sets toe aan lijst winnendeSet
			//check horizontaal
			for(x = 0; x < 3; x++){
				for(y = 0; y < 3; y++){
					if(b.isEmptyField(x,y)){
						aantalEmpty++;
						set = b.index(x,y);
					}
					if(b.getField(x,y) == m){
						aantalMark++;
					}
				}
				if(aantalEmpty == 1 && aantalMark == 2){
					winnendeSet.add(set);
				}
			}
			aantalEmpty = 0;
			aantalMark = 0;
			//check verticaal
			for(y = 0; y < 3; y++){
				for(x = 0; x < 3; x++){
					if(b.isEmptyField(x, y)){
						aantalEmpty++;
						set = b.index(x, y);
					}
					if(b.getField(x, y) == m){
						aantalMark++;
					}
				}
				if(aantalEmpty == 1 && aantalMark == 2){
					winnendeSet.add(set);
				}
			}
			aantalEmpty = 0;
			aantalMark = 0;
			//check van links boven naar rechts onder\
			for(x = 0; x < 3; x++){
				if(b.isEmptyField(x, x) == true){
					aantalEmpty++;
					set = b.index(x, x);
				}
				if(b.getField(x, x) == m){
					aantalMark++;
				}
			}
			if(aantalEmpty == 1 && aantalMark == 2){
				winnendeSet.add(set);
			}
			aantalEmpty = 0;
			aantalMark = 0;
			//check van links onder naar echts boven
			for(x = 0; x < 3; x++){
				if(b.isEmptyField(x, 2-x) == true){
					aantalEmpty++;
					set = b.index(x, 2-x);
				}
				if(b.getField(x, 2-x) == m){
					aantalMark++;
				}
			}
			if(aantalEmpty == 1 && aantalMark == 2){
				winnendeSet.add(set);
			}
		}
		aantalEmpty = 0;
		aantalMark = 0;
		if(winnendeSet.size() > 0){
			double random = Math.random() * winnendeSet.size();
			int randomInt = (int)random;
			return winnendeSet.get(randomInt);
		}	
		//blokkerende set
		else{
			//tegenstander wint
			//check horizontaal
			for(x = 0; x < 3; x++){
				for(y = 0; y < 3; y++){
					if(b.isEmptyField(x, y) == true){
						aantalEmpty++;
						set = b.index(x, y);
					}
					if(b.getField(x, y) != m && b.isEmptyField(x, y) == false){
						aantalMark++;
					}
				}
				if(aantalEmpty == 1 && aantalMark == 2){
					blokkerendeSet.add(set);
				}
			}
			aantalEmpty = 0;
			aantalMark = 0;
			//check verticaal
			for(y = 0; y < 3; y++){
				for(x = 0; x < 3; x++){
					if(b.isEmptyField(x, y) == true){
						aantalEmpty++;
						set = b.index(x, y);
					}
					if(b.getField(x, y) != m && b.isEmptyField(x, y) == false){
						aantalMark++;
					}
				}
				if(aantalEmpty == 1 && aantalMark ==2 ){
					blokkerendeSet.add(set);
				}
			}
			aantalEmpty = 0;
			aantalMark = 0;
			//check van links boven naar rechts onder\
			for(x = 0; x < 3; x++){
				if(b.isEmptyField(x, x) == true){
					aantalEmpty++;
					set = b.index(x, x);
				}
				if(b.getField(x, x) != m && b.isEmptyField(x, x) == false){
					aantalMark++;
				}
			}
			if(aantalEmpty == 1 && aantalMark == 2){
				blokkerendeSet.add(set);
			}
			aantalEmpty = 0;
			aantalMark = 0;
			//check van links onder naar echts boven
			for(x = 0; x < 3; x++){
				if(b.isEmptyField(x, 2-x) == true){
					aantalEmpty++;
					set = b.index(x, 2-x);
				}
				if(b.getField(x, 2-x) != m && b.isEmptyField(x, 2-x) == false){
					aantalMark++;
				}
			}
			if(aantalEmpty == 1 && aantalMark == 2){
				blokkerendeSet.add(set);
			}
		}
		if(blokkerendeSet.size() > 0){
			double random = Math.random() * blokkerendeSet.size();
			int randomInt = (int)random;
			return blokkerendeSet.get(randomInt);
		}
		//random keuze als de rest niet van toepassing is
		else{
			for(x = 0; x < 9; x++){
				if(b.isEmptyField(x) == true){
					leegveld.add(x);
				}
			}
			double randomGetal = Math.random() * leegveld.size();
			int randomGetalInt = (int)randomGetal;
			return leegveld.get(randomGetalInt);
		}
	}
}
