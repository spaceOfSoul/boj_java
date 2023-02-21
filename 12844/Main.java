import java.io.*;
import java.util.*;

public class Main {
	static int[] tree, lazy, arr;
	static int n,m;
	
	public static int treeInit(int start, int end, int node) {
		if(start==end)
			return tree[node] = arr[start];
		
		int mid = (start+end)/2;
		return tree[node] = treeInit(start, mid, node*2) ^ treeInit(mid+1, end, node*2+1);
	}
	
	public static void update(int start, int end, int left, int right,int val, int node) {
		//System.out.println(node);
		if(lazy[node]!=0) {//갱신
			if((end-start+1)%2 == 1)
				tree[node] ^= lazy[node];
			if(start!=end) {
				lazy[node*2] ^= lazy[node];
				lazy[node*2+1] ^= lazy[node];
			}
			lazy[node] = 0;
		}
		
		if(right<start || end < left)//범위 밖
			return;
		
		if(left <= start && end <= right) {//범위 안
			if((end-start+1)%2 == 1) {
				tree[node] ^= val;
			}
			if(start!=end) {
				lazy[node*2] ^= val;
				lazy[node*2+1] ^= val;
			}
			return;
		}
		
		int mid = (start+end)/2;
		update(start,mid, left, right, val, node*2);
		update(mid+1, end, left, right, val, node*2+1);
		tree[node] = tree[node*2] ^ tree[node*2+1];
	}
	
	public static int query(int start, int end, int left, int right, int node) {
		if(lazy[node]!=0) {//갱신
			if((end-start+1)%2 == 1)
				tree[node] ^= lazy[node];
			if(start!=end) {
				lazy[node*2] ^= lazy[node];
				lazy[node*2+1] ^= lazy[node];
			}
			lazy[node] = 0;
		}
		
		if(right<start || end<left) 
			return 0;
		
		if(left <= start && end <= right)
			return tree[node];
		
		int mid = (start+end) / 2;
		return query(start, mid, left, right, node*2) ^ query(mid+1, end, left, right, node*2+1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String inputStr = br.readLine();
		n = Integer.parseInt(inputStr);
		arr = new int[n];
		tree = new int[4*n];
		lazy = new int[4*n];
		
		inputStr = br.readLine(); 
		
		StringTokenizer st = new StringTokenizer(inputStr, " ");
		for(int i=0; i<n; i++) 
			arr[i] = Integer.parseInt(st.nextToken());
		
		treeInit(0,n-1,1);
		
		m = Integer.parseInt(br.readLine());
		for(int c=0; c<m; c++) {
			inputStr = br.readLine();
			st = new StringTokenizer(inputStr, " ");
			
			if(st.nextToken().equals("1")) {
				int left = Integer.parseInt(st.nextToken());
				int right = Integer.parseInt(st.nextToken());
				int val = Integer.parseInt(st.nextToken());
				
				update(0,n-1,left, right, val, 1);
			}else{
				int left = Integer.parseInt(st.nextToken());
				int right = Integer.parseInt(st.nextToken());
				System.out.println(query(0,n-1,left,right,1));
			}
		}
	}

}
