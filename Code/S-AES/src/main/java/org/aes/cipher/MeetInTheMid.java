package org.aes.cipher;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MeetInTheMid {

    private final Encoder encoder = new Encoder();
    private final Decoder decoder = new Decoder();
    private final String[] middleKey = new String[65536];
    private final int[] middleInt = new int[65536];

    /*
    快速排序
     */
    public static void quickSort(int[] arr,String[] heap,int low,int high){
        int i,j,temp,t;
        String s;
        if(low>high){
            return;
        }
        i=low;
        j=high;
        //temp是基准位
        temp = arr[low];
        while (i<j) {
            //先看右边，依次往左递减
            while (temp<=arr[j]&&i<j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp>=arr[i]&&i<j) {
                i++;
            }
            //如果满足条件则交换
            if (i<j) {
                //交换整数数组
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
                //交换对应的密钥数组
                s = heap[j];
                heap[j] = heap[i];
                heap[i] = s;
            }
        }
        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;
        s = heap[low];
        heap[low] = heap[i];
        heap[i] = s;
        //递归调用左半数组
        quickSort(arr,heap,low,j-1);
        //递归调用右半数组
        quickSort(arr,heap,j+1,high);
    }

    /*
    对分查找
     */
    public static int[] binaryLookUp(int[] li, int count) {
        //左指针
        int low = 0;
        //右指针
        int high = li.length - 1;
        int middle;
        while (low <= high) {
            middle = (low + high) / 2;
            if (count == li[middle]) {
                int big = middle,small = middle;
                //试探下标递增是否有相同的对象 如有则增大右下标
                while (big < 65535 && li[big + 1] == li[big]){
                    big++;
                }
                //试探下标递减是否有相同的对象 如有则减小左下标
                while ( small > 0 &&li[small - 1] == li[small]){
                    small--;
                }
                return new int[]{small,big};
            } else if (count < li[middle]) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        //如查找不到 返回负下标
        return new int[]{-1, -1};
    }

    /*
    单一明密文对破解方法
     */
    public List<String> singleMsgAttack(String original,String secret){
        List<String> keyArray = new ArrayList<>();
        StringBuilder key;
        //遍历前十六位的密钥空间 0-65535
        for (int i = 0; i < 65536; i++){
            //将整数转化成二进制字符串
            key = new StringBuilder(Integer.toBinaryString(i));
            //补0操作
            while (key.length() < 16){
                key.insert(0, '0');
            }
            //生成中间密文空间 并转化成整数数组 保持密文与对应密钥下标一致
            middleKey[i] = key.toString();
            middleInt[i] = Integer.parseInt(encoder.encode(original, key.toString()),2);
        }
        //对密文空间做快速排序 排序方法会保持密钥与密文下标一致
        quickSort(middleInt, middleKey,0,65535);
        //遍历后十六位的密钥空间 0-65535
        for (int i = 0; i < 65536; i++){
            //将整数转化成二进制字符串
            key = new StringBuilder(Integer.toBinaryString(i));
            //补0操作
            while (key.length() < 16){
                key.insert(0, '0');
            }
            //用密钥空间对密文进行解密 得到中间密文后在密钥空间进行对分查找 找到相同的中间密钥后返回两个下标 标识中间密文相同的空间
            int[] mark = binaryLookUp(middleInt,Integer.parseInt(decoder.decode(secret, key.toString()),2));
            //检测是否查找到
            if (mark[0] > 0){
                for (int j = mark[0]; j <= mark[1]; j++){
                    //将查找到的空间中的密钥与当前遍历的密钥组合 放入结果堆
                    keyArray.add(middleKey[j] + key);
                }
            }
        }
        return keyArray;
    }

    /*
    * 多明密文对破解
    * 输入初步攻击得出的密钥堆、除了初步攻击输入以外的所有明密文堆
     */
    public List<String> multiMsgAttack(List<String> original,List<String> secret,List<String> keyArray){
        List<String> res = new ArrayList<>();
        //遍历给出的密钥空间
        for (String key : keyArray){
            //遍历给出的每个明密文对
            for (int i = 0; i < original.size(); i++){
                //判断中间密文是否相同 不同则退出循环 全程相同则加入结果堆
                if (!Objects.equals(encoder.encode(original.get(i), key.substring(0, 16)), decoder.decode(secret.get(i), key.substring(16, 32)))){
                    break;
                }else if (i == original.size()-1){
                    res.add(key);
                }
            }
        }
        return res;
    }
}
