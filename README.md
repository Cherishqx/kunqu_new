**1.下载git，配置好**

**2.在settings里登录自己的github账号**
<img width="620" alt="bfb66edbc8d79df54b2957a609628e3" src="https://github.com/user-attachments/assets/8b4dadb4-0f26-4f0e-adac-12d0863be889">

**3.以防下载失败，设置代理。**
在 设置->网络和Internet->代理 中查看设置的端口号。
![image](https://github.com/user-attachments/assets/50bc4d30-5c92-48a6-83ff-aab8f0f2a856)
在终端输入以下命令：
git config --global http.proxy http://127.0.0.1:端口号
git config --global https.proxy http://127.0.0.1:端口号

**4.克隆仓库**
点击Main Menu-> VCS -> Get from Version Control...
<img width="762" alt="56160becd1853f063b843b976d46708" src="https://github.com/user-attachments/assets/1adf99bd-5d4a-47e9-b62e-3938d50f0dd0">

输入：https://github.com/2022212952-zhangweizhen/kunqu_new.git ，会自动生成路径（可以更改），点击clone
<img width="910" alt="085d7722be228533610070b880a6540" src="https://github.com/user-attachments/assets/54ddaff0-64ed-42b7-aa14-aee033e18596">



底部导航栏跳转的fragment对应FirstFragment,SecondFragment（对应xml为：知识）,ThirdFragment（词韵）,FourthFragment（我的）.
FirstFragment包含两个fragment，分别是FirstFragment_origin（对应xml为：首页/发现），FirstFragment_next（首页/前世今生）




