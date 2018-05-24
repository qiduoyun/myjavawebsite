package com.xiuxiuyu.lucene;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.junit.Test;

import com.xiuxiuyu.beans.Content;
import com.xiuxiuyu.util.StringUtils;

/**
 * 全文检索
 * 
 * @author 桂都
 *
 */
public class LuceneUtil {
	//索引路径
	public static String PATH = "";
	public static String getPath(HttpServletRequest request){
		if(!StringUtils.isEmpty(PATH)){
			return PATH;
		}
		PATH = request.getRealPath("WEB-INF/lucene");
		return PATH;
	}
	@Test
	public void testWrite(){
		String path = "D:/yaoquxiu/xiuxiuyu/src/main/webapp/WEB-INF/lucene";
//		Content content = new Content();
//		content.setId(999);
//		content.setTitle("你好");
//		content.setTxt("你叫什么名字呢");
//		writeToIndex(path, content);
		System.out.println(searchFromIndex("下载", 1, 10, 10, new HashSet<Integer>(),new HashSet<String>(), path));
	}
	/**
	 * 写入索引
	 * 
	 * @param path
	 * @param content
	 */
	public static void writeToIndex(String path, Content content) {
		Directory directory = null;
		IndexWriter writer = null;
		try {
			Path dir = Paths.get(path);
			if(!Files.isReadable(dir)){
				System.out.println("Files.isReadable(path2)"+Files.isReadable(dir));
			}
			directory = new SimpleFSDirectory(dir);
			IndexWriterConfig conf = new IndexWriterConfig(new StandardAnalyzer());
			writer = new IndexWriter(directory, conf);
			Document document = new Document();
			Field id = new StringField("id", String.valueOf(content.getId()), Field.Store.YES);
			Field title = new TextField("title", content.getTitle(), Field.Store.NO);
			Field txt = new TextField("txt", content.getTxt(), Field.Store.NO);
			document.add(id);
			document.add(title);
			document.add(txt);
			// 删除后添加
			writer.deleteDocuments(new Term("id", String.valueOf(content.getId())));
			writer.addDocument(document);
			writer.flush();
			writer.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 搜索
	 * @param word
	 * @param list
	 * @return
	 */
	public static Set<Integer> searchFromIndex(String word,Integer page,Integer pageSize,Integer total,Set<Integer> list,Set<String> ids,String path){
		IndexReader reader = null;
		try {
			Directory directory = new SimpleFSDirectory(Paths.get(path));
			reader = DirectoryReader.open(directory);
			IndexSearcher searcher = new IndexSearcher(reader);
			String[] fields={"txt","title"};
			BooleanClause.Occur[] occurs = {BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD};
			Query query = MultiFieldQueryParser.parse(word, fields, occurs, new StandardAnalyzer());
			TopDocs docs = searcher.search(query ,Integer.MAX_VALUE);
			ScoreDoc[] hits = docs.scoreDocs;
			int start = (page-1)*pageSize;
			int end = page*pageSize;
			if(start>=hits.length){
				return list;
			}
			if(end>hits.length){
				end = hits.length;
			}
			for(int i=0;i<hits.length;i++){
				Document document = searcher.doc(hits[i].doc);
				try {
					if(i>=start&&i<=end){
						list.add(Integer.valueOf(document.get("id")));	
					}
					ids.add(document.get("id"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	/**
	 * 删除索引
	 * @param id
	 * @param path
	 */
	public static void deleteIndex(String id,String path){
		Directory directory = null;
		IndexWriter writer = null;
		try {
			directory = new SimpleFSDirectory(Paths.get(path));
			IndexWriterConfig conf = new IndexWriterConfig(new StandardAnalyzer());
			writer = new IndexWriter(directory, conf);
			// ɾ��
			writer.deleteDocuments(new Term("id", id));
			writer.flush();
			writer.commit();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}
}
