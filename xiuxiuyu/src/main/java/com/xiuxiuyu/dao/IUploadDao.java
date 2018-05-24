package com.xiuxiuyu.dao;

import com.xiuxiuyu.beans.UploadFile;

public interface IUploadDao {
	/**
	 * 添加上传文件信息
	 */
   public void save(UploadFile uploadFile);
   /**
    * 查询文件信息
    * @param path
    * @return
    */
   public UploadFile getUploadFileInfo(String path);
}
