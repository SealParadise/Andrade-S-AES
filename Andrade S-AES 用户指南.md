# Andrade S-AES 用户指南

开发人员：徐涵浩 明子鸿

指南撰写人员：明子鸿 徐涵浩

单位：重庆大学大数据与软件学院

联系方式：shennmo@foxmall.com



## 1.引言

### 1.1 编写目的

​	欢迎使用S-AES加解密程序用户指南。本指南将向您介绍如何使用Java语言编写的S-AES（Simplified Advanced Encryption Standard）加解密程序。这个程序是基于"信息安全导论"课程中第7~9次课程所讲述的S-AES算法、多重加密原理及分组加密模式开发而成，旨在帮助您加密和解密数据，以保护您的信息安全。

​	AES是一种广泛使用的对称密钥加密算法，用于保护数据的机密性，其安全性已经经过广泛验证和认可。而S-AES是一种轻量级的加密算法，是对AES的简化版本，通常以16位（2字节）的分组大小工作，其密钥扩展较为简单，密钥长度和轮数相对较少，旨在在资源受限的环境中提供基本的加密功能，通常用于嵌入式系统、传感器网络和物联网等领域，其中计算和存储资源有限。通过本程序，您可以学习和实践S-AES算法、多重加密及分组加密工作模式的基本原理，并将其应用于实际数据加密和解密任务中。

​	在本用户指南中，您将找到以下内容：

​	**① 程序安装与配置**：了解如何下载、安装和配置S-AES加解密程序；

​	**② 基本用法**：学习如何使用程序进行数据加密和解密，包括输入明文、选择密钥、查看加密结果等，数据格式可以是二进制数、ASCⅡ编码字符串以及中文字符；

​	**③ 高级选项**：深入了解S-AES算法的不同参数和选项，以满足不同的加密需求，如使用多重加密、分组加密工作模式进行加解密，还可以体验中间相遇攻击的实际运用；

​	**④ 实例演示**：通过示例演示来展示程序的实际运行，以帮助您更好地理解S-AES加解密和利用多重加密、分组加密工作模式实际工作的过程；

​	**⑤ 错误排除和问题解决**：提供用户使用过程中可能遇到的常见问题的解答部分，以帮助用户解决可能出现的常见问题和错误，这将提高用户在使用本程序时的自信心和效率。

​	无论您是信息安全领域的学生、专业人士还是对加密算法感兴趣的任何人，本用户指南将为您提供宝贵的资源，帮助您掌握S-AES算法并将其应用于实际场景。让我们一起开始吧，保障您的数据安全！



### 1.2 项目背景

#### 1.2.1 项目来源

​	S-AES是一个基于AES（Advanced Encryption Standard）算法的简化版本，旨在提供更快的执行速度和更简单的实现。AES是一种广泛使用的对称密钥加密算法，但它的密钥长度较长，复杂度较高。因此，密钥扩展较为简单、密钥长度和轮数相对较少的S-AES算法被创建出来，作为一种教育和理解加密算法原理的工具。

​	在"信息安全导论"课程中，S-AES被用来教授学生加密算法的基本概念，其允许学生实际操作，演示加密和解密的过程，这有助于学生更好地理解加密算法的实际应用，理解密钥管理、数据保护和数据完整性等基本概念，以及如何使用算法来保护敏感信息。这个程序是基于"信息安全导论"课程中第7~9次课程所讲述的S-AES算法、多重加密原理及分组加密模式开发而成，目的是帮助学生理解加密算法的基本原理，同时提供了实际应用和研究的机会，有助于培养信息安全领域的专业技能和知识。



#### 1.2.2 开发单位

​	重庆大学大数据与软件学院。



#### 1.2.3 主管部门

​	重庆大学大数据与软件学院2021级软件工程03班竹园五栋513。



### 1.3 定义

​	① AES：全称为 Advanced Encryption Standard（高级加密标准），用于对称密钥加密。它是一种对称密钥加密算法，意味着相同的密钥用于加密和解密数据。AES是一种广泛使用的加密算法，旨在保护数据的机密性，同时提供高度的安全性和性能；

​	② S-AES：全称为Simplified Advanced Encryption Standard（简化版高级加密标准），是一个基于AES算法的简化版本，旨在提供更快的执行速度和更简单的实现。AES是一种对称密钥加密算法，被广泛用于数据加密，但有时对于资源受限的环境，如嵌入式系统、传感器网络、物联网设备等，标准的AES可能太复杂，因此，S-AES作为一种轻量级的加密算法，旨在提供更为简单、更节省资源的加密解决方案。在本次实验中，被应用为一种教育和理解加密算法原理的工具；

​	③ NS：全称为Nibble Substitution（半字节替代），是一种密码学中的数据替代操作。在密码学中，"半字节"通常指的是4位二进制数字（也就是半字节等于4比特），而"替代"则意味着将这些4位数字替换为另一组4位数字，从而进行加密或混淆操作；

​	④ A<sub>K</sub>：全称为Key Addition（密钥加），是在对称密钥加密算法中的一种基本操作。这个操作也被称为"轮密钥加"，它在加密和解密的每个加密轮中都会被执行。密钥加操作通常用于将轮密钥（Round Key）与明文数据进行逐比特或逐字节的按位异或运算，以混淆明文数据和密钥；

​	⑤ SR：全称为Shift Row（行位移），是AES加密算法中的一个基本步骤，用于对AES数据块内的字节进行行位移操作。SR操作在AES的加密和解密过程中都会被执行，以增加密码的安全性；

​	⑥ MC：全称为Mix Columns（列混淆），是AES加密算法中的一个重要步骤。MC操作是AES的加密和解密过程中的核心组成部分之一，用于改变AES数据块中列内字节的排列，以增加密码的安全性和混淆性；

​	⑦ S盒：全称为Substitution Box（替代盒），是一种用于字节替代的非线性变换，主要功能是将输入的8位字节替换为输出的8位字节。这种替代是非线性的，意味着输入字节的每个位（比特）对输出字节的每个位都有影响。S-盒的设计是为了增加加密算法的混淆性，以使密码更难以破解。S-盒在AES加密和解密的过程中都会被广泛使用，以增加密码的安全性；

​	⑧ IV：全称为Initialization Vector（初始向量），是在使用密码分组链模式时的一个重要参数；

​	⑨ CBC：全称为Cipher Block Chaining（密码分组链模式），是一种在密码学中常用的分组密码工作模式。它被用于对较长的消息或数据进行加密，并提供了一些重要的安全性特性。CBC 工作模式在多种加密算法中都有应用，包括 AES和 DES（Data Encryption Standard）等。



### 1.4 参考文献

​	① qq_32444825.2018-03-01.软件用户手册（软件使用说明书）模板.CSDN博客；

​	② [软件用户手册（软件使用说明书）模板-CSDN博客](https://blog.csdn.net/qq_32444825/article/details/79412969)；

​	③ Hu, Haibo. (2023) .作业2：S-AES算法实现. shimo. [作业2：S-AES算法实现 (shimo.im)](https://shimo.im/docs/zm1FlCxE3eYCQSSo/read);

​	④ Daemen, J., & Rijmen, V. (2002). The Design of Rijndael: AES - The Advanced Encryption Standard. Springer;

​	⑤ Stallings, W. (2021). Cryptography and Network Security Principles and Practice, Eighth Edition. Publishing House of Electronics Industry.



## 2 软件概述

### 2.1 目标

​	S-AES（Simplified Advanced Encryption Standard）算法实现程序的主要目的是提供一种简化的数据加密和解密方法，旨在教育和理解经典的AES算法的基本原理。S-AES是AES算法的一个简化版本，用于教育和学习密码学的目的，因此其主要目标是教育和演示而不是实际的安全性。以下是S-AES算法实现程序的主要目的：

​	① **教育和学习：** S-AES软件的主要目的是提供一种简化的AES算法的实现，旨在教育和帮助学生、研究人员和密码学爱好者理解对称密钥加密算法的基本原理。通过编写、调试和运行S-AES程序，用户可以深入了解AES算法的工作方式，包括轮函数、替代盒、行位移、列混淆等关键概念；

​	② **实验和练习：** S-AES算法通常作为密码学教育的实验之一。学生可以通过使用S-AES程序进行实验和练习，加强他们的密码学理解和编程技能，以及加密算法的应用；

​	③ **原理演示：** S-AES软件用于演示AES算法的核心原理，包括数据块的加密和解密流程。通过观察S-AES程序的运行，用户可以清楚地看到数据是如何在不同的加密轮中进行处理和变换的；

​	④ **算法测试：** S-AES程序可以用于测试和验证其他程序或库中的AES实现。它可以用作参考，以确保其他程序的正确性，尤其是在教育和研究环境中进行算法比较和测试时；

​	⑤ **密码算法研究：** 研究人员和密码学爱好者可以使用S-AES程序来进行密码算法的研究和实验，以改进和深入研究加密技术。这有助于推动密码学领域的进展和创新。

​	需要注意的是，S-AES算法的安全性相对较低，不适合用于实际的数据加密应用。正规的数据加密应用通常使用更强大和安全的加密算法，如标准AES。然而，S-AES作为教育工具仍然是有价值的，用于帮助人们理解和学习加密算法的基本概念和原理。



## 3 运行环境

### 	3.1 硬件（推荐环境）

​	处理器：AMD Ryzen 7 5800H with Radeon Graphics

​	显卡：NVIDIA GeForce RTX 3060 Laptop GPU

​	内存：Samsung DDR4 3200MHz 32GB

​	硬盘：Samsung SSD 980 1TB



### 	3.2 软件（推荐环境）

​	操作系统：Windows 11

​	集成开发环境：IntelliJ IDEA 2022.1.3

​	Java开发工具包：JDK 1.8



## 4 使用说明

### 4.1 安装与初始化

#### 4.1.1 安装Java JDK

- 如果您的计算机上没有安装Java JDK，请先下载并安装适用于您操作系统的Java JDK。您可以从Oracle官方网站 （https://www.oracle.com/java/technologies/javase-downloads.html） 或其他可信来源获取Java JDK的安装程序；
- 安装Java JDK时，请按照安装向导的步骤进行操作。完成后，您将具备Java运行环境。



#### 4.1.2 下载S-AES程序

- 下载Andrade小组在GitHub仓库中托管的S-AES程序的源代码到本地。



#### 4.1.3 编译或运行S-AES程序

- 推荐下载JetBrains IntelliJ IDEA（通常简称为IDEA），一款由JetBrains公司开发的强大的集成开发环境（IDE），专门用于Java开发，提供了丰富的功能、高度的可定制性和出色的性能；
- 选择src/main/java/org.aes/Main.java点击运行即可。

![img62](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img62.png)



### 4.2 输入

#### 4.2.1 输入格式

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



#### 4.2.2 输入样例

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



### 4.3 输出

#### 4.3.1 输出格式

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



#### 4.3.2 输出样例

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



## 5 运行说明

### 5.1 基础加解密功能

#### 5.1.1 用户交互界面

​	① 加密模块（首页）

​	运行该程序，可得到如下界面，在该界面用户可以选择“二进制加密”模式或者“ASCⅡ编码字符串加密”模式，手动添加明文与密钥，加密所得密文不可进行修改，仅供浏览；

![img1](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img1.png)



​	可以在左上角选择输入原文/密文的格式，需要注意的是每个界面的“结果”处的密文框和明文框均无法直接进行编辑，只能输出展示“加密”和“解密”的结果；

![img2](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img2.jpg)



​	② 解密模块

![img3](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img3.jpg)



​	③ 中文模块

![img4](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img4.jpg)



#### 5.1.2 加密解密流程

​	对于S-AES算法的基础实现，本系统提供纯二进制加密解密功能、ASCⅡ编码字符串加密解密功能和中文加密解密功能，在用户交互界面左上角可以选择。



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



### 5.2 双重加密

#### 5.2.1 用户交互界面-双重加密模块

​	在该界面可以输入16位二进制明文/密文、32位二进制密钥，进行双重加密，并在下面输出对应的密文/明文。

![img5](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img5.jpg)



#### 5.2.2 双重加密解密流程

​	密钥：10101010101010101010101010101010

​	明文：1100110011001100

​	密文：1100000000000101

![img33](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img33.png)



​	密钥：10101010101010101010101010101010

​	密文：1100000000000101

​	明文：1100110011001100

![img34](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img34.png)



### 5.3 中间相遇攻击

#### 5.3.1 用户交互界面-中间相遇攻击模块

​	在该界面，可以在左侧依次输入均为16位二进制数的明密文对，每完成一对明密文对的输入之后，点击“添加”按钮，便会添加在右上方的文本域中，可以输入一对或多对明密文对，完成全部输入后，点击“破解”按钮便可以执行中间相遇攻击，破解所得到的可能的密钥在右下方的文本域中显现，当可能的密钥过多时，只显示前10个。

![img6](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img6.jpg)



#### 5.3.2 中间相遇攻击流程

##### 	5.3.2.1 输入明密文对

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



##### 	5.3.2.2 破解测试

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



##### 5.3.2.3 封闭测试

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



### 5.4 三重加密

#### 5.4.1 用户交互界面-三重加密模块

​	在该界面，可以输入16位二进制明文/密文、32位二进制密钥，输出得到相对应的16位二进制密文/明文，需要注意的是，最下方的“结果”框仅供浏览，不允许用户修改。

![img7](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img7.jpg)



#### 5.4.2 三重加密解密测试

​	密钥：10101010101010101010101010101010

​	明文：1100110011001100

​	密文：1001010101101111

![img49](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img49.png)



​	密钥：10101010101010101010101010101010

​	密文：1001010101101111

​	明文：1100110011001100

![img50](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img50.png)



### 5.5 分组加密

#### 5.5.1 用户交互界面-分组加密模块

​	在该界面，可以输入16位二进制明文/密文、16位二进制初始向量、16位二进制密钥，点击“加密”/“解密”按钮启动分组加密工作模式进行加密解密操作，所得16位二进制密文/明文在最下方“结果”框中输出，需要注意的是，最下方的“结果”框中内容不允许用户修改，仅供浏览。

![img8](https://github.com/SealParadise/Andrade-S-AES/blob/main/Image/img8.jpg)



#### 5.5.2 分组加密解密流程

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



## 6 故障排除

### 6.1 异常处理

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





### 6.2 常见问题

#### 6.2.1 问题1：程序无法启动或崩溃

- 排除方法：
  - 确保已安装所需的Java JDK并正确配置了JAVA_HOME环境变量；
  - 检查程序的依赖项，确保所有必需的库和文件都位于正确的位置；
  - 检查程序的日志文件或控制台输出，以查看是否有错误消息，根据错误消息进行进一步的排除。



#### 6.2.2 问题2：加密或解密操作失败

- 排除方法：
  - 检查输入数据和密钥的格式，确保它们符合程序的要求；
  - 验证密钥生成过程，确保生成的密钥正确；
  - 检查加密或解密算法的实现，确保它遵循S-AES算法规范；
  - 如果可能，尝试使用其他输入数据和密钥进行测试，以确定问题是否特定于某个数据集。



#### 6.2.3 问题3：程序性能不佳或响应时间过长

- 排除方法：
  - 检查计算密集型操作，如数据加密或解密，是否有优化的机会，例如使用更高效的算法或数据结构；
  - 确保程序没有内存泄漏问题，通过检查内存使用情况来排除此类问题；
  - 考虑并发处理，使用多线程来提高性能，特别是在使用中间相遇攻击模块时。



#### 6.2.4 问题4：用户界面问题

- 排除方法：
  - 检查用户界面元素的布局和设计，确保它们易于理解和使用；
  - 确保用户界面在不同操作系统和分辨率下都能正常工作；
  - 收集用户反馈，并根据反馈来改进用户界面和用户体验。



#### 6.2.5 问题5：安全性问题

- 排除方法：
  - 定期更新程序以修复已知的安全漏洞；
  - 使用密码学库和工具来确保数据的机密性和完整性；
  - 进行安全审查，以识别潜在的安全问题并采取措施加以解决。



### 6.3 技术支持

​	有其他任何问题，请随时联系我们，请备注来意！

​	邮箱：shennmo@foxmall.com



## 7 联系我们

### 7.1 关于开发者团队

​	课程名称：信息安全导论

​	教学班级：992987-001

​	任课教师：胡海波

​	单位：重庆大学大数据与软件学院

​	主管部门：重庆大学大数据与软件学院2021级软件工程03班竹园五栋513

​	小组代号：Andrade

​	小组成员：明子鸿 徐涵浩



### 7.2 联系方式

​	邮箱：shennmo@foxmall.com



## 结束语

​	在使用本S-AES程序的过程中，您已经了解了S-AES算法的基本原理和实际操作，并且切身体会了多重加密、中间相遇攻击和分组加密工作模式的实际应用。S-AES不仅是一个强大的教育工具，还可以作为密码学研究和实验的基础。虽然S-AES的安全性较低，不适合用于实际的数据加密，但它帮助您打开了密码学领域的大门，让您深入了解加密算法的工作方式。

​	在继续您的密码学学习和研究旅程时，建议深入研究更复杂、更安全的加密算法，如标准AES。同时，您可以探索密码学领域的其他方面，包括公钥加密、数字签名、哈希函数等，以获得更全面的密码学知识。

​	最后，感谢您选择使用本S-AES算法实现程序。无论您是密码学新手还是专业人士，都希望S-AES程序对您的学习和研究工作有所帮助。请您继续探索密码学的奥秘，为网络安全和数据隐私做出贡献，并祝您在密码学领域的旅程中取得成功！如果您有任何疑问或需要进一步的帮助，请随时与我们联系。