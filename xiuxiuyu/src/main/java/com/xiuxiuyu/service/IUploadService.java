package com.xiuxiuyu.service;

import com.xiuxiuyu.beans.UploadFile;

public interface IUploadService {
	/**
	 * ����ϴ��ļ���Ϣ
	 */
   public void save(UploadFile uploadFile);
   /**
    * ��ѯ�ļ���Ϣ
    * @param path
    * @return
    */
   public UploadFile getUploadFileInfo(String path);
   
}
