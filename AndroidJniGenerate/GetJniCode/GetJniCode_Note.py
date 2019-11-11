# -*- coding: utf-8 -*-
import os
import sys

#���androidӦ��ʹ�õľ�̬���ӿ�ٳֽű�
#�Զ���ȡĿ��so�ļ��������������java�������ȡ��������
#���ɽٳ�Դ�룬Ĭ�ϲ���ת�������޸�
#�ýű���Ҫ��linux��python3��������

# ��ȡso�ļ�����������
def GetFuncList(FilePath):
    list = os.popen('nm -D '+FilePath+' | grep Java_').read().split('\n')
    with open('./FuncList.txt', 'a') as TxtFile:
        for funcname in list:
            if(funcname != ''):
                #print(funcname[11:])
                TxtFile.write(funcname[11:]+'\n')
            else:
                print("GetJniCode : get func list end, write to FuncList.txt")

# ���ݵ�������������java��ʵ��
def FindJavaFunc(JavaCodePath):
    with open('./FuncList.txt', 'r') as FuncList:
        with open('./FuncJavaCode.txt', 'a') as FuncJavaCode:
            for jnifuncname in FuncList:
                if(jnifuncname.find('_1') != -1):
                    jnifuncname = jnifuncname.replace('_1', '?')
                javaPath = jnifuncname[5:].split('_')
                javaClassFile = ''
                packageNum = len(javaPath)

                for package in javaPath:
                    packageNum -= 1
                    if(packageNum == 0):
                        javaClassFile = javaClassFile.replace('?', '_')
                        javaClassFile = javaClassFile + '.java'
                        javaFuncname = javaPath[len(javaPath) - 1][:-1]
                        #print(javaClassFile + ' ' + javaPath[len(javaPath) - 1][:-1] + '()')
                        javaFuncCodeTest = os.popen('grep " '+javaFuncname+'" '+JavaCodePath+javaClassFile).read().split('\n')
                        javaFuncCode = '\n'
                        if(len(javaFuncCodeTest) != 1):
                            for test in javaFuncCodeTest:
                                if((test.find('native ') != -1) and (test[len(test)-1] == '{')):
                                    javaFuncCode = test
                                    break
                        #print(javaFuncCode)
                        FuncJavaCode.write(javaFuncCode.lstrip() + '\n')
                        break
                    else:
                        javaClassFile = javaClassFile + '/' +package
            print("GetJniCode : find java func end, write to FuncJavaCode.txt")

# ����java�����дJni����typedef����
def TypeDefFunc():
    with open('./typedef.txt', 'a') as TypeDef:
        with open('./FuncList.txt', 'r') as FuncList:
            with open('./FuncJavaCode.txt', 'r') as FuncJavaCode:
                #print(len(FuncList.read().split('\n')))
                #print(len(FuncJavaCode.read().split('\n')))
                funclist = FuncList.read().split('\n')
                funcjavacode = FuncJavaCode.read().split('\n')
                for i in range(len(funclist)-1):
                    if(funcjavacode[i] == ''):
                        TypeDef.write('\n')
                        continue
                    # �û���������ֵ����
                    ReturnTypeIndex = funcjavacode[i].find('native ') + 7
                    ReturnType = ReturnTypeIndex + funcjavacode[i][ReturnTypeIndex:].find(' ')
                    #print(funcjavacode[i][Index:])
                    funcjavacode[i] = SwitchToJniType(funcjavacode[i][ReturnTypeIndex:ReturnType]) + funcjavacode[i][ReturnType:-2]
                    # �û�������
                    funcnameIndex = funcjavacode[i].find(' ') + 1
                    funcname = funcjavacode[i].find('(') - 1
                    funcjavacode[i] = funcjavacode[i][:funcnameIndex] + 'Origin_' + funclist[i] + funcjavacode[i][funcname+1:]
                    # �����������
                    argvStringIndex = funcjavacode[i].find('(') + 1
                    argvStringEnd = funcjavacode[i].find(')') - 1
                    argvString = funcjavacode[i][argvStringIndex:argvStringEnd+1]
                    argvList = argvString.split(', ')
                    #print(argvList)
                    argvString = 'JNIEnv*, jobject'
                    if(argvList[0] != ''):
                        for argvType in argvList:
                            argvType = argvType[:argvType.find(' ')]
                            argvType = SwitchToJniType(argvType)
                            argvString = argvString + ', ' + argvType
                    funcjavacode[i] = funcjavacode[i][:argvStringIndex] + argvString + ')'
                    # д���ļ�
                    TypeDef.write('typedef ' + funcjavacode[i] + ';\n')
                print("GetJniCode : Write typedef end, write to typedef.txt")

# ����typedef�������ɺ�������
def GiveMeTheJniCode():
    with open('./JniCode.txt', 'a') as JniCode:
        with open('./typedef.txt') as TypeDef:
            for typedefcode in TypeDef:
                if(typedefcode == '\n'):
                    continue
                argvTypeString = typedefcode[typedefcode.find('(')+1:-3]
                argvList = argvTypeString.split(', ')
                if(argvList[0] != ''):
                    argvString = ''
                    for i in range(len(argvList)):
                        argvString = argvString + argvList[i] + ' arg' + str(i) + ', '
                    argvString = argvString[:-2]
                    argvString = argvString
                # ����typedef.txt���ɺ�������
                FuncJniCode = 'JNIEXPORT ' + typedefcode[8:typedefcode.find('Origin_')] + 'JNICALL ' +typedefcode[typedefcode.find('Origin_')+7:typedefcode.find('(')+1] + argvString + '){\n'
                FuncJniCode = FuncJniCode + '\t' + typedefcode[typedefcode.find('Origin_'):typedefcode.find('(')] + '* TargetFunc = \n'
                FuncJniCode = FuncJniCode + '\t\t(' + typedefcode[typedefcode.find('Origin_'):typedefcode.find('(')] + '*)dlsym(Sohandle, "' + typedefcode[typedefcode.find('Origin_')+7:typedefcode.find('(')] + '");\n'
                FuncJniCode = FuncJniCode + '\t' + 'return TargetFunc('
                if(argvList[0] != ''):
                    for i in range(len(argvList)):
                        FuncJniCode = FuncJniCode + 'arg' + str(i) + ', '
                    FuncJniCode = FuncJniCode[:-2]
                FuncJniCode = FuncJniCode + ');\n}\n'
                JniCode.write(FuncJniCode)
            print('GetJniCode : Done, the Jni Code in the JniCode.txt')

# java��Jni��������ת��
def SwitchToJniType(JavaType):
    JniType = JavaType
    if(JavaType == 'String'):
        JniType = 'jstring'
    elif(JavaType == 'void'):
        JniType = 'void'
    elif(JavaType == 'boolean'):
        JniType = 'jboolean'
    elif(JavaType == 'byte'):
        JniType = 'jbyte'
    elif(JavaType == 'char'):
        JniType = 'jchar'
    elif(JavaType == 'short'):
        JniType = 'jshort'
    elif(JavaType == 'int'):
        JniType = 'jint'
    elif(JavaType == 'long'):
        JniType = 'jlong'
    elif(JavaType == 'float'):
        JniType = 'jfloat'
    elif(JavaType == 'double'):
        JniType = 'jdouble'
    elif(JavaType == 'boolean[]'):
        JniType = 'jbooleanArray'
    elif(JavaType == 'byte[]'):
        JniType = 'jbyteArray'
    elif(JavaType == 'char[]'):
        JniType = 'jcharArray'
    elif(JavaType == 'short[]'):
        JniType = 'jshortArray'
    elif(JavaType == 'int[]'):
        JniType = 'jintArray'
    elif(JavaType == 'long[]'):
        JniType = 'jlongArray'
    elif(JavaType == 'float[]'):
        JniType = 'jfloatArray'
    elif(JavaType == 'double[]'):
        JniType = 'jdoubleArray'
    elif(JavaType == 'Class'):
        JniType = 'jclass'
    elif(JavaType == 'Object[]'):
        JniType = 'jobjectArray'
    elif(JavaType == 'Throwable'):
        JniType = 'jthrowable'
    elif(JavaType[-2:] == '[]'):
        JniType = 'jobjectArray'
    else:
        JniType = 'jobject'
    return JniType

if __name__ == "__main__":
    os.popen('rm FuncList.txt')
    os.popen('rm FuncJavaCode.txt')
    os.popen('rm typedef.txt')
    os.popen('rm JniCode.txt')
    GetFuncList(sys.argv[1])
    FindJavaFunc(sys.argv[2])
    TypeDefFunc()
    GiveMeTheJniCode()
