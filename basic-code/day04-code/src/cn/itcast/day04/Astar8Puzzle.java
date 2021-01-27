package cn.itcast.day04;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;


/*
	f(n):估计从初始状态到目标状态的代价。

　　g(n):从初始状态到当前状态的实际代价。  h2

　　h(n):当前状态与目标状态的错位数  h1
*/
class Node implements Comparable<Node> {
    public int[][] state;
    public String[] actions;
    public int cost;

    Node(int[][] state, String[] actions, int cost) {
        this.state = state;
        this.actions = actions;
        this.cost = cost;
    }
    /*输出0的位置，i行j列*/
    int[] find_zero_ij() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.state[i][j] == 0) {
                    int[] temp = { i, j };
                    return temp;
                }
            }
        }
        return null;
    }
    /*显示当前的数码矩阵*/
    public int[][] copyState(){
        int[][] a = new int[3][3];
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                a[i][j] = this.state[i][j];
            }
        }
        return a;
    }

    public void prnt(){
        System.out.println(Arrays.toString(this.state[0]));
        System.out.println(Arrays.toString(this.state[1]));
        System.out.println(Arrays.toString(this.state[2]));
        System.out.println();
    }
    /*比较*/
    public int compareTo(Node o) {
        // used for sort
        return this.cost - o.cost;
    }
}


public class Astar8Puzzle {
    // 初始状态
    static int[][] start_state = { { 7, 2, 4 }, { 5, 0, 6 }, { 8, 3, 1 } };
    //	 static int[][] start_state = { {4, 0, 5}, {1, 2, 3}, {7, 8, 6}};
    // 目标状态
    static int[][] end_state = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } };

    //算出有多少个不正确的位置
    static int h1(int[][] node_state, int[][] end_state) {
        int cost = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (node_state[i][j] != end_state[i][j]) {
                    cost += 1;
                }
            }
        }
        return cost;
    }
    //计算出当前位置到目标位置的距离
    static int h2(int[][] node_state, int[][] end_state) {
        int cost = 0;
        // TODO

        return cost;
    }

    //判断是否为目标状态
    static boolean isSame(int[][] s1, int[][] s2){
        boolean same = true;
        for (int m = 0; m < s1.length; m++) {
            for (int k = 0; k < s1[0].length; k++) {
                if (s1[m][k] != s2[m][k]) {
                    same = false;
                    break;
                }
            }
        }
        return same;
    }

    //
    static boolean hasNotState(int[][] s, ArrayList<int[][]> c){
        boolean has_not = true;
        for (int i = 0; i < c.size(); i++) {
            if (isSame(s, c.get(i))){
                has_not = false;
                break;
            }
        }
        return has_not;
    }

    //移动位置
    static Node move(Node node, String action) {
        Node next_node = null;
        //矩阵
        int[][] state = node.copyState();
        //0的位置
        int[] temp = node.find_zero_ij();
        int i = temp[0];//0位置的i行,0、1、2
        int j = temp[1];//0位置的j列，0、1、2
        boolean has_next = false;
        //如果0位置向上移动
        if ((action == "Up") && (i - 1 >= 0)) {
            state[i][j] = state[i - 1][j];
            state[i - 1][j] = 0;
            has_next = true;
        }
        // TODO 'Down'
        if ((action == "Down") && (i + 1 <=2)) {
            state[i][j] = state[i + 1][j];
            state[i + 1][j] = 0;
            has_next = true;
        }
        // TODO 'Left'
        if ((action == "Left") && (j - 1 >=0)) {
            state[i][j] = state[i][j - 1];
            state[i][j - 1] = 0;
            has_next = true;
        }
        // TODO 'Right'
        if ((action == "Right") && (j + 1 <=2)) {
            state[i][j] = state[i][j +1 ];
            state[i][j + 1] = 0;
            has_next = true;
        }
        //如果移动成功
        if (has_next) {
            String[] actions = node.actions;

            int action_length = actions == null ? 0 : actions.length;

            String[] new_actions = new String[action_length + 1];
            for (int k = 0; k < action_length; k++) {
                new_actions[k] = actions[k];
            }
            new_actions[action_length] = action;
            int cost = h1(state, end_state) + new_actions.length;
            next_node = new Node(state, new_actions, cost);
        }
        return next_node;
    }

    static Node a_star() {
        Node start_node = new Node(start_state, null, 0 + h1(start_state, end_state));
        Node end_node = null;
        String[] option = { "Up", "Down", "Left", "Right" };

        ArrayList<int[][]> closed = new ArrayList<int[][]>();

        PriorityQueue<Node> fringe = new PriorityQueue<Node>();

        fringe.add(start_node);
        int n = 0;
        while (fringe.size() > 0) {
            Node current_node = fringe.poll();

            // TODO Is end state? You can call isSame(...) for this.
            if (isSame(current_node.state,end_state)){
                end_node = current_node ;
                break ;
            }
            for (int i = 0; i < 4; i++) {
                // TODO call move(...) to get next_node by each action
                Node next_node=move(current_node,option[i]);
                if (next_node != null &&  hasNotState(next_node.state, closed)) {
                    // TODO 入队列
                    fringe.add(next_node);
                }
            }
            closed.add(current_node.state);

            n += 1;
            if (n % 10000 == 0) {
                System.out.println("n=" + n + ",current_node.getCost=" + current_node.cost);
            }
            if (n >= 100000) {
                System.out.println("Do not find resolution after searching 100000 nodes ...");
            }
        }
        // print actions
        Node next_node = start_node;
        System.out.println("Initial state");
        next_node.prnt();

        for (int i = 0; i < end_node.actions.length; i++) {
            next_node = move(next_node, end_node.actions[i]);
            System.out.println(end_node.actions[i]);
            next_node.prnt();
        }
        System.out.println(n + ":" + end_node.actions.length + ":" + Arrays.toString(end_node.actions));
        return end_node;
    }

    public static void main(String[] args) {
        Astar8Puzzle.a_star();
    }
}