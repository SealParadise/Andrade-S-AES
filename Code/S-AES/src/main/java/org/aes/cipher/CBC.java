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
