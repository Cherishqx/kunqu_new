git的一些使用，有错误请改正。

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

——————————————————————————————

一些常用功能，也可以直接用命令提交，但是直接点击也挺方便的：

**1.add 提交到 暂存区**

创建文件时会提示Do you want to add the following file to Git? 选择add就可以了。

如果没有提示或选择了取消，该文件会呈现红色。右键选择该文件，选择git，选择add即可加入。

一个文件只用add一次（应该）

**2.pull / fetch 拉取仓库**

在push到github前需要拉取仓库，所以先说这一块。

git fetch

用途：git fetch 用于从远程仓库获取最新的提交，但不会自动合并或更新本地分支。

操作方式：它会将远程仓库中的提交下载到本地，并更新本地仓库中远程分支的指针位置，但不会更改工作目录中的文件或当前分支。

用法：通常与 git checkout 或 git merge 结合使用，可以查看或合并远程分支的内容到本地。

git pull

用途：git pull 用于从远程仓库获取最新的提交，并将其合并到当前分支中。

操作方式：它相当于执行了 git fetch 后紧接着执行了 git merge，即先获取远程仓库的最新提交，然后自动合并到当前分支。

用法：通常用于快速获取远程仓库的最新内容并与本地分支合并。

git pull = git fetch + git merge

我是直接点左边的小箭头，来拉取。

具体逻辑不理解，欢迎补充

**3.commit 从暂存区提交到历史记录**

在左边project下面可以看到，勾选changes,修改commit message(说明此次提交主要改了什么)。

然后可以点击commit或者commit and push

push会帮你推送到github上

在编辑commit message的上方有一个amend，勾选后可以用来修改最后一次的提交，不创建新的提交记录。

**4.左下角Git**

右键本地分支，点击checkout可以将该分支转为目前所在分支(Current Branch)，会显示小书签。

可以查看分支，创建分支什么的，不是很清楚。欢迎补充

————————————————————————————————————————————————

底部导航栏跳转的fragment对应FirstFragment,SecondFragment（对应xml为：知识）,ThirdFragment（词韵）,FourthFragment（我的）.

FirstFragment包含两个fragment，分别是FirstFragment_origin（对应xml为：首页/发现），FirstFragment_next（首页/前世今生）




