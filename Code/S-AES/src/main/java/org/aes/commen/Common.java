package org.aes.commen;

import java.util.ArrayList;
import java.util.List;

public class Common {
    /*
    * 二进制检测方法
    * 检测输入字符串是否只由0或1组成 是则返回true
     */
    public boolean checkBinary(String message){
        boolean res = true;
        for (int i = 0; i < message.length(); i++){
            //如果有某一位不由0或1构成 设置结果为false 推出循环
            if (message.charAt(i) != '1' && message.charAt(i) != '0'){
                res = false;
                break;
            }
        }
        return res;
    }

    /*
    * AscII码转二进制方法
    * 输入AscII字符串 输出各位对应的二进制字符串数组 若输入为奇数个字符 则在末尾补八位0
     */
    public String[] ascToBin(String ascinput){
        String[] out = new String[(ascinput.length()+1)/2];
        StringBuilder combine = new StringBuilder();
        for (int i = 0; i < ascinput.length(); i++){
            //转二进制
            StringBuilder tem = new StringBuilder(Integer.toBinaryString(ascinput.charAt(i)));
            //二进制补0
            while (tem.length() < 8) {
                tem.insert(0, "0");
            }
            //将字符加入临时变量拼接
            combine.append(tem);
            //当临时变量拼接为16位字符时放入数组,清空临时变量
            if (combine.length() == 16){
                out[i/2] = combine.toString();
                combine = new StringBuilder();
            }
        }
        //字符数为奇数，最后一个字符组只有八位，需要补位才能加密
        if (combine.length() > 0){
            out[(ascinput.length()+1)/2-1] = combine + "00000000";
        }
        return out;
    }

    /*
    * 中文转二进制方法
    * 输入中文字符串 输出各位对应的二进制字符串数组
     */
    public List<String> chnToBinStr(String str) {
        List<String> res = new ArrayList<>();
        //将字符串转为字符数组
        char[] chars=str.toCharArray();
        for (char aChar : chars) {
            //迭代字符数组 将每一位转化成二进制字符串 添加至res列表
            StringBuilder binstr = new StringBuilder(Integer.toBinaryString(aChar));
            while (binstr.length() < 16) {
                binstr.insert(0, "0");
            }
            res.add(binstr.toString());
        }
        return res;
    }

    /*
    二进制转中文方法
     */
    public String binStrToChn(String binary) {
        int decimal = Integer.parseInt(binary, 2);
        return String.valueOf((char)decimal);
    }

    /*
    二进制字符串按位异或方法
     */
    public String plus(String a , String b){
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < a.length(); i++){
            if (a.charAt(i) == b.charAt(i)){
                res.append("0");
            }else{
                res.append("1");
            }
        }
        return res.toString();
    }
}
