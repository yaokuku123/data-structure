package com.yqj.dataStructure.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: PolandNotation
 * Author: yaoqijun
 * Date: 2020/11/9 9:29
 */
public class PolandNotation {
    public static void main(String[] args) {
//        String expression = "3 4 + 5 * 6 -";
//        List<String> stringList = stringToList(expression);
//        System.out.println(stringList);
//        System.out.println("result="+cal(stringList));

        //中缀字符串表达式
        String expression = "1+((2+3)*4)-50";
        //转换为中缀list集合
        List<String> infixStringList = infixStringToList(expression);
        System.out.println(infixStringList);
        //中缀表达式转换为后缀表达式
        List<String> subffixList = infixToSubffixList(infixStringList);
        System.out.println(subffixList);
        //计算后缀表达式结果
        System.out.println("result="+cal(subffixList));
    }

    /**
     * 中缀表达式转换为后缀表达式
     * @param infixStringList 中缀表达式
     * @return 后缀表达式
     */
    public static List<String> infixToSubffixList(List<String> infixStringList){
        Stack<String> stack = new Stack<>(); //存放符号位
        List<String> list = new ArrayList<>(); //存放结果

        for (String item : infixStringList) {
            if (item.matches("\\d+")){ //数字直接入list
                list.add(item);
            } else if ("(".equals(item)){ //左括号直接入栈
                stack.push(item);
            } else if (")".equals(item)){ //右括号持续出栈直到遇见左括号
                while (!("(".equals(stack.peek()))){
                    list.add(stack.pop());
                }
                stack.pop(); //弹出左括号
            } else { //符号位
                //若stack不空且栈顶符号位优先级大于等于将入栈的符号位，则持续弹出到结果list中
                while (stack.size()!=0 && (Operation.getPriority(item) <= Operation.getPriority(stack.peek()))){
                    list.add(stack.pop());
                }
                stack.push(item);
            }
        }
        //弹出栈中剩余的元素
        while (stack.size()!=0){
            list.add(stack.pop());
        }
        return list;
    }

    /**
     * 将中缀表达式字符串转换为list集合
     * @param expression 中缀表达式字符串
     * @return list集合
     */
    public static List<String> infixStringToList(String expression) {
        List<String> list = new ArrayList<>();
        String str = ""; //保存多位数拼接的值
        char c; //遍历的每个字符
        int i = 0; //遍历中缀表达式
        do {
            if ((c=expression.charAt(i))<='0' || (c=expression.charAt(i))>='9'){ //若为非数字直接加入list
                list.add("" + c);
                i++;
            } else { //数字，考虑多位数的情况
                str = ""; //置空
                while (i<expression.length() && (c=expression.charAt(i))>='0' && (c=expression.charAt(i))<='9'){
                    str += c;
                    i++;
                }
                list.add(str);
            }
        }while (i<expression.length());
        return list;
    }

    /**
     * 将以空格为间隔的后缀字符串转换为list集合中
     *
     * @param expression 字符串形式的后缀表达式
     * @return 后缀list集合
     */
    public static List<String> stringToList(String expression) {
        //拆分String成数组
        String[] strings = expression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele : strings) {
            list.add(ele);
        }
        return list;
    }

    /**
     * 计算后缀表达式
     *
     * @param list
     * @return 结果
     */
    public static int cal(List<String> list) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        for (String item : list) {
            if (item.matches("\\d+")) { //若为数字则直接入栈
                stack.push(Integer.parseInt(item));
            } else { //若为运算符则弹出栈顶的两个元素
                int num2 = stack.pop();
                int num1 = stack.pop();
                if ("+".equals(item)) {
                    result = num1 + num2;
                } else if ("-".equals(item)) {
                    result = num1 - num2; //次栈顶-栈顶
                } else if ("*".equals(item)) {
                    result = num1 * num2;
                } else if ("/".equals(item)) {
                    result = num1 / num2; //次栈顶/栈顶
                }
                stack.push(result); //将结果再入栈
            }
        }
        return stack.pop(); //返回栈中最后剩下的那个元素
    }
}

//判断符号优先级的类
class Operation{
    private static final int SUM=1;
    private static final int SUB=1;
    private static final int MUL=2;
    private static final int DIV=2;

    /**
     * 获取符号的优先级
     * @param ops 符号
     * @return 优先级 越高优先级越强
     */
    public static int getPriority(String ops){
        int result = 0;
        switch (ops){
            case "+":
                result = SUM;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                break;
        }
        return result;
    }
}
