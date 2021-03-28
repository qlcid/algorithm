package algo0322;

import java.util.Scanner;

public class JOL_1681_해밀턴순환회로 {

	static int N, distance, min;
	static int[][] adj;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		adj = new int[N][N];
		visited = new boolean[N];
		
		for (int i = 0; i < N * N; i++)
			adj[i / N][i % N] =sc.nextInt();

		min = Integer.MAX_VALUE;
		dfs(0, 0);
		
		System.out.println(min == Integer.MAX_VALUE ? 0 : min);
	}

	static void dfs(int depth, int from) {
		if (min < distance) return;
		
		if (depth == N - 1) {
			// 모든 장소를 다 방문하고 회사로 돌아가지 못할 경우
			if (adj[from][0] == 0) return;
			
			distance += adj[from][0];
			min = Math.min(min, distance);
			distance -= adj[from][0];
			
			return;
		}
		
		// 시작점 빼고 방문
		for (int i = 1; i < N; i++) {
			if (visited[i]) continue;	// 이미 방문한 정점일 경우
			if (from == i || adj[from][i] == 0) continue;	// 출발지=목적지, 갈 수 없는 경우 
			
			visited[i] = true;
			distance += adj[from][i];
			dfs(depth + 1, i);
			distance -= adj[from][i];			
			visited[i] = false;
		}
	}
}
