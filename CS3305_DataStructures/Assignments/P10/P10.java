import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class P10 {
	private static final Scanner user_input = new Scanner(System.in);


	// trims leading and trailing whitespace from a string
	private static String TrimWhitespace(String str) {
		int start_idx = 0, end_idx = 0;

		// find the first non-whitespace character
		for (int i=0; i<str.length(); i++) {
			if (str.charAt(i) == ' ') continue;
			start_idx = i;
			break;
		}

		// find the last normal character
		for (int i=str.length()-1; i>=0; i--) {
			if (str.charAt(i) == ' ') continue;
			end_idx = i+1; // .substring goes until end-1, basically
			break;
		}

		return str.substring(start_idx, end_idx);
	}

	

	private static WeightedGraph<Integer> ParseInput(String file_path) {
		File graph_file = new File(file_path);
		
		int num_vertices = -1;
		ArrayList<String> raw_edges_list = new ArrayList<>();
		
		try(Scanner graph_file_sc = new Scanner(graph_file);) {

			// first line is the number of vertices in the graph
			num_vertices = Integer.parseInt(graph_file_sc.nextLine());
			System.out.printf("There are %d vertices in this graph.\n", num_vertices);

			// from there are the rest of the edges
			while (graph_file_sc.hasNextLine()) {
				raw_edges_list.add(graph_file_sc.nextLine());
			}
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}

		ArrayList<WeightedEdge> edges = new ArrayList<>();

		for (String raw_edges : raw_edges_list) {
			String[] raw_edges_tokens = raw_edges.split("\\|");
			for (String raw_edge : raw_edges_tokens) {
				raw_edge = TrimWhitespace(raw_edge);
				String[] raw_edge_tokens = raw_edge.split(",");
				if (raw_edge_tokens.length != 3) return null;

				int u = Integer.parseInt(TrimWhitespace(raw_edge_tokens[0]));
				int v = Integer.parseInt(TrimWhitespace(raw_edge_tokens[1]));
				double weight = (double)Integer.parseInt(TrimWhitespace(raw_edge_tokens[2]));
				edges.add(new WeightedEdge(u, v, weight));

				// also add the reverse
				edges.add(new WeightedEdge(v, u, weight));
			}
		}
		
		return new WeightedGraph<Integer>(edges, num_vertices);
		
	}
	
	public static void main(String[] args) {

		System.out.printf("Please enter a file path: ");
		String file_path = user_input.nextLine();
		WeightedGraph<Integer> graph = ParseInput(file_path);

		graph.printWeightedEdges();
		WeightedGraph<Integer>.MST mst = graph.getMinimumSpanningTree(0);
		System.out.printf("Total weight in MST is %.1f\n", mst.getTotalWeight());

		mst.printTree();
		
		
		user_input.close();
	}
}
