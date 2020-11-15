package com.yqj.dataStructure.tree.huffmancode;

import java.util.*;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: HuffmanCode
 * Author: yaoqijun
 * Date: 2020/11/14 16:19
 * 功能：哈夫曼编码
 */
public class HuffmanCodeDemo {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
        System.out.println(Arrays.toString(huffmanCodeBytes) +
                " 压缩率：" + (contentBytes.length - huffmanCodeBytes.length) * 1.0 / contentBytes.length * 100 + "%");
    }

    /**
     * 哈夫曼编码封装的方法
     *
     * @param bytes 传入原始数据的byte数组
     * @return 压缩后的byte数组
     */
    public static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        Map<Byte, String> huffmanCodes = getHuffmanCodes(huffmanTreeRoot);
        byte[] zip = zip(bytes, huffmanCodes);
        return zip;
    }

    /**
     * 将原先字符串对应的byte数组通过哈夫曼编码转换为压缩后的byte数组
     *
     * @param bytes        原始字符串的byte数组
     * @param huffmanCodes 哈夫曼编码表
     * @return 压缩后的byte数组
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        //获取原始byte数组每一个对应的哈夫曼编码然后拼接起来
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //获取新的压缩后byte数组的长度
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        //将String类型的二进制转换为byte类型
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            if (i + 8 > stringBuilder.length()) { //不足8位
                huffmanCodeBytes[index] = (byte) Integer.parseInt(stringBuilder.substring(i), 2);
            } else {
                huffmanCodeBytes[index] = (byte) Integer.parseInt(stringBuilder.substring(i, i + 8), 2);
            }
            index++;
        }
        return huffmanCodeBytes;
    }

    private static Map<Byte, String> huffmanCodes = new HashMap<>(); //存储huffman编码
    private static StringBuilder stringBuilder = new StringBuilder(); //用于拼接路径，0=左边 1=右边

    /**
     * 获取root节点开始的哈夫曼编码
     *
     * @param node root节点
     * @return 哈夫曼编码的map集合
     */
    private static Map<Byte, String> getHuffmanCodes(Node node) {
        if (node != null) {
            getCodes(node.getLeft(), "0", stringBuilder);
            getCodes(node.getRight(), "1", stringBuilder);
            return huffmanCodes;
        } else {
            System.out.println("哈夫曼树为空");
            return null;
        }
    }

    /**
     * 获取传入node节点的所有叶子节点的哈夫曼编码，并放入map集合
     *
     * @param node          传入的节点
     * @param code          路径 0=左 1=右
     * @param stringBuilder 递归拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        //拼接路径的编码
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null) {
            //非叶子节点
            if (node.getValue() == null) {
                getCodes(node.getLeft(), "0", stringBuilder2);
                getCodes(node.getRight(), "1", stringBuilder2);
            } else { //叶子节点
                huffmanCodes.put(node.getValue(), stringBuilder2.toString());
            }
        }
    }


    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("哈夫曼树为空");
        }
    }

    /**
     * 创建哈夫曼树
     *
     * @param nodes
     * @return
     */
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node leftNode = nodes.remove(0);
            Node rightNode = nodes.remove(0);
            Node parent = new Node(null, leftNode.getWeight() + rightNode.getWeight());
            parent.setLeft(leftNode);
            parent.setRight(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    /**
     * 获取字节数组中每个字符的权重和数值，并以Node对象的形式保存再List集合
     *
     * @param contentBytes 字节数组
     * @return list集合
     */
    private static List<Node> getNodes(byte[] contentBytes) {
        List<Node> nodes = new ArrayList<>();
        Map<Byte, Integer> map = new HashMap<>();
        for (byte b : contentBytes) {
            if (map.get(b) == null) {
                map.put(b, 1);
            } else {
                map.put(b, map.get(b) + 1);
            }
        }
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }
}

class Node implements Comparable<Node> {
    private Byte value;
    private int weight;
    private Node left;
    private Node right;

    public Node(Byte value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public Byte getValue() {
        return value;
    }

    public void setValue(Byte value) {
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", weight=" + weight +
                '}';
    }

}
