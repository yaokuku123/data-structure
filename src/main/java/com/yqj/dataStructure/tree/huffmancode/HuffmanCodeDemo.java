package com.yqj.dataStructure.tree.huffmancode;

import java.io.*;
import java.util.*;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: HuffmanCode
 * Author: yaoqijun
 * Date: 2020/11/14 16:19
 * 功能：哈夫曼编码 test
 */
public class HuffmanCodeDemo {
    public static void main(String[] args) {
//        //字符串的压缩与解压
//        String content = "i like like like java do you like a java";
//        byte[] contentBytes = content.getBytes();
//        //压缩
//        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
//        System.out.println(Arrays.toString(huffmanCodeBytes));
//        //解压
//        byte[] unZipContentBytes = huffmanUnZip(huffmanCodes,huffmanCodeBytes);
//        System.out.println(new String(unZipContentBytes));

        //文件的压缩与解压
        String srcPath = "D:\\image\\1.jpg";
        String zipPath = "D:\\image\\1.zip";
        String unzipPath = "D:\\image\\1unzip.jpg";
        //压缩
        zipFile(srcPath, zipPath);
        System.out.println("压缩成功");
        //解压
        unzipFile(zipPath, unzipPath);
        System.out.println("解压成功");
    }

    /**
     * 解压文件
     *
     * @param zipPath   压缩文件路径
     * @param unzipPath 解压文件路径
     */
    private static void unzipFile(String zipPath, String unzipPath) {
        InputStream in = null;
        OutputStream os = null;
        ObjectInputStream ois = null;
        try {
            in = new FileInputStream(zipPath);
            ois = new ObjectInputStream(in);
            //通过对象输入流获取数据
            byte[] bytes = (byte[]) ois.readObject();
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            byte[] sourceBytes = huffmanUnZip(huffmanCodes, bytes);
            os = new FileOutputStream(unzipPath);
            os.write(sourceBytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                ois.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 压缩文件
     *
     * @param srcPath 源文件路径
     * @param zipPath 压缩后文件路径
     */
    private static void zipFile(String srcPath, String zipPath) {
        InputStream in = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            in = new FileInputStream(srcPath);
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            byte[] huffmanCodesBytes = huffmanZip(bytes);
            os = new FileOutputStream(zipPath);
            oos = new ObjectOutputStream(os);
            //通过对象输出流输出数据
            oos.writeObject(huffmanCodesBytes);
            oos.writeObject(huffmanCodes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
                os.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 哈夫曼编码的解压
     *
     * @param bytes 经过编码后的byte数组
     * @return 原始byte数组
     */
    private static byte[] huffmanUnZip(Map<Byte, String> huffmanCodes, byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            //i在最后一个字节位置，且该字节为不小于0的数，说明不够8位，此时不需要补齐8位
            boolean flag = (i == (bytes.length - 1) && (int) bytes[i] >= 0);
            String s = byteToString(!flag, bytes[i]);
            stringBuilder.append(s);
        }

        //倒转哈夫曼编码的键值对
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        //获取stringBuilder中的各个二进制字符串与哈夫曼编码表比对，存在则将对应的字节保存至list集合
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 0;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                String str = stringBuilder.substring(i, i + count);
                b = map.get(str);
                if (b != null) {
                    flag = false;
                } else {
                    count++;
                }
            }
            list.add(b);
            i += count;
        }

        //list集合转为byte数组
        byte[] sourceBytes = new byte[list.size()];
        for (int i = 0; i < sourceBytes.length; i++) {
            sourceBytes[i] = list.get(i);
        }
        return sourceBytes;
    }

    /**
     * 将字节转换为字符串
     *
     * @param b
     * @param flag 标记是否需要补高位
     * @return
     */
    private static String byteToString(boolean flag, byte b) {
        //将byte转为int
        int intB = b;
        //若需要填充8位则通过该方式填充
        if (flag) {
            intB |= 256;
        }
        //将int类型转为二进制字符串类型
        String str = Integer.toBinaryString(intB);
        if (flag) {
            return str.substring(str.length() - 8); //返回后8位
        } else {
            return str; //直接返回
        }
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
