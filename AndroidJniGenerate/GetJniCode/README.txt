获取指定so文件导出表，结合java代码生成so库劫持所需cpp代码
示例：
python3 GetJniCode_Note.py ./libold.so ./xwebruntime_javacode

运行脚本后将typedef.txt中的内容粘贴在cpp文件中
将JniCode.txt中的内容粘贴在cpp函数实现的位置
（生成代码中不包括JNI_OnLoad函数）

注：GetJniCode_Note.py脚本需要在linux+python3环境下运行

