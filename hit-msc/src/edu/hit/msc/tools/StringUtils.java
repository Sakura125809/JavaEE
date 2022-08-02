/*
 * @Author: Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Date: 2022-08-02 14:28:37 
 * @Last Modified by:   Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Last Modified time: 2022-08-02 14:28:37 
 */
package edu.hit.msc.tools;

public class StringUtils {
    private static String string = "ABCDEFGHIGKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String randomStr(int len)
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<len;i++)
        {
            int a = (int)(Math.random() * string.length()) ;
            stringBuilder.append(string.charAt(a));
        }
        return stringBuilder.toString();
    }
}

