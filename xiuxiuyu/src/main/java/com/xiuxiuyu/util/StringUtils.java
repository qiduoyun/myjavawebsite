package com.xiuxiuyu.util;

import java.io.IOException;
import java.io.StringReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import org.htmlparser.Node;
import org.htmlparser.lexer.Lexer;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.util.ParserException;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

public class StringUtils {
	private static final String MD5PASSWORD="xiuxiuyu2016";
   /**
    * 用,拼接数组元素
    * @param objects
    * @return
    */
   public static String joinString(Object[] objects){
	   String resultStr="";
	   for(int index=0;index<objects.length;index++){
		   if(index<objects.length-1){
			   resultStr+=objects[index]+",";
		   }else{
			   resultStr+=objects[index];
		   }
	   }
	   return resultStr;
   }
   /**
    * 判断字符串是否为Null 或 ""
    * @param str
    * @return
    */
   public static boolean isEmpty(String str){
	   if(str==null||str.equals("")){
		   return true;
	   }
	   return false;
   }
   /**
    * 分词
    * @param content
    * @param isSmart
    * @return
    */
   public static String getKeyword(String content,boolean useSmart){
	   StringBuilder keyword=new StringBuilder();
	   StringReader reader = new StringReader(content);
	   IKSegmenter segmenter = new IKSegmenter(reader, useSmart);
	   try {
		   Lexeme lexeme;
		   while((lexeme = segmenter.next())!=null){
			   keyword.append(lexeme.getLexemeText()).append(',');
		   }
		   if(keyword.length()>0){
			   keyword.setLength(keyword.length()-1);
		   }
	} catch (IOException e) {
		e.printStackTrace();
	}
	   return keyword.toString();
   }
   /**
    * 截取HTML内容
    * @param html
    * @param len
    * @return
    */
	public static String html2text(String html, int len) {
		StringBuffer buffer = new StringBuffer();
		try {
			Lexer lexer = new Lexer(html);
			Node node;
			while ((node = lexer.nextNode()) != null) {
				if (node instanceof TextNode) {
					buffer.append(node.getText());
				}
				if (buffer.length() > len) {
					break;
				}
			}
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
	/**
	 * 获取md5信息
	 * @param string
	 * @param password
	 * @return
	 */
	public static String getMD5String(String string,String password){
		String result="";
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update((string+password).getBytes());
			byte[] data = digest.digest();
			HexBinaryAdapter adapter = new HexBinaryAdapter();
			result = adapter.marshal(data).toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}
}
