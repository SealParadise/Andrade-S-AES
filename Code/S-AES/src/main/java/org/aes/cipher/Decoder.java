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
