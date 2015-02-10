package kelp.stringsearch;

public class Levenshtein {
	
	public int  levenshtein(String string1, String string2, int insert, int replace, int delete) {
		int len1 = string1.length(), len2 = string2.length();

		if (len1 == 0) return len2;
		if (len2 == 0) return len1;
		if (string1.equals(string2)) return 0;

		int[] cost = new int[len2+1];
		int[] final_cost = new int[len2+1];
			
		for (int index = 0; index < cost.length; index++) cost[index] = index * delete;
			
		for (int i = 0; i < len1; i++) {
			final_cost[0] = i * insert;
				
			for (int j = 0; j < len2; j++) {
				int match = (string1.charAt(i) == string2.charAt(j)) ? 0 : replace;
				int replace_cost = cost[j] + match;
				int insert_cost = cost[j+1] + insert;
				int delete_cost = final_cost[j] + delete;
				final_cost[j+1] = minimun(replace_cost, insert_cost, delete_cost);
			}
			for (int k = 0; k < cost.length; k++) {
				cost[k] = final_cost[k];
			}
		}
		return final_cost[len2];
	}

	private int minimun(int a, int b, int c) {
		return Math.min(Math.min(a, b), c);
	}
}
