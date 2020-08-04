package com.scy.jvmdemo.classloader.fileload;

import java.io.*;
import java.lang.reflect.Method;

/**
 * 从指定文件中加载class文件的FileClassLoader
 * javac生成相应的class文件，放到指定目录，然后由FileClassLoader去加载。
 * 这里也可以从远程网络中加载。
 */
public class FileClassLoader extends ClassLoader {
    // class文件的目录
    private String rootDir;

    public FileClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] getClassData(String className) {

        String path = rootDir + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
        try {
            InputStream ins = new FileInputStream(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int bytesNumRead = 0;
            while ((bytesNumRead = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesNumRead);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String rootDir = "C:\\Users\\sun\\Desktop\\java测试";
        rootDir = "E:\\workspace-photon\\jvmdemo\\target\\classes";
        FileClassLoader loader = new FileClassLoader(rootDir);

        try {
            // 传入class文件的全限定名
            Class<?> clazz = loader.loadClass("com.scy.jvmdemo.classloader.fileload.Dog");
            // com.st.classloader.FileClassLoader@7ea987ac
            System.out.println(clazz.getClassLoader());
            // I am DemoObj
            System.out.println(clazz.newInstance().toString());

            Object o = clazz.newInstance();
            Method method = clazz.getDeclaredMethod("eat");
            method.invoke(o);

            Class<?> clazz2 = loader.loadClass("lang.String");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
