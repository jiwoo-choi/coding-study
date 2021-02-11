package hw06_swea1228_암호문1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, M;
	static StringBuilder sb = new StringBuilder();
	
	static class Node {
		String data;
		Node next;
		public Node(String data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
	
	static Node head = new Node("", null);
	
	static Node getNode(int index) {
		int cnt = 0;
		for (Node curNode = head; curNode != null; curNode = curNode.next, cnt++) {
			if (cnt == index) {
				return curNode;
			}
		}
		return null;
	}
	
	static void insertNode(int index, String data) {
		int cnt = 0;
		for (Node curNode = head; curNode != null; curNode = curNode.next, cnt++) {
			if (cnt == index) {
				curNode.next = new Node(data, curNode.next);
			}
		}
	}
	
	static void addNode(String data) {
		Node curNode = head;
		while (curNode.next != null) {
			curNode = curNode.next;
		}
		curNode.next = new Node(data, null);
	}
	
	static void printNode() {
		int cnt = 0;
		for (Node curNode = head.next; cnt < 10; curNode = curNode.next, cnt++) {
			sb.append(curNode.data).append(" ");
		}
	}
	
	static void clearNode() {
		Node curNode = head;
		while (curNode.next != null) {
			Node prevNode = curNode;
			curNode = curNode.next;
			prevNode.next = null;
		}
	}
	

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("in.txt"));
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
		for (int tc = 1; tc <= 10; tc++) {
			clearNode();
			sb.setLength(0);

			
			N = Integer.parseInt(in.readLine());
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < N; i++) {
				addNode(st.nextToken());
			}
			
			M = Integer.parseInt(in.readLine());
			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < M; i++) {
				char cmd = st.nextToken().charAt(0);
				int idx = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				
				Stack<String> stack = new Stack<>();
				for (int j = 0; j < cnt; j++)
					stack.push(st.nextToken());
				
				while (!stack.isEmpty())
					insertNode(idx, stack.pop());
			}
			printNode();

			System.out.print("#" + tc + " ");
			System.out.println(sb);
		}
	}

}
