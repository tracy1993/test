package com.springdemo.core;

/**
 * �ɲ������ݿ��
 * @author joe
 * @date  2011-10-24 ����10:50:27
 */
public interface Saveable {

	public String getTableName();
	//�����ֶ�
	public String[] getKeyColumns();
}
