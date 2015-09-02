package regression;

import graphics.graphs.TreeGraphView;
import targetRepresentation.GPParameters;
import treeElement.terminal.Terminal;
import treeRepresentation.Data;
import treeRepresentation.TreeNode;

public class MainClass {

	public static void main(String[] args) throws Exception {
		System.out.println(new TreeNode(new Data(2)) instanceof Terminal );
		Population population = new Population();
		population.initializePopulation();
		TreeNode fittest = population.getFittest().getSchema();
		System.out.println("wynik 1(x=3) "+fittest.getValue(3.0d));
		System.out.println("wynik 2(x=7) "+fittest.getValue(7.0));
		for(Chromosome e : population.getPopulation()){
			System.out.println(e.getFitness());
		}
		System.out.println("tutaj zaczyna sie przygoda");
		//TreeGraphView.displayTreeGraph(fittest, "TEST");
		//TreeNode random = population.getFittest().chooseRandomNode(fittest, true, 0, 0);
		//System.out.println("Random :" + random);
		for(int i = 0; i < GPParameters.GENERATIONS_AMOUNT; i++) {
			population = Genetics.evolve(population);
			for(Chromosome e : population.getPopulation()){
				System.out.println(e.getFitness());
			}
			System.out.println("~~~~~~~~~~~" + i + " Population ~~~~~~~~~~~~");
		}
		TreeGraphView.displayTreeGraph(population.getFittest().getSchema(), "TEST");
		System.out.println(population.getFittest().getSchema().printFunction());
		//System.out.println(population.getFittest().getSchema().printFunction());

	}

}
