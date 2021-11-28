import java.util.*;

public class mission2 {
    public static void main(String[] args) {
        System.out.println("Введите размер матрицы:");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] matrix = new int[n][n];
        ArrayList<LinkedList<Integer>> adjLists = new ArrayList<>();

        rand(n, matrix);
        addEdge(n, matrix, adjLists);
        output(n, matrix, adjLists);

        int count = 0;
        int[] vis = new int[n];
        int[] vis1 = new int[n];
        int[] res = new int[n];
        int[] res1 = new int[n];
        System.out.println("Введите вершину:");
        Scanner in1 = new Scanner(System.in);
        int v = in1.nextInt() - 1;

        for (int i = 0; i < n; i ++){
            vis[i] = 0;
            vis1[i] = 0;
            res[i] = 0;
            res1[i] = 0;
        }


        DFS(n, count, v, vis, res, matrix);
        count = 0;
        DFS1(n, count, v, vis1, res1, adjLists);

        for (int i =0; i < n; i++)
            System.out.println(i + 1 + ": " + res[i]);
        System.out.println();
        for (int i =0; i < n; i++)
            System.out.println(i + 1 + ": " + res1[i]);
    }

    public static void rand(int n, int[][] matrix) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = (int) ((Math.random() * 2) + 0);
                matrix[j][i] = matrix[i][j];

                if (i == j) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void addEdge(int n, int[][] matrix, ArrayList<LinkedList<Integer>> adjLists) {
        for (int i = 0; i < n; i++)
            adjLists.add(new LinkedList<>());

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1)
                    adjLists.get(i).add(j + 1);
            }
        }

    }

    public static void output(int n, int[][] matrix, ArrayList<LinkedList<Integer>> adjLists) {
        System.out.println("\nМатрица:\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nСписок:");
        System.out.println(adjLists);
    }

    public static void DFS(int n, int count, int v, int[] vis, int[] res, int[][] matrix){
        count += 1;
        vis[v] = 1;
        for (int i = 0; i < n; i++){
            if (matrix[v][i] == 1 & vis[i] == 0)
                DFS(n, count, i, vis, res, matrix);
            if (matrix[v][i] == 1)
                res[i] = count;
        }
        count -= 1;
        if (count > res[n - 1])
            return;
        res[v] = count;
    }

    public static void DFS1(int n, int count, int v, int[] vis1, int[] res1, ArrayList<LinkedList<Integer>> adjLists){
        count += 1;
        vis1[v] = 1;
        for (int i = 0; i < n; i++){
            if (adjLists.get(v).contains(i + 1) & vis1[i] == 0)
                DFS1(n, count, i, vis1, res1, adjLists);
            if (adjLists.get(v).contains(i + 1))
                res1[i] = count;
        }
        count -= 1;
        if (count > res1[n - 1])
            return;
        res1[v] = count;
    }
}
