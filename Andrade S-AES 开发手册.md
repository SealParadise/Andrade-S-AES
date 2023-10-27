# Andrade S-AES 开发手册

开发人员：徐涵浩 明子鸿

手册撰写人员：明子鸿 徐涵浩

单位：重庆大学大数据与软件学院

联系方式：shennmo@foxmall.com



## 1. 引言

### 1.1 关于本手册

​	本手册旨在提供有关S-AES算法实现程序的详细开发和使用信息。它包括了安装、配置、使用说明、实现细节以及测试等方面的信息，以帮助开发者正确使用和理解该程序。



### 1.2 目标与范围

​	编写S-AES（Simplified Advanced Encryption Standard）开发手册的主要目的是为了确保开发和使用S-AES算法实现程序的顺利进行，提供清晰的文档和指南，以支持用户、开发团队和维护人员，旨在提高程序的可维护性、可用性和用户体验的同时促进团队内部的知识共享和合作。主要包括以下目标：

​	①**提供使用指南**：Andrade S-AES开发手册旨在向开发者和用户提供清晰、易于理解的使用指南，它详细说明了如何安装、配置、编译和使用本S-AES算法实现程序，以确保用户能够正确地运行和使用该工具;

​	②**解释算法原理**：手册解释了S-AES算法的原理和工作流程，这有助于用户理解程序内部的加密和解密过程，以及如何选择适当的输入数据和密钥;

​	③**提供实现细节**：开发手册涵盖了程序的实现细节，包括程序结构、数据处理流程和用户界面设计等方面，这对于开发团队的成员来说是一个重要的参考资源，帮助他们理解和维护代码;

​	④**提供常见问题解答**：本开发手册包括了一章关于常见问题与故障排除，以帮助用户解决可能遇到的问题，这有助于提高用户的使用体验并减少潜在的困惑;

​	⑤**促进知识传递**：编写开发手册有助于知识的传递和分享，新成员可以通过手册快速了解项目，并且可以在团队内部共享和学习最佳实践;

​	⑥**支持维护与更新**：随着时间的推移，软件项目需要进行维护和更新，本开发手册可以帮助开发团队理解项目的历史和设计决策，从而更好地管理和维护代码。



## 2. 项目概述

### 2.1 项目描述

​	S-AES算法实现程序是一个用于演示和理解S-AES（Simplified Advanced Encryption Standard）加密算法的教育工具，该程序的主要目的是帮助学生、研究人员和密码学爱好者理解对称密钥加密算法的基本原理，并提供一个互动的学习平台。需要注意的是，本S-AES算法实现程序不仅完成了S-AES算法的基本功能实现，还进行了一定的扩展，主要包括对ASCⅡ编码字符串进行加密解密、对中文字符进行加密解密、双重加密、中间相遇攻击、三重加密、分组加密工作模式等功能的实现。



### 2.2 功能特性

- 加密和解密功能
- 支持16bit二进制数据、ASCⅡ编码字符串和中文字符的输入
- 支持双重加密、三重加密以及分组加密工作模式（CBC）
- 提供对已知明密文对进行中间相遇攻击的功能
- 提供图形用户界面（GUI）支持



### 2.3 技术栈

- 编程语言：Java
- 版本控制：Git
- 依赖管理：Maven



## 3. 安装与配置

### 3.1 环境要求

- 操作系统：Windows 10/11
- Java JDK：1.8及以上



### 3.2 获取源代码

- 源代码仓库：https://github.com/SealParadise/Andrade-S-AES



### 3.3 构建与编译

- 构建工具：Maven
- 编译代码：使用IntelliJ IDEA编译运行代码



## 4. 程序结构

### 4.1 目录结构

![img109](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img109.png)



### 4.2 主要组件

#### 4.2.1 数据处理方法

​	① `checkBinary` 方法：用于检测输入字符串是否只由0和1组成，如果是，则返回`true`，否则返回`false`；

​	② `ascToBin` 方法：将输入的ASCII字符串转换为对应的二进制字符串数组。如果输入字符串长度为奇数，会在末尾补充八位0；

​	③ `chnToBinStr` 方法：将输入的中文字符串转换为包含各个字符对应的二进制字符串的列表；

​	④ `binStrToChn` 方法：将输入的二进制字符串转换为对应的中文字符；

​	⑤ `plus` 方法：对两个二进制字符串按位进行异或操作，返回异或结果的二进制字符串。

```java
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
```



#### 4.2.2 S-AES加密方法

​	① `plus` 方法：对两个二进制字符串按位进行异或操作，返回异或结果的二进制字符串；

​	② `addZero` 方法：将原始二进制字符串的前面补零，使其达到指定的长度；

​	③ `gChange` 方法：执行AES算法中的g变换，用于密钥扩展。它接受输入字符串 `w` 和轮常数 `RC`，并返回扩展后的密钥；

​	④ `NS` 方法：执行AES算法中的NS（Nibble Substitution）操作，对输入字符串的每个4位进行替代；

​	⑤ `SR` 方法：执行AES算法中的SR（Shift Rows）操作，对输入字符串的四个行进行循环左移；

​	⑥ `MC` 方法：执行AES算法中的MC（Mix Columns）操作，将输入字符串的每一列进行混淆；

​	⑦ `encode` 方法：执行完整的AES加密操作。它生成轮密钥，然后对输入的原始数据进行多轮的替代、位移和混淆操作，最终返回加密后的结果。

```java
package org.aes.cipher;

public class Encoder {
    //初始化盒子、RCON
    String RC1 = "10000000",RC2 = "00110000";
    int[][] sBox = {{9,4,10,11},{13,1,8,5},{6,2,0,3},{12,14,15,7}};
    int[] imcBox = {0,4,8,12,3,7,11,15,6,2,14,10,5,1,13,9};
    /*
    AK 异或操作
     */
    public String plus(String a , String b){
        //对两个二进制字符串进行异或操作
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

    /*
    补0
     */
    public String addZero(String original,int length){
        StringBuilder originalBuilder = new StringBuilder(original);
        while (originalBuilder.length() < length){
            originalBuilder.insert(0, "0");
        }
        original = originalBuilder.toString();
        return original;
    }

    /*
    g变换
     */
    public String gChange(String w,String RC){
        String N0 = w.substring(0,4),N1 = w.substring(4,8);
        N1 = addZero(Integer.toBinaryString(sBox[Integer.parseUnsignedInt(N1.substring(0,2),2)][Integer.parseUnsignedInt(N1.substring(2,4),2)]),4);
        N0 = addZero(Integer.toBinaryString(sBox[Integer.parseUnsignedInt(N0.substring(0,2),2)][Integer.parseUnsignedInt(N0.substring(2,4),2)]),4);
        w = plus(N1 + N0,RC);
        return w;
    }

    /*
    NS操作
     */
    public String NS(String original){
        String S0 = original.substring(0,4),
                S1 = original.substring(4,8),
                S2 = original.substring(8,12),
                S3 = original.substring(12,16);
        S0 = addZero(Integer.toBinaryString(sBox[Integer.parseUnsignedInt(S0.substring(0,2),2)][Integer.parseUnsignedInt(S0.substring(2,4),2)]),4);
        S1 = addZero(Integer.toBinaryString(sBox[Integer.parseUnsignedInt(S1.substring(0,2),2)][Integer.parseUnsignedInt(S1.substring(2,4),2)]),4);
        S2 = addZero(Integer.toBinaryString(sBox[Integer.parseUnsignedInt(S2.substring(0,2),2)][Integer.parseUnsignedInt(S2.substring(2,4),2)]),4);
        S3 = addZero(Integer.toBinaryString(sBox[Integer.parseUnsignedInt(S3.substring(0,2),2)][Integer.parseUnsignedInt(S3.substring(2,4),2)]),4);
        return S0 + S1 + S2 + S3;
    }

    /*
    SR操作
     */
    public String SR(String original){
        return original.substring(0,4) + original.substring(12,16) + original.substring(8,12) + original.substring(4,8);
    }

    /*
    MC操作
     */
    public String MC(String original){
        String s00 = plus(addZero(Integer.toBinaryString(imcBox[Integer.parseUnsignedInt(original.substring(4,8),2)]),4),original.substring(0,4));
        String s10 = plus(addZero(Integer.toBinaryString(imcBox[Integer.parseUnsignedInt(original.substring(0,4),2)]),4),original.substring(4,8));
        String s01 = plus(addZero(Integer.toBinaryString(imcBox[Integer.parseUnsignedInt(original.substring(12,16),2)]),4),original.substring(8,12));
        String s11 = plus(addZero(Integer.toBinaryString(imcBox[Integer.parseUnsignedInt(original.substring(8,12),2)]),4),original.substring(12,16));
        return s00 + s10 + s01 + s11;
    }

    /*
    encode方法
     */
    public String encode(String original , String key){
        //密钥生成
        String res;
        String w0 = key.substring(0,8),w1 = key.substring(8,16);
        String w2 = plus(gChange(w1,RC1),w0);
        String w3 = plus(w1,w2);
        String w4 = plus(gChange(w3,RC2),w2);
        String w5 = plus(w3,w4);
        String w01 = w0 + w1,w23 = w2 + w3,w45 = w4 + w5;
        //AES编码
        res = plus(SR(NS(plus(MC(SR(NS(plus(original,w01)))),w23))),w45);
        return res;
    }
}
```



#### 4.2.3 S-AES解密方法

​	① `plus` 方法：对两个二进制字符串按位进行异或操作，返回异或结果的二进制字符串；

​	② `addZero` 方法：将原始二进制字符串的前面补零，使其达到指定的长度；

​	③ `gChange` 方法：执行AES算法中的g变换，用于密钥扩展。它接受输入字符串 `w` 和轮常数 `RC`，并返回扩展后的密钥；

​	④ `INS` 方法：执行AES算法中的INS（Inverse Nibble Substitution）操作，对输入字符串的每个4位进行逆替代；

​	⑤ `ISR` 方法：执行AES算法中的ISR（Inverse Shift Rows）操作，对输入字符串的四个行进行逆循环左移；

​	⑥ `IMC` 方法：执行AES算法中的IMC（Inverse Mix Columns）操作，对输入字符串的每一列进行逆混淆；

​	⑦ `decode` 方法：执行完整的AES解密操作。它生成轮密钥，然后对输入的密文进行多轮的逆替代、逆位移和逆混淆操作，最终返回解密后的原始数据。

```java
package org.aes.cipher;

public class Decoder {
    //初始化盒子、RCON
    String RC1 = "10000000",RC2 = "00110000";
    int[][] sBoxConverse = {{10,5,9,11},{1,7,8,15},{6,0,2,3},{12,4,13,14}};
    int[][] sBox = {{9,4,10,11},{13,1,8,5},{6,2,0,3},{12,14,15,7}};
    int[] imcBox2 = {0,2,4,6,8,10,12,14,3,1,7,5,11,9,15,13};
    int[] imcBox9 = {0,9,1,8,2,11,3,10,4,13,5,12,6,15,7,14};

    /*
    AK 异或操作
     */
    public String plus(String a , String b){ //异或操作
        //对两个二进制字符串进行异或操作
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

    /*
    补0
     */
    public String addZero(String original,int length){
        StringBuilder originalBuilder = new StringBuilder(original);
        while (originalBuilder.length() < length){
            originalBuilder.insert(0, "0");
        }
        original = originalBuilder.toString();
        return original;
    }

    /*
    g变换
     */
    public String gChange(String w,String RC){
        String N0 = w.substring(0,4),N1 = w.substring(4,8);
        N1 = addZero(Integer.toBinaryString(sBox[Integer.parseUnsignedInt(N1.substring(0,2),2)][Integer.parseUnsignedInt(N1.substring(2,4),2)]),4);
        N0 = addZero(Integer.toBinaryString(sBox[Integer.parseUnsignedInt(N0.substring(0,2),2)][Integer.parseUnsignedInt(N0.substring(2,4),2)]),4);
        w = plus(N1 + N0,RC);
        return w;
    }

    /*
    INS操作
     */
    public String INS(String original){
        String S0 = original.substring(0,4),
                S1 = original.substring(4,8),
                S2 = original.substring(8,12),
                S3 = original.substring(12,16);
        S0 = addZero(Integer.toBinaryString(sBoxConverse[Integer.parseUnsignedInt(S0.substring(0,2),2)][Integer.parseUnsignedInt(S0.substring(2,4),2)]),4);
        S1 = addZero(Integer.toBinaryString(sBoxConverse[Integer.parseUnsignedInt(S1.substring(0,2),2)][Integer.parseUnsignedInt(S1.substring(2,4),2)]),4);
        S2 = addZero(Integer.toBinaryString(sBoxConverse[Integer.parseUnsignedInt(S2.substring(0,2),2)][Integer.parseUnsignedInt(S2.substring(2,4),2)]),4);
        S3 = addZero(Integer.toBinaryString(sBoxConverse[Integer.parseUnsignedInt(S3.substring(0,2),2)][Integer.parseUnsignedInt(S3.substring(2,4),2)]),4);
        return S0 + S1 + S2 + S3;
    }

    /*
    ISR操作
     */
    public String ISR(String original){
        return original.substring(0,4) + original.substring(12,16) + original.substring(8,12) + original.substring(4,8);
    }

    /*
    IMC操作
     */
    public String IMC(String original){
        String s00 = plus(addZero(Integer.toBinaryString(imcBox9[Integer.parseUnsignedInt(original.substring(0,4),2)]),4),
                addZero(Integer.toBinaryString(imcBox2[Integer.parseUnsignedInt(original.substring(4,8),2)]),4));
        String s10 = plus(addZero(Integer.toBinaryString(imcBox9[Integer.parseUnsignedInt(original.substring(4,8),2)]),4),
                addZero(Integer.toBinaryString(imcBox2[Integer.parseUnsignedInt(original.substring(0,4),2)]),4));
        String s01 = plus(addZero(Integer.toBinaryString(imcBox9[Integer.parseUnsignedInt(original.substring(8,12),2)]),4),
                addZero(Integer.toBinaryString(imcBox2[Integer.parseUnsignedInt(original.substring(12,16),2)]),4));
        String s11 = plus(addZero(Integer.toBinaryString(imcBox9[Integer.parseUnsignedInt(original.substring(12,16),2)]),4),
                addZero(Integer.toBinaryString(imcBox2[Integer.parseUnsignedInt(original.substring(8,12),2)]),4));
        return s00 + s10 + s01 + s11;
    }

    /*
    decode方法
     */
    public String decode(String original, String key){
        String res;
        String w0 = key.substring(0,8),w1 = key.substring(8,16);
        String w2 = plus(gChange(w1,RC1),w0);
        String w3 = plus(w1,w2);
        String w4 = plus(gChange(w3,RC2),w2);
        String w5 = plus(w3,w4);
        String w01 = w0 + w1 , w23 = w2 + w3 , w45 = w4 + w5;
        res = plus(INS(ISR(IMC(plus(INS(ISR(plus(original,w45))),w23)))),w01);
        return res;
    }
}
```



#### 4.2.4 中间相遇攻击模块

​	① `quickSort`：快速排序方法，对整数数组进行快速排序，并保持密钥与密文下标一致。该方法用于对中间密文空间进行排序，以加速中间相遇攻击的查找过程；

​	② `binaryLookUp`：二分查找方法，对排序后的中间密文空间执行二分查找，以查找具有相同中间密文的密钥。该方法返回中间密文相同的空间的起始和结束下标；

​	③ `singleMsgAttack`：单一明密文对破解方法，该方法接受一个明文 (`original`) 和一个密文 (`secret`)，并尝试通过遍历密钥空间，对密文进行解密，并查找中间密文空间，以找到相同的中间密钥。它返回一个包含可能的密钥的列表；

​	④ `multiMsgAttack`：多明密文对破解方法，该方法接受多个明文和密文对，以及单一明密文对破解方法返回的密钥堆。它遍历给定的密钥空间，对每个密钥，遍历每个明密文对并检查是否中间密文相同。如果中间密文相同，将该密钥添加到结果列表中。

```java
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
```



#### 4.2.5 分组加密（CBC）模块

​	① 初始化编码器和解码器：类中初始化了`Encoder`和`Decoder`对象，用于进行AES算法的加密和解密操作；

​	② `cbcEncode`：CBC编码方法，该方法接受明文块列表 (`original`)、密钥 (`key`) 和初始向量 (`IV`) 作为参数。它首先将初始向量 (`IV`) 与第一个明文块异或，然后使用AES加密算法对结果进行加密，将加密结果放回原位。然后，将每个密文块与前一个密文块异或后，再进行AES加密，将加密结果转化为中文字符，并将其连接成最终的密文字符串；

​	③ `cbcDecode`：CBC解码方法，该方法接受密文块列表 (`secret`)、密钥 (`key`) 和初始向量 (`IV`) 作为参数。它首先将第一个密文块使用AES解密算法进行解密，然后与初始向量 (`IV`) 异或，将结果转化为中文字符，将其放入结果字符串 (`res`) 中。接着，对于每个后续的密文块，它将密文块使用AES解密后，与前一个密文块异或，再将结果转化为中文字符，添加到结果字符串 (`res`) 中。

```java
package org.aes.cipher;

import org.aes.commen.Common;

import java.util.List;

public class CBC {
    //初始化编码器
    Encoder encoder = new Encoder();
    //初始化解码器
    Decoder decoder = new Decoder();
    //初始化通用操作对象
    Common common = new Common();

    /*
    CBC编码方法
     */
    public String cbcEncode(List<String> original, String key, String IV){
        //将第一位和IV异或并编码 结果放回第一位
        original.set (0,encoder.encode( common.plus( original.get(0) , IV) , key));
        //将放回原位的结果转中文放入res
        StringBuilder res = new StringBuilder(common.binStrToChn(original.get(0)));
        for (int i = 1; i < original.size(); i++){
            //每位和之前一位的结果结果后编码 将结果放回原位
            original.set(i,encoder.encode( common.plus( original.get(i) , original.get(i - 1)) , key));
            //将放回原位的结果转中文放入res
            res.append(common.binStrToChn(original.get(i)));
        }
        return res.toString();
    }

    /*
    CBC解码方法
     */
    public String cbcDecode(List<String> secret, String key, String IV){
        //将第一位和IV解码后异或 结果放入res
        StringBuilder res = new StringBuilder(common.binStrToChn(common.plus(decoder.decode(secret.get(0), key), IV)));
        for (int i = 1; i < secret.size(); i++){
            //每一位解码后与前一位密文异或 之后放入res
            res.append(common.binStrToChn(common.plus(decoder.decode(secret.get(i), key), secret.get(i - 1))));
        }
        return res.toString();
    }
}
```



#### 4.2.6 JavaFX图形用户界面控制方法

​	① 使用`@FXML` 注解标记了FXML文件中的UI组件，这些组件在Controller类中被引用和操作；

​	② `binaryEncode` 方法是用于执行二进制AES编码的事件处理方法。它执行以下操作：

​		a) 检查输入的密钥和明文是否符合规范，要求密钥和明文的长度都为16位的二进制，且检查它们是否是合法的二进制字符串；

​		b) 如果输入满足规范，调用 `encoder.encode` 方法执行AES编码，并将结果显示在 `binaryEncodeRes` 文本框中；

​		c) 如果输入不符合规范，会在文本框中显示相应的错误提示信息；

​	③ `binaryDecode` 方法是用于执行二进制AES解码的事件处理方法，执行与编码操作相似的操作，但是使用 `decoder.decode` 方法执行AES解码，结果显示在 `binaryDecodeRes` 文本框中；

​	④ `ascEncode` 方法用于执行ASCII码编码。它执行以下操作：

​		a) 检查输入的密钥是否符合规范，要求密钥的长度为16位的二进制，且检查它是否是合法的二进制字符串；

​		b) 使用 `common.ascToBin` 方法将ASCII字符输入转化为16位二进制数组；

​		c) 对每组16位二进制执行AES编码，将编码结果转化为ASCII字符，并将结果显示在 `ascEncodeRes` 文本框中；

​		d) 如果输入不符合规范，会在文本框中显示相应的错误提示信息；

​	⑤ `ascDecode` 方法是用于执行ASCII码解码的事件处理方法，执行与编码操作相似的操作，但使用 `common.ascToBin` 方法将ASCII密文输入转化为16位二进制数组，并使用 `decoder.decode` 方法执行AES解码，结果显示在 `ascDecodeRes` 文本框中；

​	⑥ `chnEncode` 方法用于执行中文编码。它执行以下操作：

​		a) 检查输入的密钥是否符合规范，要求密钥的长度为16位的二进制，且检查它是否是合法的二进制字符串；

​		b) 使用 `common.chnToBinStr` 方法将中文字符输入转化为16位二进制列表；

​		c) 对每组16位二进制执行AES编码，将编码结果转化为中文字符，并将结果显示在 `chnEncodeRes` 文本框中；

​		d) 如果输入不符合规范，会在文本框中显示相应的错误提示信息；

​	⑦ `chnDecode` 方法是用于执行中文解码的事件处理方法，执行与编码操作相似的操作，但使用 `common.chnToBinStr` 方法将中文密文输入转化为16位二进制列表，并使用 `decoder.decode` 方法执行AES解码，结果显示在 `chnDecodeRes` 文本框中；

​	⑧ `doubleEncode` 方法用于执行双重AES加密。它执行以下操作：

​		a) 检查输入的密钥是否符合规范，要求密钥的长度为32位的二进制，明文长度为16位的二进制，且检查它们是否是合法的二进制字符串；

​		b) 将密钥拆分为两个16位的子密钥，分别用于两次AES加密操作；

​		c) 使用 `encoder.encode` 方法两次执行AES编码，将结果显示在 `doubleEncodeRes` 文本框中；

​		d) 如果输入不符合规范，会在文本框中显示相应的错误提示信息；

​	⑨ `doubleDecode` 方法是用于执行双重AES解密的事件处理方法，执行与加密操作相似的操作，但使用 `decoder.decode` 方法两次执行AES解码，结果显示在 `doubleDecodeRes` 文本框中；

​	⑩ `trebleEncode` 方法用于执行三重AES加密。它执行以下操作：

​		a) 检查输入的密钥是否符合规范，要求密钥的长度为32位的二进制，明文长度为16位的二进制，且检查它们是否是合法的二进制字符串；

​		b) 将密钥拆分为两个16位的子密钥，分别用于三次AES加密操作；

​		c) 使用 `encoder.encode` 方法三次执行AES编码，将结果显示在 `trebleEncodeRes` 文本框中；

​		d) 如果输入不符合规范，会在文本框中显示相应的错误提示信息；

​	⑪ `trebleDecode` 方法是用于执行三重AES解密的事件处理方法，执行与加密操作相似的操作，但使用 `decoder.decode` 方法三次执行AES解码，结果显示在 `trebleDecodeRes` 文本框中；

​	⑫ `appendMessage` 方法用于添加明密文对到中间相遇攻击模块，它执行以下操作：

​		a) 检查输入的明文和密文是否符合规范，要求明文和密文的长度都为16位的二进制，且检查它们是否是合法的二进制字符串；

​		b) 如果输入满足规范，将明文和密文添加到存储明密文对的列表，并在 `MessagePresent` 文本域中显示这些明密文对；

​		c) 如果输入不符合规范，会在文本框中显示相应的错误提示信息；

​	⑬ `MimDecode` 方法用于执行中间相遇攻击，它执行以下操作：

​		a) 如果没有存储明密文对，显示提示信息要求至少输入一对明密文对；

​		b) 如果只有一对明密文对，调用攻击模块的 `singleMsgAttack` 方法缩小可能的密钥范围，并显示前十个可能的密钥；

​		c) 如果有多对明密文对，先使用 `singleMsgAttack` 方法缩小密钥范围，然后使用 `multiMsgAttack` 方法进一步缩小可能的密钥范围，并显示前十个可能的密钥；

​		d) 结果显示在 `attackRes` 文本域中；

​	⑭ `clearMessage` 方法用于清空明密文对的输入；

​	⑮ `initialize` 方法是JavaFX的初始化方法，在界面加载后自动调用。它设置 `encodeRes` 和 `decodeRes` 文本框为不可编辑状态，以防止用户手动输入。

```java
package org.aes.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.aes.cipher.CBC;
import org.aes.cipher.Decoder;
import org.aes.cipher.Encoder;
import org.aes.cipher.MeetInTheMid;
import org.aes.commen.Common;
import java.util.ArrayList;
import java.util.List;

public class IndexController {
    //初始化通用操作对象
    Common common = new Common();
    //初始化解码器
    Decoder decoder = new Decoder();
    //初始化编码器
    Encoder encoder = new Encoder();
    //初始化中间相遇攻击对象
    MeetInTheMid attacker = new MeetInTheMid();
    //初始化CBC密码器
    CBC cbc = new CBC();
    //存储中间相遇攻击模块的密文的输入集
    private final List<String> MimSecret = new ArrayList<>();
    //存储中间相遇攻击模块的明文的输入集
    private final List<String> MimOriginal = new ArrayList<>();

    @FXML
    private TextField chnEncodeKey;

    @FXML
    private TextField MimOriginalMessage;

    @FXML
    private TextField cbcDecodeKey;

    @FXML
    private TextField chnEncodeRes;

    @FXML
    private TextField cbcDecodeRes;

    @FXML
    private TextField cbcOriginalMessage;

    @FXML
    private TextField trebleOriginalMessage;

    @FXML
    private TextField binarySecretMessage;

    @FXML
    private TextField doubleOriginalMessage;

    @FXML
    private TextArea doubleEncodeKey;

    @FXML
    private TextField doubleEncodeRes;

    @FXML
    private TextField binaryDecodeKey;

    @FXML
    private TextField trebleEncodeRes;

    @FXML
    private TextField binaryDecodeRes;

    @FXML
    private TextField cbcEncodeKey;

    @FXML
    private TextField cbcEncodeIV;

    @FXML
    private TextField doubleSecretMessage;

    @FXML
    private TextField cbcEncodeRes;

    @FXML
    private TextArea trebleEncodeKey;

    @FXML
    private TextField ascOriginalMessage;

    @FXML
    private TextField binaryEncodeRes;

    @FXML
    private TextField doubleDecodeRes;

    @FXML
    private TextField binaryEncodeKey;

    @FXML
    private TextArea doubleDecodeKey;

    @FXML
    private TextField trebleDecodeRes;

    @FXML
    private TextArea trebleDecodeKey;

    @FXML
    private TextField MimSecretMessage;

    @FXML
    private TextField binaryOriginalMessage;

    @FXML
    private TextField ascEncodeKey;

    @FXML
    private TextField ascEncodeRes;

    @FXML
    private TextArea MessagePresent;

    @FXML
    private TextArea MimKey;

    @FXML
    private TextField chnSecretMessage;

    @FXML
    private TextField chnDecodeRes;

    @FXML
    private TextField ascDecodeKey;

    @FXML
    private TextField chnDecodeKey;

    @FXML
    private TextField trebleSecretMessage;

    @FXML
    private TextField chnOriginalMessage;

    @FXML
    private TextField cbcDecodeIV;

    @FXML
    private TextField ascDecodeRes;

    @FXML
    private TextField ascSecretMessage;

    @FXML
    private TextField cbcSecretMessage;

    /*
    二进制AES编码方法
     */
    @FXML
    void binaryEncode() {
        //验证输入是否规范 要求明文、密钥输入皆为16位二进制 调用common.checkBinary验证是否为二进制
        if (binaryEncodeKey.getText().length() == 16 && binaryOriginalMessage.getText().length() == 16
                && common.checkBinary(binaryEncodeKey.getText()) && common.checkBinary(binaryOriginalMessage.getText())){
            //调用encoder的编码方法 并输出编码结果
            binaryEncodeRes.setText(encoder.encode(binaryOriginalMessage.getText(),binaryEncodeKey.getText()));
        }else{
            //提示输入不规范
            if (binaryEncodeKey.getText().length() != 16 || !common.checkBinary(binaryEncodeKey.getText())){
                binaryEncodeKey.setText("密钥内容不规范");
            }
            if (binaryOriginalMessage.getText().length() != 16 || !common.checkBinary(binaryOriginalMessage.getText())){
                binaryOriginalMessage.setText("明文内容不规范");
            }
        }
    }

    /*
    二进制AES解码方法
     */
    @FXML
    void binaryDecode() {
        //验证输入是否规范 要求密文、密钥输入皆为16位二进制 调用common.checkBinary验证是否为二进制
        if (binaryDecodeKey.getText().length() == 16 && binarySecretMessage.getText().length() == 16
        && common.checkBinary(binaryDecodeKey.getText()) && common.checkBinary(binarySecretMessage.getText())){
            //调用decoder的解码方法 并输出解码结果
            binaryDecodeRes.setText(decoder.decode(binarySecretMessage.getText(),binaryDecodeKey.getText()));
        }else {
            //提示输入不规范
            if (binaryDecodeKey.getText().length() != 16 || !common.checkBinary(binaryDecodeKey.getText())){
                binaryDecodeKey.setText("密钥内容不规范");
            }
            if (binarySecretMessage.getText().length() != 16 || !common.checkBinary(binarySecretMessage.getText())){
                binarySecretMessage.setText("密文内容不规范");
            }
        }
    }

    /*
    AscII码编码方法
     */
    @FXML
    void ascEncode() {
        //验证输入是否规范 要求密钥输入为16位二进制 调用common.checkBinary验证是否为二进制
        if (ascEncodeKey.getText().length() == 16 && common.checkBinary(ascEncodeKey.getText())){
            //调用common.sub方法 将asc输入转化成16位二进制数组
            String[] asc = common.ascToBin(ascOriginalMessage.getText());
            StringBuilder res = new StringBuilder();
            String key = ascEncodeKey.getText();
            //迭代数组 调用encoder的编码方法 将每组编码结果转化成asc码输出至变量res
            for (String origin : asc){
                res.append((char) Integer.parseInt(encoder.encode(origin, key).substring(0, 8), 2)).append((char) Integer.parseInt(encoder.encode(origin, key).substring(8, 16), 2));
            }
            //输出编码结果
            ascEncodeRes.setText(res.toString());
        }else{
            //提示输入不规范
            ascEncodeKey.setText("密钥内容不规范");
        }
    }

    /*
    AscII码解码方法
     */
    @FXML
    void ascDecode() {
        //验证输入是否规范 要求密钥输入为16位二进制 调用common.checkBinary验证是否为二进制
        if (ascDecodeKey.getText().length() == 16 && common.checkBinary(ascDecodeKey.getText())){
            //调用common.sub方法 将asc输入转化成16位二进制数组
            String[] asc = common.ascToBin(ascSecretMessage.getText());
            StringBuilder res = new StringBuilder();
            String key = ascDecodeKey.getText();
            //迭代数组，调用decoder的解码方法 将每组解码结果转化成asc码输出至变量res
            for (String origin : asc){
                res.append((char) Integer.parseInt(decoder.decode(origin, key).substring(0, 8), 2)).append((char) Integer.parseInt(decoder.decode(origin, key).substring(8, 16), 2));
            }
            //输出解码结果
            ascDecodeRes.setText(res.toString());
        }else{
            //提示输入不规范
            ascDecodeKey.setText("密钥内容不规范");
        }
    }

    /*
    中文编码方法
     */
    @FXML
    void chnEncode() {
        //验证输入是否规范 要求密钥输入为16位二进制 调用common.checkBinary验证是否为二进制
        if (chnEncodeKey.getText().length() == 16 && common.checkBinary(chnEncodeKey.getText())){
            //调用common.chnToBinStr方法 将中文输入转化成16位二进制列表
            List<String> original = common.chnToBinStr(chnOriginalMessage.getText());
            StringBuilder res = new StringBuilder();
            //迭代列表 调用encoder的编码方法 并将每组编码结果输出至变量res
            for (String ori : original){
                res.append(common.binStrToChn(encoder.encode(ori, chnEncodeKey.getText())));
            }
            //输出编码结果
            chnEncodeRes.setText(res.toString());
        }else{
            //提示输入不规范
            chnEncodeKey.setText("密钥内容不规范");
        }
    }

    /*
    中文解码方法
     */
    @FXML
    void chnDecode() {
        //验证输入是否规范 要求密钥输入为16位二进制 调用common.checkBinary验证是否为二进制
        if (chnDecodeKey.getText().length() == 16 && common.checkBinary(chnEncodeKey.getText())){
            //调用common.chnToBinStr方法 将中文输入转化成16位二进制列表
            List<String> secret = common.chnToBinStr(chnSecretMessage.getText());
            StringBuilder res = new StringBuilder();
            //迭代列表 调用decoder的解码方法 并将每组解码结果输出至变量res
            for (String sec : secret){
                res.append(common.binStrToChn(decoder.decode(sec, chnDecodeKey.getText())));
            }
            //输出解码结果
            chnDecodeRes.setText(res.toString());
        }else {
            //提示输入不规范
            chnDecodeKey.setText("密钥内容不规范");
        }
    }

    /*
    双重AES加密方法
     */
    @FXML
    void doubleEncode() {
        //验证输入是否规范 要求明文输入为16位二进制 密钥输入为32位二进制 调用common.checkBinary验证是否为二进制
        if (doubleEncodeKey.getText().length() == 32 && doubleOriginalMessage.getText().length() == 16
                && common.checkBinary(doubleEncodeKey.getText()) && common.checkBinary(doubleOriginalMessage.getText())){
            //迭代encoder的编码方法两次 输出编码结果
            String middle = encoder.encode(doubleOriginalMessage.getText(),doubleEncodeKey.getText().substring(0,16));
            doubleEncodeRes.setText(encoder.encode(middle,doubleEncodeKey.getText().substring(16,32)));
        }else{
            //提示输入不规范
            if (doubleEncodeKey.getText().length() != 32 || !common.checkBinary(doubleEncodeKey.getText())){
                doubleEncodeKey.setText("密钥内容不规范");
            }
            if (doubleOriginalMessage.getText().length() != 16 || !common.checkBinary(doubleOriginalMessage.getText())){
                doubleOriginalMessage.setText("明文内容不规范");
            }
        }
    }

    /*
    双重AES解密方法
     */
    @FXML
    void doubleDecode() {
        //验证输入是否规范 要求明文输入为16位二进制 密钥输入为32位二进制 调用common.checkBinary验证是否为二进制
        if (doubleDecodeKey.getText().length() == 32 && doubleSecretMessage.getText().length() == 16
                && common.checkBinary(doubleDecodeKey.getText()) && common.checkBinary(doubleSecretMessage.getText())){
            //迭代decoder的解码方法两次 输出解码结果
            String middle = decoder.decode(doubleSecretMessage.getText(),doubleDecodeKey.getText().substring(16,32));
            doubleDecodeRes.setText(decoder.decode(middle,doubleDecodeKey.getText().substring(0,16)));
        }else {
            //提示输入不规范
            if (doubleDecodeKey.getText().length() != 16 || !common.checkBinary(doubleDecodeKey.getText())){
                doubleDecodeKey.setText("密钥内容不规范");
            }
            if (doubleSecretMessage.getText().length() != 16 || !common.checkBinary(doubleSecretMessage.getText())){
                doubleSecretMessage.setText("密文内容不规范");
            }
        }
    }
    /*
    三重AES加密方法
     */
    @FXML
    void trebleEncode() {
        //验证输入是否规范 要求明文输入为16位二进制 密钥输入为32位二进制 调用common.checkBinary验证是否为二进制
        if (trebleEncodeKey.getText().length() == 32 && trebleOriginalMessage.getText().length() == 16
                && common.checkBinary(trebleEncodeKey.getText()) && common.checkBinary(trebleOriginalMessage.getText())){
            //迭代encoder的编码方法三次 输出编码结果
            String middle = encoder.encode(trebleOriginalMessage.getText(),trebleEncodeKey.getText().substring(0,16));
            middle = encoder.encode(middle,trebleEncodeKey.getText().substring(16,32));
            trebleEncodeRes.setText(encoder.encode(middle,trebleEncodeKey.getText().substring(0,16)));
        }else{
            //提示输入不规范
            if (trebleEncodeKey.getText().length() != 32 || !common.checkBinary(trebleEncodeKey.getText())){
                trebleEncodeKey.setText("密钥内容不规范");
            }
            if (trebleOriginalMessage.getText().length() != 16 || !common.checkBinary(trebleOriginalMessage.getText())){
                trebleOriginalMessage.setText("明文内容不规范");
            }
        }
    }

    /*
    三重AES解码方法
     */
    @FXML
    void trebleDecode() {
        //验证输入是否规范 要求明文输入为16位二进制 密钥输入为32位二进制 调用common.checkBinary验证是否为二进制
        if (trebleDecodeKey.getText().length() == 32 && trebleSecretMessage.getText().length() == 16
                && common.checkBinary(trebleDecodeKey.getText()) && common.checkBinary(trebleSecretMessage.getText())){
            //迭代decoder的解码方法三次 输出解码结果
            String middle = decoder.decode(trebleSecretMessage.getText(),trebleDecodeKey.getText().substring(0,16));
            middle = decoder.decode(middle,trebleDecodeKey.getText().substring(16,32));
            trebleDecodeRes.setText(decoder.decode(middle,trebleDecodeKey.getText().substring(0,16)));
        }else {
            //提示输入不规范
            if (trebleDecodeKey.getText().length() != 16 || !common.checkBinary(trebleDecodeKey.getText())){
                trebleDecodeKey.setText("密钥内容不规范");
            }
            if (trebleSecretMessage.getText().length() != 16 || !common.checkBinary(trebleSecretMessage.getText())){
                trebleSecretMessage.setText("密文内容不规范");
            }
        }
    }

    /*
    * 中间相遇攻击模块
    * 添加明密文对方法
     */
    @FXML
    void appendMessage() {
        //验证输入是否规范 要求明文、密文输入为16位二进制 调用common.checkBinary验证是否为二进制
        if (MimOriginalMessage.getText().length() == 16 && MimSecretMessage.getText().length() == 16
                && common.checkBinary(MimOriginalMessage.getText()) && common.checkBinary(MimSecretMessage.getText())){
            //展示已经存储的明密文对
            MessagePresent.appendText("明文：" + MimOriginalMessage.getText() + "\n");
            MessagePresent.appendText("密文：" + MimSecretMessage.getText() + "\n\n");
            //将输入的明密文对存储至输入集
            MimOriginal.add(MimOriginalMessage.getText());
            MimSecret.add(MimSecretMessage.getText());
        }else{
            //提示输入不规范
            if (MimSecretMessage.getText().length() != 16 || !common.checkBinary(MimSecretMessage.getText())){
                MimSecretMessage.setText("密文内容不规范");
            }
            if (MimOriginalMessage.getText().length() != 16 || !common.checkBinary(MimOriginalMessage.getText())){
                MimOriginalMessage.setText("明文内容不规范");
            }
        }
    }

    /*
    * 中间相遇攻击模块
    * 密钥获取方法
     */
    @FXML
    void MimDecode() {
        if (MessagePresent.getText().isEmpty()) {
            //检测输入 输入框为空返回提示
            MimKey.clear();
            MimKey.appendText("请至少输入一对明密文对");
        } else if (MimOriginal.size() == 1) {
            //检测输入 输入框只有一对输入时 调用attacker.singleMsgAttacker方法 将结果输出至keyArray
            String iniOri = MimOriginal.get(0), iniSec = MimSecret.get(0);
            List<String> keyArray = attacker.singleMsgAttack(iniOri, iniSec);
            //单对明密文对的密钥数量必然庞大，只展示十条密钥
            MimKey.clear();
            MimKey.appendText("密钥数量过于庞大，只展示以下十条\n");
            for (int i = 0; i < 10; i++) {
                MimKey.appendText(keyArray.get(i) + "\n");
            }
        } else {
            //检测输入 输入框有多对明密文对时 先调用attacker.singleMsgAttacker缩小密钥范围
            String iniOri = MimOriginal.get(0), iniSec = MimSecret.get(0);
            List<String> keyArray = attacker.singleMsgAttack(iniOri, iniSec);
            //使用剩余的明密文对集合 中间相遇攻击得到的密钥集 调用attacker.multiMsgAttack方法筛选准确密钥
            keyArray = attacker.multiMsgAttack(MimOriginal, MimSecret, keyArray);
            //输出密钥集合
            if (keyArray.isEmpty()) {
                //不存在明密文集合所对应的密钥的情况
                MimKey.clear();
                MimKey.appendText("该组明密文对不存在可行密钥");
            } else if (keyArray.size() > 10) {
                //密钥集合过大的情况
                MimKey.clear();
                MimKey.appendText("可能的密钥数量过于庞大，只展示以下十条\n");
                for (int i = 0; i < 10; i++) {
                    MimKey.appendText(keyArray.get(i) + "\n");
                }
            } else {
                MimKey.clear();
                MimKey.appendText("密钥\n");
                //正常输出情况
                for (String key : keyArray) {
                    MimKey.appendText(key + "\n");
                }
            }
        }
    }

    /*
    * 中间相遇攻击模块
    * 清空输入明密文对方法
     */
    @FXML
    void clearMessage() {
        //清空展示框
        MessagePresent.clear();
        //清空存储输入集
        MimSecret.clear();
        MimOriginal.clear();
    }

    /*
    CBC编码方法
     */
    @FXML
    void cbcEncode() {
        //验证输入是否规范 要求密钥 初始向量输入为16位二进制 调用common.checkBinary验证是否为二进制
        if (cbcEncodeKey.getText().length() == 16 && cbcEncodeIV.getText().length() == 16
                && common.checkBinary(cbcEncodeKey.getText()) && common.checkBinary(cbcEncodeIV.getText())){
            //调用common.chnToBinStr方法将中文字符转为二进制列表 调用cbc.cbcEncode方法进行编码 输出编码结果
            cbcEncodeRes.setText(cbc.cbcEncode( common.chnToBinStr( cbcOriginalMessage.getText() ) , cbcEncodeKey.getText() , cbcEncodeIV.getText()));
        }else{
            //提示输入不规范
            if (cbcEncodeKey.getText().length() != 16 || !common.checkBinary(cbcEncodeKey.getText())){
                cbcEncodeKey.setText("密钥内容不规范");
            }
            if (cbcEncodeIV.getText().length() != 16 || !common.checkBinary(cbcEncodeIV.getText())){
                cbcEncodeIV.setText("初始向量不规范");
            }
        }
    }

    /*
    CBC解码方法
     */
    @FXML
    void cbcDecode() {
        //验证输入是否规范 要求密钥 初始向量输入为16位二进制 调用common.checkBinary验证是否为二进制
        if (cbcDecodeKey.getText().length() == 16 && cbcDecodeIV.getText().length() == 16
                && common.checkBinary(cbcDecodeKey.getText()) && common.checkBinary(cbcDecodeIV.getText())){
            //调用common.chnToBinStr方法将中文字符转为二进制列表 调用cbc.cbcDecode方法进行解码 输出解码结果
            cbcDecodeRes.setText(cbc.cbcDecode( common.chnToBinStr( cbcSecretMessage.getText() ) , cbcDecodeKey.getText() , cbcDecodeIV.getText()));
        }else{
            //提示输入不规范
            if (cbcDecodeKey.getText().length() != 16 || !common.checkBinary(cbcDecodeKey.getText())){
                cbcDecodeKey.setText("密钥内容不规范");
            }
            if (cbcDecodeIV.getText().length() != 16 || !common.checkBinary(cbcDecodeIV.getText())){
                cbcDecodeIV.setText("初始向量不规范");
            }
        }
    }

    /*
    GUI初始化
     */
    @FXML
    void initialize() {
        binaryDecodeRes.setEditable(false);
        binaryEncodeRes.setEditable(false);
        ascDecodeRes.setEditable(false);
        ascEncodeRes.setEditable(false);
        doubleDecodeRes.setEditable(false);
        doubleEncodeRes.setEditable(false);
        trebleDecodeRes.setEditable(false);
        trebleEncodeRes.setEditable(false);
        MessagePresent.setEditable(false);
        MimKey.setEditable(false);
    }
}
```



#### 4.2.7 程序启动方法

​	① `Main` 类是JavaFX应用程序的入口类，它继承自`Application`类;

​	② `public static void main(String[] args)` 方法是应用程序的入口点，它调用`launch`方法启动JavaFX应用程序;

​	③ `start(Stage primaryStage)` 方法是JavaFX应用程序的主要入口点，它执行以下操作：

​		a) 通过`FXMLLoader`加载名为"index.fxml"的FXML文件，用于定义应用程序的用户界面;

​		b) 使用`FXMLLoader`的`load`方法加载FXML文件中的UI布局，并将其存储在`Parent`类型的`root`变量中;

​		c) 通过`mainLoader.getController()`获取与FXML文件相关联的`IndexController`对象，这个对象可以用于操作UI组件和处理用户交互;

​		d) 设置主窗口(`Stage`)的标题为"S-AES";

​		e) 创建一个新的`Scene`，将UI布局(`root`)作为参数，然后将该`Scene`设置为主窗口的场景;

​		f) 设置主窗口为不可调整大小;

​		g) 最后，通过`primaryStage.show()`方法显示主窗口，启动JavaFX应用程序。

```java
package org.aes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.aes.controller.IndexController;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {// javafx + fxml 获取ui
        //加载fxml文件
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/fxml/index.fxml"));
        //加载父结点
        Parent root = mainLoader.load();
        //获取Controller
        IndexController indexController = mainLoader.getController();
        //设置Stage属性
        primaryStage.setTitle("S-AES");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        //显示Stage
        primaryStage.show();
    }
}
```



#### 4.2.8 JavaFX应用程序的启动引导方法

​	若不能直接通过Main.java启动本程序，可以通过该方法进行启动：

​	① `JavaFXBootstrap` 类包含了 `main` 方法，是一个独立的 Java 类；

​	② `main` 方法调用了 `Main.main(args)`，其中 `Main` 是另一个类，通常用作 JavaFX 应用程序的入口点；

​	③ 通过这段代码，`JavaFXBootstrap` 类实际上是将控制权传递给了 `Main` 类，从而启动了 JavaFX 应用程序。

```java
import org.aes.Main;

public class JavaFXBootstrap {
    public static void main(String[] args) {
        Main.main(args);
    }
}
```



## 5. 实现细节

### 5.1 S-AES算法原理

#### 5.1.1 总览

![img110](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img110.png)



#### 5.1.2 标准设定

​	① 分组长度：16-bit；

​	② 密钥长度：16-bit；



#### 5.1.3 算法概述

​	① 加密算法

​		我们可以简单地将加密算法表示为一个复合函数：


$$
A_{K_2} \circ SR \circ NS \circ A_{K_1} \circ MC \circ SR \circ NS \circ A_{K_0}
$$


​	② 解密算法

​		本质上是加密的逆：


$$
A_{K_0} \circ INS \circ ISR \circ IMC \circ A_{K_1} \circ INS \circ ISR \circ A_{K_2}
$$


​	③ S-AES加密轮

![img112](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img112.png)



#### 5.1.4 函数细节

​	① 密钥加（A<sub>K</sub>）

​		密钥加函数将16位状态矩阵与16位轮密钥逐位异或：

![img121](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img121.png)



​		例如：

![img113](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img113.png)

​		由于异或运算是其本身的逆运算，因此密钥加函数的逆函数与密钥加函数相同。



​	② 半字节代替（NS）

​		半字节代替函数是一个简单的查表操作：

![img114](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img114.png)



​		AES定义一个4x4 的半字节值阵，称为S盒：

![img115](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img115.png)



​		其中包含所有4位值的排列。状态中的每个半字节都按以下方式映射到一个新的半字节：

​		半字节最左侧的2位用作行值，最右侧的2位用作列值；

​		这些行和列的值用作S盒中选择唯一的4位输出值的索引；

​		例如，十六进制值A代表S盒中第2行、第2列的值0，因此值A被映射为值0。

​		下面是一个半字节代替的例子：

![img116](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img116.png)



​		半字节代替的逆函数（INS）用以下的逆S盒表示：

![img117](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img117.png)



​	③ 行位移（SR）

​		行位移函数在状态的第二行执行一个半子节循环移位，第一行不变：

![img119](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img119.png)



​		例如：

![img118](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img118.png)



​		由于逆行位移（ISR）函数将倒数第二行移回到原来的位置，因此逆行位移函数与行位移函数相同。



​	④ 列混淆（MC）

​		列混淆函数在各列上执行，列中的每个半子节映射到一个新的值，其中新值是该列中两个半子节的函数：

![img120](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img120.png)



​		列混淆函数定义为：

![img122](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img122.png)



​		逆列混淆（IMC）函数定义为：

![img123](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img123.png)



​		其中，算数运算是在GF(2<sup>4</sup>)上执行的：

![img124](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img124.png)



![img125](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img125.png)



#### 5.1.5 密钥扩展

![img126](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img126.png)



### 5.2 界面设计

#### 5.2.1 用户交互界面

​	① 文件声明和导入部分：

​		a) 使用XML声明指定文档版本和编码；

​		b) 导入了Java和JavaFX相关的类库；

​	② 加密模块选项卡:

​		a) 包含一个 `<Tab>`，标签文本为 "加密模块"；

​		b) 在选项卡内容中，包含一个 `<Pane>` 容器，用于设置布局和样式；

​		c) 包含两个文本字段 `<TextField>` 用于输入明文和密文，分别由 `fx:id` 为 "binaryOriginalMessage" 和 "binaryEncodeKey" 的字段表示；

​		d) 包含一个 "加密" 按钮 `<Button>`，点击时触发 `#binaryEncode` 事件；

​		e) 包含多个标签 `<Label>` 用于标识文本字段的用途，如明文、密钥和密文；

​		f) 显示 "二进制加密" 标题；

​		g) 包含一个 `<TextArea>` 用于输入密钥，由 `fx:id` 为 "binaryEncodeRes" 的字段表示；

​	③ 解密模块选项卡:

​		a) 包含一个 `<Tab>`，标签文本为 "解密模块"；

​		b) 在选项卡内容中，包含一个 `<Pane>` 容器，用于设置布局和样式；

​		c) 包含两个文本字段 `<TextField>` 用于输入明文和密文，分别由 `fx:id` 为 "ascOriginalMessage" 和 "ascEncodeKey" 的字段表示；

​		d) 包含一个 "解密" 按钮 `<Button>`，点击时触发 `#ascDecode` 事件；

​		e) 包含多个标签 `<Label>` 用于标识文本字段的用途，如明文、密钥和密文；

​		f) 显示 "ASCII解密" 标题；

​		g) 包含一个 `<TextArea>` 用于显示解密结果，由 `fx:id` 为 "ascDecodeRes" 的字段表示；

​	④ 中文模块选项卡:

​		a) 包含一个 `<Tab>`，标签文本为 "中文模块"；

​		b) 在选项卡内容中，包含一个 `<Pane>` 容器，用于设置布局和样式；

​		c) 包含两个文本字段 `<TextField>` 用于输入明文和密文，分别由 `fx:id` 为 "chnOriginalMessage" 和 "chnEncodeKey" 的字段表示；

​		d) 包含一个 "加密" 按钮 `<Button>`，点击时触发 `#chnEncode` 事件；

​		e) 包含多个标签 `<Label>` 用于标识文本字段的用途，如明文、密钥和密文；

​		f) 显示 "中文加密" 标题；

​		g) 包含一个 `<TextArea>` 用于显示加密结果，由 `fx:id` 为 "chnEncodeRes" 的字段表示；

​	⑤ 双重加密标签页:

​		a) 包含一个 `<Pane>` 容器，用于设置布局和样式；

​		b) 包含两个文本字段（`<TextField>`) 用于输入明文和密文；

​		c) 包含一个 "加密" 按钮，点击时触发 `#doubleEncode` 事件；

​		d) 包含多个标签 (`<Label>`) 用于标识文本字段的用途；

​		e) 显示 "双重加密" 和 "双重解密" 两个标题；

​		f) 包含一个 `<TextArea>` 用于输入密钥；

​	⑥ 中间相遇攻击标签页:

​		a) 包含一个 `<Pane>` 容器，用于设置布局和样式；

​		b) 包含两个文本字段用于输入明文和密文；

​		c)包含 "添加"、"破解" 和 "清空" 三个按钮，分别触发不同的事件；

​		d) 包含两个 `<TextArea>` 用于显示信息；

​		e) 包含多个标签用于标识不同的元素；

​	⑦ 三重加密标签页:

​		与双重加密标签页相似，包含一个 `<Pane>` 容器，多个文本字段、按钮、标签和标题。这些元素用于三重加密和解密；

​	⑧ 分组加密标签页:

​		a) 与前两个标签页相似，包含一个 `<Pane>` 容器，多个文本字段、按钮、标签和标题。这些元素用于分组加密和解密;

​		b) 此外，这个标签页还包含两个额外的文本字段用于输入初始向量。

```xml
<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.scene.text.*?>
<?import javafx.scene.control.*?>
<?import javafx.scene.layout.*?>

<AnchorPane prefHeight="400.0" prefWidth="600.0" xmlns="http://javafx.com/javafx/8" xmlns:fx="http://javafx.com/fxml/1" fx:controller="org.aes.controller.IndexController">
   <children>
      <TabPane prefHeight="449.0" prefWidth="821.0" tabClosingPolicy="UNAVAILABLE">
        <tabs>
          <Tab text="加密模块">
               <content>
                  <Pane id="encodePane" prefHeight="200.0" prefWidth="200.0" stylesheets="@../CSS/indexCSS.css">
                     <children>
                        <TextField fx:id="binaryOriginalMessage" layoutX="128.0" layoutY="122.0" prefHeight="32.0" prefWidth="184.0">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                        <TextField fx:id="binaryEncodeKey" layoutX="128.0" layoutY="195.0" prefHeight="32.0" prefWidth="184.0">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                        <Button layoutX="177.0" layoutY="320.0" mnemonicParsing="false" onAction="#binaryEncode" prefHeight="34.0" prefWidth="84.0" text="加密">
                           <font>
                              <Font size="20.0" />
                           </font>
                        </Button>
                        <Label layoutX="70.0" layoutY="121.0" prefHeight="34.0" prefWidth="58.0" text="明文：">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <Label layoutX="70.0" layoutY="193.0" prefHeight="34.0" prefWidth="59.0" text="密钥：">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <Label layoutX="168.0" layoutY="43.0" prefHeight="41.0" prefWidth="103.0" text="二进制加密">
                           <font>
                              <Font size="20.0" />
                           </font>
                        </Label>
                        <Separator layoutX="396.0" orientation="VERTICAL" prefHeight="421.0" prefWidth="0.0" />
                        <Label layoutX="574.0" layoutY="43.0" prefHeight="41.0" prefWidth="103.0" text="ASCII加密">
                           <font>
                              <Font size="20.0" />
                           </font>
                        </Label>
                        <Label layoutX="476.0" layoutY="121.0" prefHeight="34.0" prefWidth="58.0" text="明文：">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <TextField fx:id="ascOriginalMessage" layoutX="534.0" layoutY="122.0" prefHeight="32.0" prefWidth="184.0">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                        <TextField fx:id="ascEncodeKey" layoutX="534.0" layoutY="195.0" prefHeight="32.0" prefWidth="184.0">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                        <Label layoutX="476.0" layoutY="193.0" prefHeight="34.0" prefWidth="59.0" text="密钥：">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <Button layoutX="584.0" layoutY="320.0" mnemonicParsing="false" onAction="#ascEncode" prefHeight="34.0" prefWidth="84.0" text="加密">
                           <font>
                              <Font size="20.0" />
                           </font>
                        </Button>
                        <TextField fx:id="binaryEncodeRes" layoutX="128.0" layoutY="263.0" prefHeight="32.0" prefWidth="184.0" style="-fx-background-color: silver;">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                        <TextField fx:id="ascEncodeRes" layoutX="534.0" layoutY="263.0" prefHeight="32.0" prefWidth="184.0" style="-fx-background-color: silver;">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                        <Label layoutX="476.0" layoutY="262.0" prefHeight="34.0" prefWidth="59.0" text="密文：">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <Label layoutX="70.0" layoutY="262.0" prefHeight="34.0" prefWidth="59.0" text="密文：">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                     </children>
                  </Pane>
               </content>
            </Tab>
          <Tab text="解密模块">
               <content>
                  <Pane id="decodePane" prefHeight="200.0" prefWidth="200.0" stylesheets="@../CSS/indexCSS.css">
                     <children>
                        <Button layoutX="584.0" layoutY="320.0" mnemonicParsing="false" onAction="#ascDecode" prefHeight="34.0" prefWidth="84.0" style="-fx-background-color: black; -fx-border-color: white;" text="解密" textFill="WHITE">
                           <font>
                              <Font size="20.0" />
                           </font>
                        </Button>
                        <Label layoutX="574.0" layoutY="43.0" prefHeight="41.0" prefWidth="103.0" text="ASCII解密" textFill="WHITE">
                           <font>
                              <Font size="20.0" />
                           </font>
                        </Label>
                        <Label layoutX="476.0" layoutY="262.0" prefHeight="34.0" prefWidth="59.0" text="明文：" textFill="WHITE">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <Label layoutX="70.0" layoutY="193.0" prefHeight="34.0" prefWidth="59.0" text="密钥：" textFill="WHITE">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <Label layoutX="168.0" layoutY="43.0" prefHeight="41.0" prefWidth="103.0" text="二进制解密" textFill="WHITE">
                           <font>
                              <Font size="20.0" />
                           </font>
                        </Label>
                        <Separator layoutX="396.0" orientation="VERTICAL" prefHeight="421.0" prefWidth="0.0" />
                        <Label layoutX="476.0" layoutY="121.0" prefHeight="34.0" prefWidth="58.0" text="密文：" textFill="WHITE">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <TextField fx:id="ascDecodeKey" layoutX="534.0" layoutY="194.0" prefHeight="32.0" prefWidth="184.0" style="-fx-background-color: black; -fx-border-color: white;" styleClass="decodeInput">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                        <Label layoutX="70.0" layoutY="262.0" prefHeight="34.0" prefWidth="59.0" text="明文：" textFill="WHITE">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <TextField fx:id="binaryDecodeRes" layoutX="128.0" layoutY="263.0" prefHeight="32.0" prefWidth="184.0" style="-fx-background-color: black; -fx-border-color: gray;" styleClass="decodeOutput">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                        <TextField fx:id="binarySecretMessage" layoutX="128.0" layoutY="122.0" prefHeight="32.0" prefWidth="184.0" style="-fx-background-color: black; -fx-border-color: white;" styleClass="decodeInput">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                        <Label layoutX="70.0" layoutY="121.0" prefHeight="34.0" prefWidth="58.0" text="密文：" textFill="WHITE">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <Label layoutX="476.0" layoutY="193.0" prefHeight="34.0" prefWidth="59.0" text="密钥：" textFill="WHITE">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <TextField fx:id="ascDecodeRes" layoutX="534.0" layoutY="263.0" prefHeight="32.0" prefWidth="184.0" style="-fx-background-color: black; -fx-border-color: gray;" styleClass="decodeOutput">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                        <Button layoutX="177.0" layoutY="320.0" mnemonicParsing="false" onAction="#binaryDecode" prefHeight="34.0" prefWidth="84.0" style="-fx-background-color: black; -fx-border-color: white;" text="解密" textFill="WHITE">
                           <font>
                              <Font size="20.0" />
                           </font>
                        </Button>
                        <TextField fx:id="binaryDecodeKey" layoutX="128.0" layoutY="194.0" prefHeight="32.0" prefWidth="184.0" style="-fx-background-color: black; -fx-border-color: white;" styleClass="decodeInput">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                        <TextField fx:id="ascSecretMessage" layoutX="534.0" layoutY="122.0" prefHeight="32.0" prefWidth="184.0" style="-fx-background-color: black; -fx-border-color: white;" styleClass="decodeInput">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                     </children>
                  </Pane>
               </content>
            </Tab>
            <Tab text="中文模块">
               <content>
                  <Pane id="multiPane" prefHeight="200.0" prefWidth="200.0" stylesheets="@../CSS/multiPaneCSS.css">
                     <children>
                        <TextField fx:id="chnOriginalMessage" layoutX="128.0" layoutY="122.0" prefHeight="32.0" prefWidth="184.0">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                        <Button layoutX="177.0" layoutY="320.0" mnemonicParsing="false" onAction="#chnEncode" prefHeight="34.0" prefWidth="84.0" text="加密">
                           <font>
                              <Font size="20.0" />
                           </font>
                        </Button>
                        <Label layoutX="70.0" layoutY="121.0" prefHeight="34.0" prefWidth="58.0" text="明文：">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <Label layoutX="70.0" layoutY="193.0" prefHeight="34.0" prefWidth="59.0" text="密钥：">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <Label layoutX="178.0" layoutY="43.0" prefHeight="41.0" prefWidth="85.0" text="中文加密">
                           <font>
                              <Font size="20.0" />
                           </font>
                        </Label>
                        <Label layoutX="584.0" layoutY="43.0" prefHeight="41.0" prefWidth="84.0" text="中文解密" textFill="WHITE">
                           <font>
                              <Font size="20.0" />
                           </font>
                        </Label>
                        <Label layoutX="476.0" layoutY="121.0" prefHeight="34.0" prefWidth="58.0" text="密文：" textFill="WHITE">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <TextField id="decodeSecretMessage" fx:id="chnSecretMessage" layoutX="534.0" layoutY="122.0" prefHeight="32.0" prefWidth="184.0" style="-fx-background-color: black; -fx-border-color: white;">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                        <Label layoutX="476.0" layoutY="193.0" prefHeight="34.0" prefWidth="59.0" text="密钥：" textFill="WHITE">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <Button layoutX="584.0" layoutY="320.0" mnemonicParsing="false" onAction="#chnDecode" prefHeight="34.0" prefWidth="84.0" style="-fx-background-color: black; -fx-border-color: white;" text="解密" textFill="WHITE">
                           <font>
                              <Font size="20.0" />
                           </font>
                        </Button>
                        <TextField fx:id="chnEncodeRes" layoutX="128.0" layoutY="263.0" prefHeight="32.0" prefWidth="184.0" style="-fx-background-color: silver;">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                        <Label layoutX="476.0" layoutY="262.0" prefHeight="34.0" prefWidth="59.0" text="明文：" textFill="WHITE">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <Label layoutX="70.0" layoutY="262.0" prefHeight="34.0" prefWidth="59.0" text="密文：">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <TextField fx:id="chnDecodeRes" layoutX="534.0" layoutY="263.0" prefHeight="32.0" prefWidth="184.0" style="-fx-background-color: black; -fx-border-color: gray;" styleClass="decodeOutput">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                        <TextField fx:id="chnEncodeKey" layoutX="129.0" layoutY="194.0" prefHeight="32.0" prefWidth="184.0">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                        <TextField id="decodeSecretMessage" fx:id="chnDecodeKey" layoutX="534.0" layoutY="194.0" prefHeight="32.0" prefWidth="184.0" style="-fx-background-color: black; -fx-border-color: white;">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                     </children>
                  </Pane>
               </content>
            </Tab>
            <Tab text="双重加密">
               <content>
                  <Pane id="multiPane" prefHeight="200.0" prefWidth="200.0" stylesheets="@../CSS/multiPaneCSS.css">
                     <children>
                        <TextField fx:id="doubleOriginalMessage" layoutX="128.0" layoutY="122.0" prefHeight="32.0" prefWidth="184.0">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                        <Button layoutX="177.0" layoutY="320.0" mnemonicParsing="false" onAction="#doubleEncode" prefHeight="34.0" prefWidth="84.0" text="加密">
                           <font>
                              <Font size="20.0" />
                           </font>
                        </Button>
                        <Label layoutX="70.0" layoutY="121.0" prefHeight="34.0" prefWidth="58.0" text="明文：">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <Label layoutX="70.0" layoutY="193.0" prefHeight="34.0" prefWidth="59.0" text="密钥：">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <Label layoutX="178.0" layoutY="43.0" prefHeight="41.0" prefWidth="85.0" text="双重加密">
                           <font>
                              <Font size="20.0" />
                           </font>
                        </Label>
                        <Label layoutX="584.0" layoutY="43.0" prefHeight="41.0" prefWidth="84.0" text="双重解密" textFill="WHITE">
                           <font>
                              <Font size="20.0" />
                           </font>
                        </Label>
                        <Label layoutX="476.0" layoutY="121.0" prefHeight="34.0" prefWidth="58.0" text="密文：" textFill="WHITE">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <TextField id="decodeSecretMessage" fx:id="doubleSecretMessage" layoutX="534.0" layoutY="122.0" prefHeight="32.0" prefWidth="184.0" style="-fx-background-color: black; -fx-border-color: white;">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                        <Label layoutX="476.0" layoutY="193.0" prefHeight="34.0" prefWidth="59.0" text="密钥：" textFill="WHITE">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <Button layoutX="584.0" layoutY="320.0" mnemonicParsing="false" onAction="#doubleDecode" prefHeight="34.0" prefWidth="84.0" style="-fx-background-color: black; -fx-border-color: white;" text="解密" textFill="WHITE">
                           <font>
                              <Font size="20.0" />
                           </font>
                        </Button>
                        <TextField fx:id="doubleEncodeRes" layoutX="128.0" layoutY="263.0" prefHeight="32.0" prefWidth="184.0" style="-fx-background-color: silver;">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                        <Label layoutX="476.0" layoutY="262.0" prefHeight="34.0" prefWidth="59.0" text="明文：" textFill="WHITE">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <Label layoutX="70.0" layoutY="262.0" prefHeight="34.0" prefWidth="59.0" text="密文：">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <TextArea fx:id="doubleEncodeKey" layoutX="129.0" layoutY="177.0" prefHeight="67.0" prefWidth="183.0">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextArea>
                        <TextArea id="decodeKeyArea" fx:id="doubleDecodeKey" layoutX="535.0" layoutY="177.0" prefHeight="67.0" prefWidth="183.0" style="-fx-border-color: white;">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextArea>
                        <TextField fx:id="doubleDecodeRes" layoutX="534.0" layoutY="263.0" prefHeight="32.0" prefWidth="184.0" style="-fx-background-color: black; -fx-border-color: gray;" styleClass="decodeOutput">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                     </children>
                  </Pane>
               </content>
            </Tab>
            <Tab text="中间相遇攻击">
               <content>
                  <Pane id="mimPane" prefHeight="200.0" prefWidth="200.0" stylesheets="@../CSS/mimPane.css">
                     <children>
                        <TextField fx:id="MimOriginalMessage" layoutX="97.0" layoutY="149.0">
                           <font>
                              <Font size="15.0" />
                           </font></TextField>
                        <TextField fx:id="MimSecretMessage" layoutX="97.0" layoutY="231.0">
                           <font>
                              <Font size="15.0" />
                           </font></TextField>
                        <Button layoutX="164.0" layoutY="293.0" mnemonicParsing="false" onAction="#appendMessage" text="添加">
                           <font>
                              <Font size="20.0" />
                           </font></Button>
                        <Button layoutX="620.0" layoutY="178.0" mnemonicParsing="false" onAction="#MimDecode" text="破解">
                           <font>
                              <Font size="20.0" />
                           </font></Button>
                        <Button layoutX="449.0" layoutY="178.0" mnemonicParsing="false" onAction="#clearMessage" text="清空">
                           <font>
                              <Font size="20.0" />
                           </font></Button>
                        <TextArea fx:id="MessagePresent" layoutX="411.0" layoutY="43.0" prefHeight="111.0" prefWidth="315.0" styleClass="textArea">
                           <font>
                              <Font size="15.0" />
                           </font></TextArea>
                        <TextArea fx:id="MimKey" layoutX="412.0" layoutY="250.0" prefHeight="126.0" prefWidth="314.0" styleClass="textArea">
                           <font>
                              <Font size="15.0" />
                           </font></TextArea>
                        <Label layoutX="138.0" layoutY="71.0" text="输入明密文对">
                           <font>
                              <Font size="20.0" />
                           </font>
                        </Label>
                        <Label layoutX="43.0" layoutY="152.0" text="明文：">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <Label layoutX="43.0" layoutY="234.0" text="密文：">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <Label layoutX="321.0" layoutY="86.0" text="明密文对：">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <Label layoutX="339.0" layoutY="301.0" text="密钥：">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                     </children>
                  </Pane>
               </content>
            </Tab>
            <Tab text="三重加密">
               <content>
                  <Pane id="multiPane" prefHeight="200.0" prefWidth="200.0" stylesheets="@../CSS/multiPaneCSS.css">
                     <children>
                        <TextField fx:id="trebleOriginalMessage" layoutX="128.0" layoutY="122.0" prefHeight="32.0" prefWidth="184.0">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                        <Button layoutX="177.0" layoutY="320.0" mnemonicParsing="false" onAction="#trebleEncode" prefHeight="34.0" prefWidth="84.0" text="加密">
                           <font>
                              <Font size="20.0" />
                           </font>
                        </Button>
                        <Label layoutX="70.0" layoutY="121.0" prefHeight="34.0" prefWidth="58.0" text="明文：">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <Label layoutX="70.0" layoutY="193.0" prefHeight="34.0" prefWidth="59.0" text="密钥：">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <Label layoutX="178.0" layoutY="43.0" prefHeight="41.0" prefWidth="85.0" text="三重加密">
                           <font>
                              <Font size="20.0" />
                           </font>
                        </Label>
                        <Label layoutX="584.0" layoutY="43.0" prefHeight="41.0" prefWidth="84.0" text="三重解密" textFill="WHITE">
                           <font>
                              <Font size="20.0" />
                           </font>
                        </Label>
                        <Label layoutX="476.0" layoutY="121.0" prefHeight="34.0" prefWidth="58.0" text="密文：" textFill="WHITE">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <TextField id="decodeSecretMessage" fx:id="trebleSecretMessage" layoutX="534.0" layoutY="122.0" prefHeight="32.0" prefWidth="184.0" style="-fx-background-color: black; -fx-border-color: white;">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                        <Label layoutX="476.0" layoutY="193.0" prefHeight="34.0" prefWidth="59.0" text="密钥：" textFill="WHITE">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <Button layoutX="584.0" layoutY="320.0" mnemonicParsing="false" onAction="#trebleDecode" prefHeight="34.0" prefWidth="84.0" style="-fx-background-color: black; -fx-border-color: white;" text="解密" textFill="WHITE">
                           <font>
                              <Font size="20.0" />
                           </font>
                        </Button>
                        <TextField fx:id="trebleEncodeRes" layoutX="128.0" layoutY="263.0" prefHeight="32.0" prefWidth="184.0" style="-fx-background-color: silver;">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                        <Label layoutX="476.0" layoutY="262.0" prefHeight="34.0" prefWidth="59.0" text="明文：" textFill="WHITE">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <Label layoutX="70.0" layoutY="262.0" prefHeight="34.0" prefWidth="59.0" text="密文：">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <TextArea fx:id="trebleEncodeKey" layoutX="129.0" layoutY="177.0" prefHeight="67.0" prefWidth="183.0">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextArea>
                        <TextArea id="decodeKeyArea" fx:id="trebleDecodeKey" layoutX="535.0" layoutY="177.0" prefHeight="67.0" prefWidth="183.0" style="-fx-border-color: white;">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextArea>
                        <TextField fx:id="trebleDecodeRes" layoutX="534.0" layoutY="263.0" prefHeight="32.0" prefWidth="184.0" style="-fx-background-color: black; -fx-border-color: gray;" styleClass="decodeOutput">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                     </children>
                  </Pane>
               </content>
            </Tab>
            <Tab text="分组加密">
               <content>
                  <Pane id="multiPane" prefHeight="200.0" prefWidth="200.0" stylesheets="@../CSS/multiPaneCSS.css">
                     <children>
                        <TextField fx:id="cbcOriginalMessage" layoutX="129.0" layoutY="90.0" prefHeight="32.0" prefWidth="184.0">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                        <Button layoutX="177.0" layoutY="320.0" mnemonicParsing="false" onAction="#cbcEncode" prefHeight="34.0" prefWidth="84.0" text="加密">
                           <font>
                              <Font size="20.0" />
                           </font>
                        </Button>
                        <Label layoutX="71.0" layoutY="89.0" prefHeight="34.0" prefWidth="58.0" text="明文：">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <Label layoutX="70.0" layoutY="203.0" prefHeight="34.0" prefWidth="59.0" text="密钥：">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <Label layoutX="178.0" layoutY="43.0" prefHeight="41.0" prefWidth="85.0" text="分组加密">
                           <font>
                              <Font size="20.0" />
                           </font>
                        </Label>
                        <Label layoutX="584.0" layoutY="43.0" prefHeight="41.0" prefWidth="84.0" text="分组解密" textFill="WHITE">
                           <font>
                              <Font size="20.0" />
                           </font>
                        </Label>
                        <Label layoutX="477.0" layoutY="89.0" prefHeight="34.0" prefWidth="58.0" text="密文：" textFill="WHITE">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <TextField id="decodeSecretMessage" fx:id="cbcSecretMessage" layoutX="534.0" layoutY="90.0" prefHeight="32.0" prefWidth="184.0" style="-fx-background-color: black; -fx-border-color: white;">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                        <Label layoutX="477.0" layoutY="203.0" prefHeight="34.0" prefWidth="59.0" text="密钥：" textFill="WHITE">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <Button layoutX="584.0" layoutY="320.0" mnemonicParsing="false" onAction="#cbcDecode" prefHeight="34.0" prefWidth="84.0" style="-fx-background-color: black; -fx-border-color: white;" text="解密" textFill="WHITE">
                           <font>
                              <Font size="20.0" />
                           </font>
                        </Button>
                        <TextField fx:id="cbcEncodeRes" layoutX="128.0" layoutY="263.0" prefHeight="32.0" prefWidth="184.0" style="-fx-background-color: silver;">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                        <Label layoutX="477.0" layoutY="262.0" prefHeight="34.0" prefWidth="59.0" text="明文：" textFill="WHITE">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <Label layoutX="70.0" layoutY="262.0" prefHeight="34.0" prefWidth="59.0" text="密文：">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <TextField fx:id="cbcDecodeRes" layoutX="534.0" layoutY="263.0" prefHeight="32.0" prefWidth="184.0" style="-fx-background-color: black; -fx-border-color: gray;" styleClass="decodeOutput">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                        <TextField fx:id="cbcEncodeKey" layoutX="129.0" layoutY="204.0" prefHeight="32.0" prefWidth="184.0">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                        <TextField id="decodeSecretMessage" fx:id="cbcDecodeKey" layoutX="534.0" layoutY="204.0" prefHeight="32.0" prefWidth="184.0" style="-fx-background-color: black; -fx-border-color: white;">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                        <TextField fx:id="cbcEncodeIV" layoutX="129.0" layoutY="148.0" prefHeight="32.0" prefWidth="184.0">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                        <Label layoutX="36.0" layoutY="147.0" prefHeight="34.0" prefWidth="93.0" text="初始向量：">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                        <TextField id="decodeSecretMessage" fx:id="cbcDecodeIV" layoutX="534.0" layoutY="148.0" prefHeight="32.0" prefWidth="184.0" style="-fx-background-color: black; -fx-border-color: white;">
                           <font>
                              <Font size="15.0" />
                           </font>
                        </TextField>
                        <Label layoutX="444.0" layoutY="147.0" prefHeight="34.0" prefWidth="90.0" text="初始向量：" textFill="WHITE">
                           <font>
                              <Font size="18.0" />
                           </font>
                        </Label>
                     </children>
                  </Pane>
               </content>
            </Tab>
        </tabs>
      </TabPane>
   </children>
</AnchorPane>
```



#### 5.2.2 JavaFX 用户界面CSS样式设计

​	① `#encodePane` 和 `#decodePane` 样式规则:

​		a) 为 `#encodePane` 设置了背景图片为 "../img/true.jpg"，并将背景大小调整为覆盖整个容器 (`cover`)；

​		b) 为 `#decodePane` 设置了背景图片为 "../img/false.png"，并将背景大小调整为覆盖整个容器 (`cover`)；

​	② `.decodeInput` 样式规则:

​		定义文本字段 (`TextField`) 的文本颜色为白色；

​	③ `.decodeOutput` 样式规则:

​		定义文本字段 (`TextField`) 的文本颜色为银色 (silver)；

​	④ `#mimPane` 样式规则:

​		为 `#mimPane` 设置了背景图片为 "../img/true.jpg"，并将背景大小调整为覆盖整个容器 (`cover`)；

​	⑤ `.textArea .content` 样式规则:

​		定义文本区域 (`TextArea`) 的内容区域的背景颜色为银色 (silver)；

​	⑥ `#multiPane` 样式规则:

​		为 `#multiPane` 设置了背景图片为 "../img/or.png"，并将背景大小调整为覆盖整个容器 (`cover`)；

​	⑦ `#decodeSecretMessage` 样式规则:

​		定义文本字段 (`TextField`) 的文本颜色为白色；

​	⑧ `#decodeKeyArea .content` 样式规则:

​		定义 "decodeKeyArea" 区域的内容区域的背景颜色为黑色 (black)；

​	⑨ `#decodeKeyArea` 样式规则:

​		定义 "decodeKeyArea" 区域的文本颜色为白色。

```css
#encodePane{
    -fx-background-image: url("../img/true.jpg");
    -fx-background-size: cover;
}
#decodePane{
    -fx-background-image: url("../img/false.png");
    -fx-background-size: cover;
}
.decodeInput{
    -fx-text-fill: white;
}
.decodeOutput{
    -fx-text-fill: silver;
}

#mimPane{
    -fx-background-image: url("../img/true.jpg");
    -fx-background-size: cover;
}
.textArea .content{
    -fx-background-color: silver;
}

#multiPane{
    -fx-background-image: url("../img/or.png");
    -fx-background-size: cover;
}
#decodeSecretMessage{
    -fx-text-fill: white;
}
#decodeKeyArea .content{
    -fx-background-color: black;
}
#decodeKeyArea{
    -fx-text-fill: white;
}
```



## 6. 使用说明

### 6.1 安装与初始化

#### 6.1.1 安装Java JDK

- 如果您的计算机上没有安装Java JDK，请先下载并安装适用于您操作系统的Java JDK。您可以从Oracle官方网站（https://www.oracle.com/java/technologies/javase-downloads.html）或其他可信来源获取Java JDK的安装程序；
- 安装Java JDK时，请按照安装向导的步骤进行操作。完成后，您将具备Java运行环境。



#### 6.1.2 编译运行S-AES程序

- 推荐下载JetBrains IntelliJ IDEA（通常简称为IDEA），一款由JetBrains公司开发的强大的集成开发环境（IDE），专门用于Java开发，提供了丰富的功能、高度的可定制性和出色的性能；
- 选择src/main/java/org.aes/Main.java点击运行即可。

![img111](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img111.png)



#### 6.1.3 版本控制

- 推荐使用Git进行版本控制；
- 每次更新需要撰写日志。



### 6.2 用户交互界面

#### 6.2.1 首页（加密模块）

​	运行该程序，可得到如下界面，在该界面用户可以选择“二进制加密”模式或者“ASCⅡ编码字符串加密”模式，手动添加明文与密钥，加密所得密文不可进行修改，仅供浏览；

![img1](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img1.png)



​	左上角可以选择不同模式，可以切换到不同的界面；

![img2](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img2.jpg)



#### 6.2.2 解密模块

![img3](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img3.jpg)



#### 6.2.3 中文模块

![img4](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img4.jpg)



#### 6.2.4 双重加密

![img5](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img5.jpg)



#### 6.2.5 中间相遇攻击

![img6](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img6.jpg)



#### 6.2.6 三重加密

![img7](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img7.jpg)



#### 6.2.7 密码分组链模式

![img8](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img8.jpg)



### 6.3 输入

#### 6.3.1 输入格式

​	① 加密模块

​		输入为16bit二进制数或ASCⅡ编码字符串（分组为2Bytes）、16bit二进制密钥；

![img63](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img63.png)



​	② 解密模块

​		输入为16bit二进制数或ASCⅡ编码字符串（分组为2Bytes）、16bit二进制密钥；

![img64](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img64.png)



​	③ 中文模块

​		输入为中文、16bit二进制密钥；

![img65](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img65.png)



​	④ 双重加密模块

​		输入为16bit二进制数、32bit二进制密钥；

![img66](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img66.png)



​	⑤ 中间相遇攻击模块

​		输入为16bit二进制明密文对；

![img67](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img67.png)



​	⑥ 三重加密模块

​		输入为16bit二进制数、32bit二进制密钥；

![img68](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img68.png)



​	⑦ 分组加密模块

​		输入为中文、16bit二进制初始向量、16bit二进制密钥；

![img70](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img70.png)



#### 6.3.2 输入样例

​	① 加密模块

​		a. 明文为16bit二进制数

​			密钥：1010101010101010

​			明文：1100110011001100

![img71](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img71.png)



​		b. 明文为ASCⅡ编码字符串

​			密钥：1010101010101010

​			明文：Andrade

![img72](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img72.png)



​	② 解密模块

​		a. 密文为16bit二进制数

​			密钥：1010101010101010

​			密文：0100001110000001

![img73](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img73.png)



​		b. 密文为ASCⅡ编码字符串

​			密钥：1010101010101010

​			密文：Dc~Ôey[

![img74](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img74.png)



​	③ 中文模块

​		a. 加密

​			密钥：1010101010101010

​			明文：原神启动

![img75](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img75.png)



​		b. 解密

​			密钥：1010101010101010

​			密文：쨱愜셑넋

![img76](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img76.png)



​	④ 双重加密

​		a. 加密

​			密钥：10101010101010101010101010101010

​			明文：1100110011001100

![img78](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img78.png)



​		b. 解密

​			密钥：10101010101010101010101010101010

​			密文：1100000000000101

![img79](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img79.png)



​	⑤ 中间相遇攻击

​		明文：1100110011001100

​		密文：1100000000000101

![img80](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img80.png)

![img81](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img81.png)



​	⑥ 三重加密

​		a. 加密

​			密钥：10101010101010101010101010101010

​			明文：1100110011001100

![img85](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img85.png)



​		b. 解密

​			密钥：10101010101010101010101010101010

​			密文：1001010101101111

![img84](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img84.png)



​	⑦ 分组加密

​		a. 加密

​			初始向量：1001011001001110

​			密钥：1010101010101010

​			明文：安德雷德

![img86](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img86.png)



​		b. 解密

​			初始向量：1001011001001110

​			密钥：1010101010101010

​			密文：흖弹눈됼

![img87](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img87.png)



### 6.4 输出

#### 6.4.1 输出格式

​	① 加密模块

​		输出为16bit二进制数或ASCⅡ编码字符串（分组为2Bytes）密文；

![img88](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img88.png)



​	② 解密模块

​		输出为16bit二进制数或ASCⅡ编码字符串（分组为2Bytes）明文；

![img89](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img89.png)



​	③ 中文模块

​		输出为中文字符密文、明文；

![img90](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img90.png)



​	④ 双重加密模块

​		输出为16bit二进制密文、明文；

![img91](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img91.png)



​	⑤ 中间相遇攻击模块

​		输出为可能的32bit二进制密钥；

![img92](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img92.png)



​	⑥ 三重加密模块

​		输出为16bit二进制密文、明文；

![img93](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img93.png)



​	⑦ 分组加密模块

​		输出为中文字符密文、明文；

![img94](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img94.png)



#### 6.4.2 输出样例

​	① 加密模块

​		a. 明文为16bit二进制数

​			密钥：1010101010101010

​			明文：1100110011001100

​			密文：0100001110000001

![img95](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img95.png)



​		b. 明文为ASCⅡ编码字符串

​			密钥：1010101010101010

​			明文：Andrade

​			密文：Dc~Ôey[

![img96](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img96.png)



​	② 解密模块

​		a. 密文为16bit二进制数

​			密钥：1010101010101010

​			密文：0100001110000001

​			明文：1100110011001100

![img97](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img97.png)



​		b. 密文为ASCⅡ编码字符串

​			密钥：1010101010101010

​			密文：Dc~Ôey[

​			明文：Andrade

![img98](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img98.png)



​	③ 中文模块

​		a. 加密

​			密钥：1010101010101010

​			明文：原神启动

​			密文：쨱愜셑넋

![img99](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img99.png)



​		b. 解密

​			密钥：1010101010101010

​			密文：쨱愜셑넋

​			明文：原神启动

![img100](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img100.png)



​	④ 双重加密

​		a. 加密

​			密钥：10101010101010101010101010101010

​			明文：1100110011001100

​			密文：1100000000000101

![img101](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img101.png)



​		b. 解密

​			密钥：10101010101010101010101010101010

​			密文：1100000000000101

​			明文：1100110011001100

![img103](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img103.png)



​	⑤ 中间相遇攻击

​		明文：1100110011001100

​		密文：1100000000000101

​		可能的密钥：略

![img104](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img104.png)



​	⑥ 三重加密

​		a. 加密

​			密钥：10101010101010101010101010101010

​			明文：1100110011001100

​			密文：1001010101101111

![img105](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img105.png)



​		b. 解密

​			密钥：10101010101010101010101010101010

​			密文：1001010101101111

​			明文：1100110011001100

![img106](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img106.png)



​	⑦ 分组加密

​		a. 加密

​			初始向量：1001011001001110

​			密钥：1010101010101010

​			明文：安德雷德

​			密文：흖弹눈됼

![img107](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img107.png)



​		b. 解密

​			初始向量：1001011001001110

​			密钥：1010101010101010

​			密文：흖弹눈됼

​			明文：安德雷德

![img108](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img108.png)



## 7. 测试与验证

### 7.1 基本加密解密测试

​	本系统提供纯二进制加密解密功能、ASCⅡ编码字符串加密解密功能以及中文字符加密解密功能，在用户交互界面左上角可以选择，并在选中后进入新的界面。



​	① 二进制加密解密

​		密钥：1010101010101010

​		输入明文：1100110011001100

​		所得密文：0100001110000001

![img9](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img9.png)



​		沿用之前密钥：1010101010101010

​		输入密文：0100001110000001

​		解得明文：1100110011001100

![img10](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img10.png)



​	② ASCⅡ编码字符串加密解密

​		密钥：1010101010101010

​		明文：Andrade

​		求得密文：Dc~Ôey[

![img29](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img29.png)



​		密钥：1010101010101010

​		密文：Dc~Ôey[

​		解得明文：Andrade

![img30](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img30.png)



​	③ 中文加密解密

​		密钥：1010101010101010

​		明文：原神启动

​		求得密文：쨱愜셑넋

![img31](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img31.png)



​		密钥：1010101010101010

​		密文：쨱愜셑넋

​		解得明文：原神启动

![img32](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img32.png)



### 7.2 双重加密解密测试

​	密钥：10101010101010101010101010101010

​	明文：1100110011001100

​	密文：1100000000000101

![img33](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img33.png)



​	密钥：10101010101010101010101010101010

​	密文：1100000000000101

​	明文：1100110011001100

![img34](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img34.png)



### 7.3 中间相遇攻击测试

#### 	7.3.1 输入明密文对

​	① 输入单对明密文对

​		在左侧相应位置输入已知的一对明密文对后，点击添加即可添加到右侧明密文对文本域中；

​		明文：1100110011001100

​		密文：1100000000000101

![img35](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img35.png)

![img36](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img36.png)

​		注：点击“清空”按钮可以情况已存储的已知明密文对，从而方便重新输入明密文对。



​	② 输入多对明密文对

​		a. 输入两对明密文对

​			明文1：1100110011001100

​			密文1：1100000000000101

​			明文2：1010101010101010

​			密文2：1001011001100110

![img40](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img40.png)



​		b. 输入三对明密文对

​			明文1：1100110011001100

​			密文1：1100000000000101

​			明文2：1010101010101010

​			密文2：1001011001100110

​			明文3：1110001110001010

​			密文3：1011010101100111

![img42](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img42.png)

![img43](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img43.png)



​			注：根据需要可以输入更多的明密文对，此处不再一一展示。



#### 	7.3.2 破解测试

​	① 输入单对明密文对

​		点击“破解”按钮即可开始中间相遇攻击破解可能的密钥，由于单对明密文对可以破解出的可能的密钥数量过多，所以仅展示其中一部分（十个），推荐输入两对及以上明密文对，方便精准找到密钥；

​		明文：1100110011001100

​		密文：1100000000000101

​		可能的密钥：

​		① 01010100010110110000000000000000；

​		② 01100010010011100000000000000000；

​		③ 00010100001110100000000000000011；

​		④ 00110001001101100000000000000100；

​		⑤ 11101100000101000000000000001000；

​		⑥ 01000000100100000000000000001000；

​		⑦ 00101110111111110000000000001001；

​		⑧ 10000101010101010000000000001010；

​		⑨ 10001001101010010000000000001010；

​		⑩ 01011111101001000000000000001011。

![img38](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img38.png)

![img37](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img37.png)

![img39](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img39.png)



​	② 输入多对明密文对

​		a. 输入两对明密文对

​			明文1：1100110011001100

​			密文1：1100000000000101

​			明文2：1010101010101010

​			密文2：1001011001100110

​			可能的密钥：

​			10101010101010101010101010101010（此时对于该样例已经可以通过中间相遇攻击破解得到唯一的密钥）

![img41](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img41.png)



​		b. 输入三对明密文对

​			明文1：1100110011001100

​			密文1：1100000000000101

​			明文2：1010101010101010

​			密文2：1001011001100110

​			明文3：1110001110001010

​			密文3：1011010101100111

​			可能的密钥：

​			10101010101010101010101010101010

![img44](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img44.png)



#### 7.3.3 封闭测试

​	将输入单对明密文对时破解得到的可能的密钥带回到加密解密测试中，测试密钥的正确性（以下展示两组可能的密钥的测试结果）；

​	① 使用破解得到的密钥-01100010010011100000000000000000

​		密钥：01100010010011100000000000000000

​		明文：1100110011001100

​		密文：1100000000000101

![img45](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img45.png)

![img48](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img48.png)



​	② 使用破解得到的密钥-10001001101010010000000000001010

​		密钥：10001001101010010000000000001010

​		明文：1100110011001100

​		密文：1100000000000101

![img46](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img46.png)

![img47](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img47.png)



### 7.4 三重加密解密测试

​	密钥：10101010101010101010101010101010

​	明文：1100110011001100

​	密文：1001010101101111

![img49](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img49.png)



​	密钥：10101010101010101010101010101010

​	密文：1001010101101111

​	明文：1100110011001100

![img50](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img50.png)



### 7.5 分组加密工作模式测试

​	初始向量：1001011001001110

​	密钥：1010101010101010

​	明文：安德雷德

​	所得密文：흖弹눈됼

![img52](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img52.png)



​	初始向量：1001011001001110

​	密钥：1010101010101010

​	密文：흖弹눈됼

​	解得明文：安德雷德

![img51](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img51.png)



## 8. 常见问题与故障排除

### 8.1 问题1：程序无法启动或崩溃

- 排除方法：
  - 确保已安装所需的Java JDK并正确配置了JAVA_HOME环境变量；
  - 检查程序的依赖项，确保所有必需的库和文件都位于正确的位置；
  - 检查程序的日志文件或控制台输出，以查看是否有错误消息，根据错误消息进行进一步的排除。



### 8.2 问题2：加密或解密操作失败

- 排除方法：
  - 检查输入数据和密钥的格式，确保它们符合程序的要求；
  - 验证密钥生成过程，确保生成的密钥正确；
  - 检查加密或解密算法的实现，确保它遵循S-AES算法规范；
  - 如果可能，尝试使用其他输入数据和密钥进行测试，以确定问题是否特定于某个数据集。



### 8.3 问题3：程序性能不佳或响应时间过长

- 排除方法：
  - 检查计算密集型操作，如数据加密或解密，是否有优化的机会，例如使用更高效的算法或数据结构；
  - 确保程序没有内存泄漏问题，通过检查内存使用情况来排除此类问题；
  - 考虑并发处理，使用多线程来提高性能，特别是在大规模数据处理时。



### 8.4 问题4：用户界面问题

- 排除方法：
  - 检查用户界面元素的布局和设计，确保它们易于理解和使用；
  - 确保用户界面在不同操作系统和分辨率下都能正常工作；
  - 收集用户反馈，并根据反馈来改进用户界面和用户体验。



### 8.5 问题5：安全性问题

- 排除方法：
  - 定期更新程序以修复已知的安全漏洞；
  - 使用密码学库和工具来确保数据的机密性和完整性；
  - 进行安全审查，以识别潜在的安全问题并采取措施加以解决。



## 9. 维护与更新

### 9.1 更新计划

​	①未来计划支持网上加密服务，无需本地部署，远程即可体验我们开发的S-AES加密系统；

​	②未来计划更新密钥随机生成功能，方便用户进行使用；

​	③未来计划支持更灵活位数原文的加密，提高用户体验；

​	④……



### 9.2 联系方式

​	有任何建议欢迎联系我们的工作邮箱：shennmo@foxmall.com，届时请说明来意，谢谢！



## 10. 附录

### 10.1 术语表

​	① AES：全称为 Advanced Encryption Standard（高级加密标准），用于对称密钥加密。它是一种对称密钥加密算法，意味着相同的密钥用于加密和解密数据。AES是一种广泛使用的加密算法，旨在保护数据的机密性，同时提供高度的安全性和性能；

​	② S-AES：全称为Simplified Advanced Encryption Standard（简化版高级加密标准），是一个基于AES算法的简化版本，旨在提供更快的执行速度和更简单的实现。AES是一种对称密钥加密算法，被广泛用于数据加密，但有时对于资源受限的环境，如嵌入式系统、传感器网络、物联网设备等，标准的AES可能太复杂，因此，S-AES作为一种轻量级的加密算法，旨在提供更为简单、更节省资源的加密解决方案。在本次实验中，被应用为一种教育和理解加密算法原理的工具；

​	③ NS：全称为Nibble Substitution（半字节替代），是一种密码学中的数据替代操作。在密码学中，"半字节"通常指的是4位二进制数字（也就是半字节等于4比特），而"替代"则意味着将这些4位数字替换为另一组4位数字，从而进行加密或混淆操作；

​	④ A<sub>k</sub>：全称为Key Addition（密钥加），是在对称密钥加密算法中的一种基本操作。这个操作也被称为"轮密钥加"，它在加密和解密的每个加密轮中都会被执行。密钥加操作通常用于将轮密钥（Round Key）与明文数据进行逐比特或逐字节的按位异或运算，以混淆明文数据和密钥；

​	⑤ SR：全称为Shift Row（行位移），是AES加密算法中的一个基本步骤，用于对AES数据块内的字节进行行位移操作。SR操作在AES的加密和解密过程中都会被执行，以增加密码的安全性；

​	⑥ MC：全称为Mix Columns（列混淆），是AES加密算法中的一个重要步骤。MC操作是AES的加密和解密过程中的核心组成部分之一，用于改变AES数据块中列内字节的排列，以增加密码的安全性和混淆性；

​	⑦ S盒：全称为Substitution Box（替代盒），是一种用于字节替代的非线性变换，主要功能是将输入的8位字节替换为输出的8位字节。这种替代是非线性的，意味着输入字节的每个位（比特）对输出字节的每个位都有影响。S-盒的设计是为了增加加密算法的混淆性，以使密码更难以破解。S-盒在AES加密和解密的过程中都会被广泛使用，以增加密码的安全性；

​	⑧ IV：全称为Initialization Vector（初始向量），是在使用密码分组链模式时的一个重要参数；

​	⑨CBC：全称为Cipher Block Chaining（密码分组链模式），是一种在密码学中常用的分组密码工作模式。它被用于对较长的消息或数据进行加密，并提供了一些重要的安全性特性。CBC 工作模式在多种加密算法中都有应用，包括 AES和 DES（Data Encryption Standard）等。



### 10.2 参考资料

​	① Hu, Haibo. (2023) .作业2：S-AES算法实现. shimo. [作业2：S-AES算法实现 (shimo.im)](https://shimo.im/docs/zm1FlCxE3eYCQSSo/read);

​	② Daemen, J., & Rijmen, V. (2002). The Design of Rijndael: AES - The Advanced Encryption Standard. Springer;

​	③ Stallings, W. (2021). Cryptography and Network Security Principles and Practice, Eighth Edition. Publishing House of Electronics Industry.



## 结束语

​	开发S-AES算法实现程序是一项令人兴奋的任务，它使我们能够深入了解对称密钥加密算法的工作原理，并为密码学领域的学习和研究提供了有力工具。

​	在这份S-AES算法实现的开发手册中，我们提供了详细的信息和指南，旨在帮助您了解、安装、配置和使用S-AES算法实现程序。通过本开发手册，您已经了解了S-AES算法的基本概念、原理和实际实现，并切身体会到了多重加密、中间相遇攻击及分组加密工作模式的实际应用，这将有助于您更好地理解加密技术。同时，在使用S-AES程序的过程中，您不仅掌握了S-AES的核心原理，还学会了如何将这些原理转化为可操作的代码，这对于密码学爱好者、学生和研究人员来说是一次宝贵的学习机会，可以加深对密码学的理解。

​	请记住，S-AES算法的安全性较低，不适合用于实际的数据加密应用，但它仍然是一个有用的教育和研究工具，可用于教育、实验和密码学研究。

​	希望这个开发手册能够帮助您进一步探索密码学的世界，并为您的学术和职业道路提供支持。如果您有任何疑问、建议或需要进一步的帮助，不要犹豫，随时联系我们（shennmo@foxmall.com）。祝愿您在密码学领域的旅程中取得成功！