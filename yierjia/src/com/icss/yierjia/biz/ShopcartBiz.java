package com.icss.yierjia.biz;

import java.util.List;

import com.icss.yierjia.dao.ShopcartDao;

public class ShopcartBiz {
	/**
	 * ���ﳵ�����Ʒ
	 * @param uid
	 * @param iid
	 * @param iname
	 * @param iprice
	 * @param iimg
	 * @param icount
	 */
	public void addToShopcart(int uid,String iid,String iname,String iprice,String iimg,String icount){
		ShopcartDao sd=new ShopcartDao();
		sd.addToShopcart(uid, iid, iname, iprice, iimg, icount);
	}
	/**
	 * ����ָ���û����ﳵ������Ʒ
	 * @param uid
	 * @return
	 */
	public List<String > shopcartList(int uid) {
		List<String> list=null;
		ShopcartDao sd=new ShopcartDao();
		list=sd.shopcartList(uid);
		return list;
	}
	
	/**
	 * ��ȡ���ﳵѡ����Ʒ��Ϣ
	 * @param uid
	 * @param ids
	 * @return
	 */
	public List<String> shopcartSelectedList(int uid,String[] ids){
		List<String> list=null;
		ShopcartDao dao = new ShopcartDao();
		list = dao.shopcartSelectedList(uid,ids);
		return list;
	}
	/**
	 * �û��޸Ĺ��ﳵָ����Ʒ����
	 * @param uid
	 * @param iid
	 * @param icount
	 */
	public void updateShopcartCount(int uid,String iid,String icount) {
		ShopcartDao sd=new ShopcartDao();
		sd.updateShopcartCount(uid, iid, icount);
	}
	/**
	 * ɾ���û�ָ������Ʒ
	 * @param uid
	 * @param iid
	 */
	public void deleteShopcartItem(int uid,String iid){
		ShopcartDao sd=new ShopcartDao();
		sd.deleteShopcartItem(uid, iid);
	}
}
