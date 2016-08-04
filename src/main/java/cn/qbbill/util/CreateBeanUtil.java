package cn.qbbill.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by 钱斌 on 2016/8/4.
 */
@Component
public class CreateBeanUtil {
    private static final String LINE = "\r\n";
    private static final String TAB = "\t";
    private static final String SPACE = " ";
    private static Map<String, String> map;

    static {
        map = new HashMap<String, String>();
        map.put("CLOB", "String");
        map.put("VARCHAR2", "String");
        map.put("VARCHAR", "String");
        map.put("INTEGER", "Integer");
        map.put("NUMBER", "Integer");
        map.put("FLOAT", "float");
        map.put("TIMESTAMP", "Date");
        map.put("CHAR", "String");
        map.put("DATETIME", "Date");
        map.put("DATE", "Date");
        map.put("BIGINT", "Long");
        map.put("DATE_IMPORT", "import java.util.Date");
        map.put("TIMESTAMP_IMPORT", "import java.util.Date");
        map.put("DATETIME_IMPORT", "import java.util.Date");
    }

    public boolean createJavaBean(ResultSetMetaData rsmetaData, String packageStr, boolean flag) {
        StringBuffer sb = new StringBuffer();
        boolean reFlag = true;
        try {
            // 获取table 列数
            int colum = rsmetaData.getColumnCount();
            /**
             * 获取包名 & 类名 拼接package 与 类名 package com.testmvn.java;
             */
            String packSrc = packageStr.substring(0, packageStr.lastIndexOf("."));
            String className = packageStr.substring(packageStr.lastIndexOf(".") + 1);
            sb.append("package").append(SPACE).append(packSrc).append(";");
            // 追加换行符号
            sb.append(LINE);
            /**
             * 添加 import java.util.Date;
             */
            importPackage(rsmetaData, colum, sb);
            sb.append(LINE);
            sb.append(LINE);
            sb.append("public class ").append(className + " {");
            sb.append(LINE);
            /**
             * 设置属性
             */
            defProperty(rsmetaData, colum, sb);
            /**
             * 添加get & set 方法
             */
            genSetGetMethod(rsmetaData, colum, sb);
            sb.append("}");
            /**
             * 判断项目是否为 maven构建 true 为maven构建 false 非maven构建
             */
            String sysDir = System.getProperty("user.dir");
            if (flag) {
                sysDir += File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator;
            } else {
                sysDir += File.separator + "src" + File.separator;
            }

            String filePath = sysDir + packSrc.replace(".", File.separator);

            createJavaFile(filePath, className, sb.toString());

        } catch (Exception e) {
            reFlag = false;
            e.printStackTrace();
        }
        return reFlag;
    }

    /**
     * 生成 get Set 方法
     * @param rsmetaData
     * @param columnCount
     * @param sb
     */
    private void genSetGetMethod(ResultSetMetaData rsmetaData, int columnCount, StringBuffer sb) {
        try {
            for (int i = 1; i <= columnCount; i++) {
                sb.append(TAB);
                String proType = getPojoType(rsmetaData.getColumnTypeName(i));
                String columnName = dealLine(rsmetaData, i);
                String getName = null;
                String setName = null;
                if (columnName.length() > 1) {
                    getName = "public " + proType + " get" + toUpperCaseFirst(columnName) + "() {";
                    setName = "public void " + "set" + toUpperCaseFirst(columnName) + "(" + proType + " " + columnName
                            + ") {";
                } else {
                    getName = "public get" + columnName.toUpperCase() + "() {";
                    setName = "public set" + columnName.toUpperCase() + "(" + proType + " " + columnName + ") {";
                }
                sb.append(LINE).append(TAB).append(getName);
                sb.append(LINE).append(TAB).append(TAB);
                sb.append("return " + columnName + ";");
                sb.append(LINE).append(TAB).append("}");
                sb.append(LINE);
                sb.append(LINE).append(TAB).append(setName);
                sb.append(LINE).append(TAB).append(TAB);
                sb.append("this." + columnName + " = " + columnName + ";");
                sb.append(LINE).append(TAB).append("}");
                sb.append(LINE);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getPojoType(String dataType) {
        StringTokenizer st = new StringTokenizer(dataType);
        return map.get(st.nextToken());
    }

    // 处理下划线情况，把下划线后一位的字母变大写；
    private String dealLine(ResultSetMetaData md, int i) throws Exception {
        return dealName(md.getColumnName(i).toLowerCase());
    }

    /**
     * 处理下划线 字符大写
     * @param columnName
     * @return
     */
    private String dealName(String columnName) {
        if (columnName.contains("_")) {
            String[] colum_name = columnName.split("_");
            StringBuffer names = new StringBuffer();
            names.append(colum_name[0]);
            for (int i = 1; i < colum_name.length; i++) {
                String suffixStr = colum_name[i].substring(0, 1).toUpperCase()
                        + colum_name[i].substring(1, colum_name[i].length());
                names.append(suffixStr);
            }
            columnName = names.toString();
        }
        return columnName;
    }

    /**
     * 导入所需要的包
     * @param rsmetaData
     * @param columnCount
     * @param sb
     */
    private void importPackage(ResultSetMetaData rsmetaData, int columnCount, StringBuffer sb) {
        try {
            for (int i = 1; i <= columnCount; i++) {
                String importStr = getImport(rsmetaData.getColumnTypeName(i) + "_IMPORT");
                if (importStr != null) {
                    if (sb.toString().contains(importStr)) {
                        continue;
                    } else {
                        sb.append(importStr + ";");
                        sb.append(LINE);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String getImport(String dataType) {
        if (StringUtils.isBlank(map.get(dataType))) {
            return null;
        } else {
            return map.get(dataType);
        }
    }

    /**
     * 定义属性 private String xxxx;
     * @param md
     * @param columnCount
     * @param sb
     */
    private void defProperty(ResultSetMetaData md, int columnCount, StringBuffer sb) {
        try {
            for (int i = 1; i <= columnCount; i++) {
                sb.append(TAB);
                String columnName = dealLine(md, i);
                sb.append("private ").append(getPojoType(md.getColumnTypeName(i))).append(" ").append(columnName + ";");
                sb.append(LINE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将列明首字母大写 ColumName
     * @param columnName
     * @return
     */
    private String toUpperCaseFirst(String columnName) {
        return columnName.substring(0, 1).toUpperCase() + columnName.substring(1, columnName.length());
    }

    /**

     * 生成java 文件 到本地工程中
     * @param fileContent
     * @param filePath
     */
    private void createJavaFile(String filePath, String className, String fileContent) {
        try {
            dirExist(filePath);
            File file = new File(filePath + File.separator + className + ".java");
            FileOutputStream fos = new FileOutputStream(file);
            PrintWriter print = new PrintWriter(fos);
            print.println(fileContent);
            print.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void dirExist(String path) {
        final File filepath = new File(path);

        if (!filepath.exists()) {
            filepath.mkdirs();
        }
    }

}
