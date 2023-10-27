# 信息安全导论-作业2

## S-AES算法实现报告

### 一、开发者概况

任课教师：胡海波

小组代号：Andrade

小组成员：徐涵浩 明子鸿

联系方式：shennmo@foxmall.com

单位：重庆大学大数据与软件学院

![Aibo](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/Aibo.jpg)

​	注：左起第一位为徐涵浩，第二位为明子鸿。



### 二、仓库说明

​	本仓库包含Andrade小组完成的信息安全导论作业二的全部内容，主要为源代码（Code文件夹）、测试报告（README.md）、开发手册（Andrade S-AES 开发手册.md）、用户指南（Andrade S-AES 用户指南.md）、图片（Image文件夹），如有内容缺少、内容错误或其他问题请随时联系我们，联系方式shennmo@foxmall.com（请备明来意）。



### 三、测试报告

#### 第一关:基本测试

**题目**： 根据S-AES算法编写和调试程序，提供GUI解密支持用户交互。输入可以是16bit的数据和16bit的密钥，输出是16bit的密文。



**答案**：

##### 1.1 用户交互界面

###### 1.1.1 首页（加密模块）

​	运行该程序，可得到如下界面，在该界面用户可以选择“二进制加密”模式或者“ASCⅡ编码字符串加密”模式，手动添加明文与密钥，加密所得密文不可进行修改，仅供浏览；

![img1](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img1.png)



​	左上角可以选择不同模式，可以切换到不同的界面；

![img2](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img2.jpg)



###### 1.1.2 解密模块

![img3](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img3.jpg)



###### 1.1.3 中文模块

![img4](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img4.jpg)



###### 1.1.4 双重加密

![img5](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img5.jpg)



###### 1.1.5 中间相遇攻击

![img6](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img6.jpg)



###### 1.1.6 三重加密

![img7](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img7.jpg)



###### 1.1.7 密码分组链模式

![img8](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img8.jpg)



##### 1.2 加密解密测试

​	对于S-AES算法的基础实现，本系统提供纯二进制加密解密功能、ASCⅡ编码字符串加密解密功能和中文加密解密功能，在用户交互界面左上角可以选择。由于第一关为基础测试，因此仅展示使用纯二进制数字进行加密解密的功能。



###### 	1.2.1 加密测试

​	密钥：1010101010101010

​	输入明文：1100110011001100

​	所得密文：0100001110000001

![img9](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img9.png)



###### 	1.2.2 解密测试

​	沿用之前密钥：1010101010101010

​	输入密文：0100001110000001

​	解得明文：1100110011001100

![img10](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img10.png)



###### 	1.2.3 异常处理

​	① 当加密时输入明文不符合长度要求时（解密时同理）；

![img13](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img13.png)

![img14](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img14.png)



​	② 当加密时输入明文类型不匹配时（解密时同理）；



​		a. 输入明文为ASCⅡ编码字符串

![img17](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img17.png)

![img18](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img18.png)



​		b. 输入明文为中文字符

![img15](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img15.png)

![img16](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img16.png)



​	③ 当加密时输入密钥不符合长度要求时（解密时同理）；

![img11](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img11.png)

![img12](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img12.png)



​	④当加密时输入明文、密钥均不规范时（解密时同理）；

![img19](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img19.png)

![img20](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img20.png)



##### 1.3 总结

​	在本关卡中，我们小组成功完成了任务要求，主要做到了以下几点：

​	①**S-DES算法的理解和实现**： 在本实验中，我们学习了S-AES算法，这是一种简化版的高级加密标准（Advanced Encryption Standard，AES）算法，使用轻量级加密技术，适用于资源受限的环境，例如嵌入式系统。并且，我们成功编写了一个S-AES加解密程序，该程序能够接收用户提供的16位明文或密文以及16位密钥，通过对输入明文应用S-AES算法、对输入密文应用逆S-AES算法，程序能够加密得到16位密文、还原原始的16位明文；

​	②**GUI设计和用户交互**： 实验中我们实现了一个图形用户界面（GUI），使程序更加用户友好。GUI允许用户轻松输入数据和密钥，并提供加密、解密按钮以启动加密、解密过程，并且，用户可以在GUI上看到加密、解密后的结果，这提高了程序的实用性和易用性；

​	③**加密和解密功能的实现**： 实现了S-AES算法的加密和解密功能，并进行了系统性的测试和调试，以确保程序能够正确处理各种情况，这确保了程序的正确性和稳定性；

​	④**用户友好性和错误处理**： 致力于确保GUI界面易于使用，并提供了错误提示和反馈，以帮助用户在出现问题时解决问题，这增加了程序的友善度。



#### 第二关:交叉测试

**题目**： 考虑到是"**算法标准"**，所有人在编写程序的时候需要使用相同算法流程和转换单元(替换盒、列混淆矩阵等)，以保证算法和程序在异构的系统或平台上都可以正常运行。设有A和B两组位同学(选择相同的密钥K)；则A、B组同学编写的程序对明文P进行加密得到相同的密文C；或者B组同学接收到A组程序加密的密文C，使用B组程序进行解密可得到与A相同的P。



**答案**：

​	我方与胡海波老师班的uttu小组进行交叉测试。



##### 2.1 我方主动加密，由对方进行协作测试；

###### 	2.1.1 我方加密结果

​	密钥：1010101010101010

​	明文：1100110011001100

​	密文：0100001110000001

![img21](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img21.png)



###### 	2.1.2 对方加密结果

​	密钥：1010101010101010

​	明文：1100110011001100

​	密文：0100001110000001

![img22](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img22.jpg)



###### 	2.1.3 我方解密结果

​	密钥：1010101010101010

​	密文：0100001110000001

​	明文：1100110011001100

![img23](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img23.png)



###### 	2.1.4 对方解密结果

​	密钥：1010101010101010

​	密文：0100001110000001

​	明文：1100110011001100

![img24](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img24.jpg)



##### 2.2 对方主动加密，由我方进行协作测试；

###### 	2.2.1 对方加密结果

​	密钥：1010101010101010

​	明文：1010000111001011

​	密文：1110110111000000

![img27](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img27.jpg)



###### 	2.2.2 我方加密结果

​	密钥：1010101010101010

​	明文：1010000111001011

​	密文：1110110111000000

![img25](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img25.png)



###### 	2.2.3 对方解密结果

​	密钥：1010101010101010

​	密文：1110110111000000

​	明文：1010000111001011

![img28](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img28.jpg)



###### 	2.2.4 我方解密结果

​	密钥：1010101010101010

​	密文：1110110111000000

​	明文：1010000111001011

![img26](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img26.png)



##### 2.3 总结

​	在本关卡中，我方小组与对方小组成功进行了交叉测试，当所选密钥相同时，加密、解密的结果均相同且无误，在该过程中，我们主要实现了以下目标：

​	① **理解算法标准化的重要性**： 通过这个实验，我们深刻理解了算法标准化的重要性。确保不同人编写的程序都能够使用相同的算法流程和转换单元对数据进行加密和解密，是信息安全领域的关键，以保证数据的安全性和一致性；

​	② **遵循相同的算法规范**： 在实验中，虽然我们两个小组独立编写了加密和解密程序，但都遵循了相同的算法规范，这样使得我们依然能够在加密和解密操作上达到一致的结果，这对于确保信息安全系统的可扩展性和可维护性至关重要；

​	③ **密钥的共享**： 我们两组选择了相同的密钥K，这是为了在保持算法一致性的前提下，使得加密和解密的结果能够匹配。共享密钥的方式模拟了真实世界中的加密通信，其中双方需要共享相同的密钥以实现数据的安全传输；

​	④ **加解密一致性**： 实验的主要目标是确保A组和B组同学编写的程序可以生成相同的密文C，并对于相同的密文C和密钥K，可以解密得到相同且正确的密文，这意味着无论是A组加密后由B组解密，还是B组加密后由A组解密，都能够得到与原始明文P相同的结果，这进一步证实了程序的一致性；

​	⑤ **强调算法的异构性和跨平台性**： 实验还强调了算法的异构性和跨平台性。这对于信息安全领域至关重要，因为它确保了不同系统和平台上的程序都能够正确地处理数据，而不会导致不一致或错误的结果；

​	⑥ **加深对信息安全的理解**： 这个实验使我们更深入地了解了信息安全领域的一些基本概念，包括加密算法的实际应用、密钥管理和算法标准化的必要性。这将有助于我更好地理解和解决未来的信息安全挑战。



#### 第三关:扩展功能

**题目**： 考虑到向实用性扩展，加密算法的数据输入可以是ASII编码字符串(分组为2 Bytes)，对应地输出也可以是ACII字符串(很可能是乱码)。



**答案**：

​	本系统提供纯二进制加密解密功能、ASCⅡ编码字符串加密解密功能和中文加密解密功能，在用户交互界面左上角可以选择。由于第三关为拓展功能展示，因此在本关卡将展示使用ASCⅡ编码字符串和中文字符进行加密解密的功能；



##### 3.1 ASCⅡ编码字符串加密解密测试

###### 	3.1.1 加密测试

​	密钥：1010101010101010

​	明文：Andrade

​	求得密文：Dc~Ôey[

![img29](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img29.png)



###### 	3.1.2 解密测试

​	密钥：1010101010101010

​	密文：Dc~Ôey[

​	解得明文：Andrade

![img30](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img30.png)



##### 3.2 中文字符加密解密测试

###### 3.2.1 加密测试

​	密钥：1010101010101010

​	明文：原神启动

​	求得密文：쨱愜셑넋

![img31](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img31.png)



###### 3.2.2 解密测试

​	密钥：1010101010101010

​	密文：쨱愜셑넋

​	解得明文：原神启动

![img32](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img32.png)



##### 3.3 总结

​	在本关卡中，我们小组成功实现了对原系统功能的扩展，实现了对ASCⅡ编码字符串以及中文字符进行加密解密的操作，在过程中我们主要实现了以下目标：

​	① **数据输入的多样性**： 在本次实验中，我们满足了数据输入的多样性的要求。不仅可以处理常见的ASCII编码字符串，还可以处理中文字符，这反映了现实世界中的多语言和字符集的应用需求；

​	② **输出的多样性**： 同样，我们在本次实验中实现了输出可以是ASCII字符串或中文字符的功能，这意味着加密算法必须能够适应不同的输出需求，而不仅仅是局限于一种字符集；

​	③ **实际应用的考虑**： 这次实验的目标是实用性扩展，这意味着我们要关注实际应用中的需求，因为不同情况可能需要不同的输入和输出格式。这对于信息安全领域的专业人员来说是非常重要的，因为他们需要根据具体情况来选择最合适的加密算法和配置；

​	④ **字符编码的处理**： 由于字符集的不同，可能导致在处理中文字符时出现乱码。因此，我们需要考虑字符编码问题，以确保在加密和解密过程中不会损坏数据的完整性；

​	⑤ **扩展性和灵活性**： 本次实验对加密算法的扩展性和灵活性提出了挑战，需要算法能够适应不同的输入和输出格式，同时保持数据的安全性。这孤立了我们思考如何构建灵活的算法，以适应未来的需求。



#### 第四关:多重加密

**题目**：

1. 双重加密

​	将S-AES算法通过双重加密进行扩展，分组长度仍然是16 bits，但密钥长度为32 bits。



2. 中间相遇攻击

​	假设你找到了使用相同密钥的明、密文对(一个或多个)，请尝试使用中间相遇攻击的方法找到正确的密钥Key(K1+K2)。



3. 三重加密

​	将S-AES算法通过三重加密进行扩展，下面两种模式选择一种完成：

​	(1)按照32 bits密钥Key(K1+K2)的模式进行三重加密解密；

​	(2)使用48bits(K1+K2+K3)的模式进行三重加解密。



**答案**：

##### 4.1 双重加密

###### 4.1.1 加密测试

​	密钥：10101010101010101010101010101010

​	明文：1100110011001100

​	密文：1100000000000101

![img33](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img33.png)



###### 4.1.2 解密测试

​	密钥：10101010101010101010101010101010

​	密文：1100000000000101

​	明文：1100110011001100

![img34](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img34.png)



##### 4.2 中间相遇攻击

###### 	4.2.1 输入明密文对

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



###### 	4.2.2 中间相遇攻击测试

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



###### 4.2.3 封闭测试

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



​	以上两组密钥均符合要求，通过封闭测试，因篇幅有限，不再一一测试每一个可能的密钥。



##### 4.3 三重加密

​	本系统选择按照32 bits密钥Key(K1+K2)的模式进行三重加密解密；



###### 4.3.1 加密测试

​	密钥：10101010101010101010101010101010

​	明文：1100110011001100

​	密文：1001010101101111

![img49](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img49.png)



###### 4.3.2 解密测试

​	密钥：10101010101010101010101010101010

​	密文：1001010101101111

​	明文：1100110011001100

![img50](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img50.png)



##### 4.4 总结

​	在本关卡中，我们了解了双重加密、中间相遇攻击和三重加密这几个关键概念，扩展了S-AES算法的应用，在该过程中我们主要实现了以下目标：

​	①**双重加密**：

- 在本部分，我们扩展了S-AES算法，以支持双重加密。这意味着明文将经过两次加密，而密钥长度变为32位；
- 双重加密提供了更高的安全性，因为需要两个密钥（K1和K2）以解密数据。这增加了破解密钥的难度。



​	②**中间相遇攻击**：

- 在本部分，我们考虑了中间相遇攻击的概念，作为攻击者，有可能获得了一对或多对使用相同密钥的明文和密文；
- 攻击者的目标是找到正确的密钥（K1+K2），而不必暴力破解密钥，这需要分析明文、密文和中间结果来计算正确的密钥；
- 中间相遇攻击是一种巧妙的攻击方式，提醒了我们在实施安全性分析时需要考虑到密钥管理的重要性。



​	③**三重加密**：

- 本部分扩展了S-AES算法以支持三重加密，我们选择的模式是利用32位密钥（K1+K2）进行扩展；
- 三重加密提供了更高的安全性，因为明文将经过三次加密，而且有多个密钥，这增加了攻击者破解密钥的难度；
- 我们可以选择适合其应用场景的模式，以平衡安全性和性能。



#### 第五关:工作模式

**题目**： 基于S-AES算法，使用密码分组链(CBC)模式对较长的明文消息进行加密。注意初始向量(16 bits) 的生成，并需要加解密双方共享。在CBC模式下进行加密，并尝试对密文分组进行替换或修改，然后进行解密，请对比篡改密文前后的解密结果。



**答案**：

##### 5.1 加密解密测试

###### 5.1.1 加密测试

​	初始向量：1001011001001110

​	密钥：1010101010101010

​	明文：安德雷德

​	所得密文：흖弹눈됼

![img52](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img52.png)



###### 5.1.2 解密测试

​	初始向量：1001011001001110

​	密钥：1010101010101010

​	密文：흖弹눈됼

​	解得明文：安德雷德

![img51](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img51.png)



##### 5.2 篡改对比

###### 5.2.1 仅截获一组密文，进行恶意篡改

​	① 无篡改情况

​		初始向量：1001011001001110

​		密钥：1010101010101010

​		明文：安德雷德

​		密文：흖弹눈됼

![img54](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img54.png)



​	② 有篡改情况

​		初始向量：1001011001001110

​		密钥：1010101010101010

​		密文：흖弹怓됼

​		明文：安德箍趬

![img53](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img53.png)



​	可以看到，经过篡改后，所解得明文与原始明文有不小的差距，接收者可以立刻知道该信息已被人纂改/替换。



###### 5.2.2 截获两组及以上密文，替换部分内容

​	① 无替换情况

​		a.

​		初始向量：1001011001001110

​		密钥：1010101010101010

​		明文：今晚喝什么

​		密文：燆퇳곮爔ब

![img59](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img59.png)



​		b.

​		初始向量：1001011001001110

​		密钥：1010101010101010

​		明文：明天吃火鸡

​		密文：㹓䜴䲪䎈榄

![img60](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img60.png)



​	② 有替换情况

​		初始向量：1001011001001110

​		密钥：1010101010101010

​		密文：燆퇳䲪爔ब

​		明文：今晚싄꺄么

![img61](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img61.png)



​	可以看到，经过部分替换后，所解得明文与原始明文有不小的差距，接收者可以立刻知道该信息已被人纂改/替换。



##### 5.3 总结

​	在本关卡中，我们掌握了关于S-AES算法的CBC模式加密和篡改检测的实际应用经验，主要实现了以下目标：

​	① **CBC模式的引入**：通过引入CBC模式，本次实验展示了一种常见的分组密码加密模式。在CBC模式中，每个明文块在加密前都与前一个密文块进行异或操作，以增加加密的随机性和安全性；

​	② **初始向量（IV）的重要性**：初始向量是在CBC模式中至关重要的，它用于增加密码分组链的复杂性，确保相同的明文在不同加密周期中产生不同的密文。

IV必须在加解密双方共享，以便正常进行加密和解密操作；

​	③ **篡改密文的尝试**：实验要求尝试对密文分组进行篡改或替换，模拟潜在的攻击行为，这有助于理解加密算法的安全性，以及如何检测和应对篡改尝试；

​	④ **解密结果的对比**：重要的一部分是对比篡改密文前后的解密结果。如果密文被篡改，解密后的结果将与原始明文不同，这有助于我们理解密文的完整性和安全性。