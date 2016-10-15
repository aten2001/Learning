package code.cheff;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * https://www.codechef.com/problems/RGAME
 * @author gokulvanan.v
 *
 */
public class RGame {

	static class CombinationBuilder{

		static class Node{
			public Node(String patt, long i) {
				this.pattern = patt;
				this.points = i;
			}
			public long points;
			public String pattern;
			
		}

		private Map<String,Node> combinationSumMap = new HashMap<>();
		
		public void ingest(int index, long val){
			if(combinationSumMap.isEmpty()){
				combinationSumMap.put(index+"", new Node(val+"",0));
			}else{
				Map<String,Node> newCombMap = new HashMap<>();
				for(String key : combinationSumMap.keySet()){
					Node obj = combinationSumMap.get(key);
					long value = obj.points;
					//right
					int last = obj.pattern.length()-1;
					long increase = 
							Long.parseLong(obj.pattern.charAt(last)+"")
							* val;
					newCombMap.put(key+index, new Node(obj.pattern+val, value+increase));
					//left
					int first = 0;
					increase = 
							Long.parseLong(obj.pattern.charAt(first)+"")
							* val;
					newCombMap.put(index+key, new Node(val+obj.pattern, value+increase));
				}
				combinationSumMap = newCombMap; //overwrite
			}
			
		}
		

		public long sum(){
			long sum = 0;
			for(Node obj : combinationSumMap.values()){
				sum += obj.points;
			}
			return sum % 1000000007;
		}
	}



	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		while(T-- > 0){
			int N = sc.nextInt();
			CombinationBuilder comb = new CombinationBuilder();
			for(int i=0; i<=N; i++){
				int val = sc.nextInt();
				comb.ingest(i, val);
			}
			System.out.println(comb.sum());
		}
		sc.close();
	}
}
