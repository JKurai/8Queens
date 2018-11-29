import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class main {

	public static void main(String[] args) {
		int populationSize = 100;
		int maxGenerations = 40000;

		ArrayList<Combination> population = new ArrayList<>();
		int generation = 0;
		Combination solution = new Combination();
		for (int i = 0; i < populationSize; i++) // the population is initialized
		{
			Combination comb = new Combination();
			population.add(comb);
		}
		
		int end = 1;

		do {
			Random r = new Random();
			System.out.println("Generation: " + generation);
			for (int i = 0; i < population.size() && end != 0; i++)// population evaluate
			{
				end = population.get(i).evaluate();
				if(end==0)
					solution=population.get(i);
			}
			if(end!=0)
			{	
				Combination parent1 = new Combination();
				parent1.setQuality(99);
				Combination parent2 = new Combination();
				parent2.setQuality(99);
				
				int[] random = new int[5];
				
				boolean notRep=true; // 5 random number for choose the parents
				do {
					notRep = true;
					for (int i = 0; i < 5; i++)
						random[i] = r.nextInt(10);
					for (int i = 0; i < 5; i++)
						for (int j = 0; j < 5; j++)
							if (random[i] == random[j] && i != j)
								notRep = false;
				} while (notRep == false);
				
				for (int i = 0; i < 5; i++) //  5 random parents and finally we choose the 2 best
				{
					Combination aux = new Combination();
					aux = population.get(random[i]);
					if (aux.getQuality() < parent1.getQuality()) {
						if (parent1.getQuality() < parent2.getQuality())
							parent2 = parent1;
						parent1 = aux;
					} else if (aux.getQuality() < parent2.getQuality()) {
						parent2 = aux;
					}
					r.setSeed(r.nextLong());
				}
				
				population.remove(population.indexOf(parent1));
				population.remove(population.indexOf(parent2));
				
				System.out.println("Parent's best qualities: "+parent1.getQuality()+" | "+parent2.getQuality());
				
				Combination son1 = new Combination(parent1);
				Combination son2 = new Combination(parent2);
				
				int randomAux = r.nextInt(8);
				int randomAux1 = r.nextInt(8);
				if (randomAux > randomAux1) {
					int aux = randomAux;
					randomAux = randomAux1;
					randomAux1 = aux;
				}
				
				int[] auxHijo1 = new int[8];
				int[] auxHijo2 = new int[8];
				for (int i = 0; i < 8; i++) {
					auxHijo1[i] = 99;
					auxHijo2[i] = 99;
				}
				for (int i = randomAux; i < randomAux1; i++) {
					auxHijo1[i] = parent1.getQueens()[i];
					auxHijo2[i] = parent2.getQueens()[i];
				}
			//	/////////////////////////////////
				for (int i = randomAux1; i < 8; i++) {
					int aux = parent2.getQueens()[i];
					boolean rep = false;
					for (int j = 0; j < 8 && rep == false; j++)
						if (auxHijo1[j] == aux)
							rep = true;
					for (int j = randomAux1; j < 8 && rep == false; j++)
						if (auxHijo1[j] == 99) {
							auxHijo1[j] = aux;
							rep = true;
						}	
					if (rep == false)
						for (int j = 0; j < randomAux && rep == false; j++)
							if (auxHijo1[j] == 99) {
								auxHijo1[j] = aux;
								rep = true;
							}
				}
				
				for (int i = 0; i < randomAux1; i++) {
					int aux = parent2.getQueens()[i];
					boolean rep = false;
					for (int j = 0; j < 8 && rep == false; j++)
						if (auxHijo1[j] == aux)
							rep = true;
					for (int j = randomAux1; j < 8 && rep == false; j++)
						if (auxHijo1[j] == 99) {
							auxHijo1[j] = aux;
							rep = true;
						}
					if (rep == false)
						for (int j = 0; j < randomAux && rep == false; j++)
							if (auxHijo1[j] == 99) {
								auxHijo1[j] = aux;
								rep = true;
							}
				}
			//	///////////////////////////////////////////
			//	/////////////////////////////////
				for (int i = randomAux1; i < 8; i++) {
					int aux = parent1.getQueens()[i];
					boolean rep = false;
					for (int j = 0; j < 8 && rep == false; j++)
						if (auxHijo2[j] == aux)
							rep = true;
					for (int j = randomAux1; j < 8 && rep == false; j++)
						if (auxHijo2[j] == 99) {
							auxHijo2[j] = aux;
							rep = true;
						}
					if (rep == false)
						for (int j = 0; j < randomAux && rep == false; j++)
							if (auxHijo2[j] == 99) {
								auxHijo2[j] = aux;
								rep = true;
							}
				}
				
				for (int i = 0; i < randomAux1; i++) {
					int aux = parent1.getQueens()[i];
					boolean rep = false;
					for (int j = 0; j < 8 && rep == false; j++)
						if (auxHijo2[j] == aux)
							rep = true;
					for (int j = randomAux1; j < 8 && rep == false; j++)
						if (auxHijo2[j] == 99) {
							auxHijo2[j] = aux;
							rep = true;
						}
					if (rep == false)
						for (int j = 0; j < randomAux && rep == false; j++)
							if (auxHijo2[j] == 99) {
								auxHijo2[j] = aux;
								rep = true;
							}
				}
//	///////////////////////////////////////////
			son1.setQueens(auxHijo1);
			son2.setQueens(auxHijo2);

			son1.mutation();
			son2.mutation();

			int max = 0;
			int first = 99;
			int second = 99;
			int max2 = 0;
			for (int i = 0; i < population.size(); i++)// se buscan los dos peores de la poblacion para sustituirlos
			{
				Combination aux = new Combination();
				aux = population.get(i);
				if (aux.getQuality() > max) {
					if (max > max2) {
						max2 = max;
						second = first;
					}
					first = i;
					max = aux.getQuality();
				} else if (aux.getQuality() > max2) {
					second = i;
					max2 = aux.getQuality();
				}
			}
				son1.setAge(0);
				son2.setAge(0);
			
				population.add(parent1);
				population.add(parent2);
				population.set(first, son1);
				population.set(second, son2);

				r.setSeed(r.nextLong());
				generation++;
			}
		} while (end != 0 && generation < maxGenerations);

		if (end == 0)
		{
			System.out.println("solution find!!");
			solution.printQueens();
			solution.printBoard();
		}else
			System.out.println("Max generation");
	}

}
