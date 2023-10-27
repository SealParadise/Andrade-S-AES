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
