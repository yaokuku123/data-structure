package com.yqj.dataStructure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: Graph
 * Author: yaoqijun
 * Date: 2020/11/16 18:54
 */
public class Graph {

    private List<String> vertexList;
    private int[][] edges;
    private int numOfEdges;
    private boolean[] isValided;

    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
        isValided = new boolean[n];
    }

    /**
     * 添加顶点
     *
     * @param vertex
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1
     * @param v2
     * @param weight
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    /**
     * 获取边数
     *
     * @return
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     * 获取顶点数
     *
     * @return
     */
    public int getNumOfVertex() {
        return vertexList.size();
    }

    /**
     * 获取顶点下标对应的值
     *
     * @param index
     * @return
     */
    public String getVertex(int index) {
        return vertexList.get(index);
    }

    /**
     * 打印边
     */
    public void showEdges() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    /**
     * 获取index的邻接节点
     *
     * @param index
     * @return
     */
    public int getVertexNeighbor(int index) {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取下一个邻近节点
     *
     * @param index
     * @param current
     * @return
     */
    public int getNextNeighbor(int index, int current) {
        for (int i = current + 1; i < getNumOfVertex(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    public void dfs(int index) {
        System.out.print(getVertex(index) + "=>");
        isValided[index] = true;
        int w = getVertexNeighbor(index);
        while (w != -1) {
            if (!isValided[w]) {
                dfs(w);
            }
            w = getNextNeighbor(index, w);
        }
    }

    public void dfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isValided[i]) {
                dfs(i);
            }
        }
    }

    public void bfs(int index) {
        LinkedList<Integer> queue = new LinkedList<>();
        System.out.print(getVertex(index) + "=>");
        isValided[index] = true;
        queue.addLast(index);
        while (!queue.isEmpty()) {
            int u = queue.removeFirst();
            int w = getVertexNeighbor(u);
            while (w != -1) {
                if (!isValided[w]) {
                    System.out.print(getVertex(w) + "=>");
                    isValided[w] = true;
                    queue.addLast(w);
                }
                w = getNextNeighbor(u, w);
            }
        }
    }

    public void bfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isValided[i]) {
                bfs(i);
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        String[] vertexs = {"A", "B", "C", "D", "E"};
        //添加顶点
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        //添加边
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        //打印边
        graph.showEdges();

        //dfs
        //graph.dfs();

        //bfs
        graph.bfs();
    }
}
