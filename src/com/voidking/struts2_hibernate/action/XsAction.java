package com.voidking.struts2_hibernate.action;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.voidking.struts2_hibernate.dao.XsDao;
import com.voidking.struts2_hibernate.dao.ZyDao;
import com.voidking.struts2_hibernate.dao.imp.KcDaoImp;
import com.voidking.struts2_hibernate.dao.imp.XsDaoImp;
import com.voidking.struts2_hibernate.dao.imp.ZyDaoImp;
import com.voidking.struts2_hibernate.model.Dlb;
import com.voidking.struts2_hibernate.model.Kcb;
import com.voidking.struts2_hibernate.model.Xsb;
import com.voidking.struts2_hibernate.model.Zyb;
public class XsAction extends ActionSupport{
	XsDao xsDao;
	//定义学生对象
	private Xsb xs;
	//定义课程对象
	private Kcb kcb;
	//用于获取照片文件
	private File zpFile;
	//定义专业对象
	private Zyb zyb;
	//生成其getter和setter方法
	public File getZpFile() {
		return zpFile;
	}
	public void setZpFile(File zpFile) {
		this.zpFile=zpFile;
	}
	
	public Kcb getKcb() {
		return kcb;
	}
	public void setKcb(Kcb kcb) {
		this.kcb=kcb;
	}
	
	public Zyb getZyb() {
		return zyb;
	}
	public void setZyb(Zyb zyb) {
		this.zyb=zyb;
	}
	
	public Xsb getXs() {
		return xs;
	}
	public void setXs(Xsb xs) {
		this.xs=xs;
	}
	
	//默认情况下，用该方法获得当前学生的个人信息
	public String execute() throws Exception {
		//获得 Session 对象
		Map session=(Map)ActionContext.getContext().getSession();
		//从 Session 中取出当前用户
		Dlb user=(Dlb)session.get("user");
		//创建 XsDao 接口对象
		xsDao=new XsDaoImp();
		//根据登录学号得到该学生信息
		Xsb xs=xsDao.getOneXs(user.getXh());
		Map request=(Map)ActionContext.getContext().get("request");
		//保存
		request.put("xs", xs);
		return SUCCESS;
	}
	//读取照片信息
	public String getImage() throws Exception{
		xsDao=new XsDaoImp();
		//得到照片的字节数组
		byte[] zp=xsDao.getOneXs(xs.getXh()).getZp();
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("image/jpeg");
		//得到输出流
		ServletOutputStream os=response.getOutputStream();
		if(zp!=null&&zp.length>0){
			for(int i=0;i<zp.length;i++){
				os.write(zp[i]);
			}
		}
		//不去任何页面
		return NONE;
	}
	//这里后面还要加入其他方法，这里先不列出，用到后会列出代码，要加入到这里
	
	//进入修改学生信息页面
	public String updateXsInfo() throws Exception{
		//获取当前用户对象
		Map session=(Map)ActionContext.getContext().getSession();
		Dlb user=(Dlb)session.get("user");
		xsDao=new XsDaoImp();
		ZyDao zyDao=new ZyDaoImp();
		//取出所有专业信息，因为在修改学生信息时，专业栏是下拉列表选择专业，而不是学生自己随便填写
		List zys=zyDao.getAll();
		//得到当前学生的信息
		Xsb xs=xsDao.getOneXs(user.getXh());
		Map request=(Map)ActionContext.getContext().get("request");
		request.put("zys", zys);
		request.put("xs", xs);
		return SUCCESS;
	}
	
	//修改学生信息
	public String updateXs() throws Exception{
		xsDao=new XsDaoImp();
		ZyDao zyDao=new ZyDaoImp();
		//创建一个学生对象，用于存放要修改的学生信息
		Xsb stu=new Xsb();
		//设置学生学号
		stu.setXh(xs.getXh());
		//设置用户填写的姓名
		stu.setXm(xs.getXm());
		//性别
		stu.setXb(xs.isXb());
		//出生时间
		stu.setCssj(xs.getCssj());
		//总学分
		stu.setZxf(xs.getZxf());
		//备注
		stu.setBz(xs.getBz());
		//处理照片信息
		if(this.getZpFile()!=null){
			//得到输入流
			FileInputStream fis=new FileInputStream(this.getZpFile());
			//创建大小为 fis.available() 的字节数组
			byte[] buffer=new byte[fis.available()];
			//把输入流读到字节数组中
			fis.read(buffer);
			stu.setZp(buffer);
		}
		Zyb zy=zyDao.getOneZy(zyb.getId());
		//专业，这里要设置对象，所以下拉列表中传值是要传专业的ID
		stu.setZyb(zy);
		//由于没有修改学生对应的选修的课程，所以直接取出不用改变
		//Hibernate 级联到第三张表，所以要设置，如果不设置，会认为设置为空，会把连接表中有关内容删除
		Set list=xsDao.getOneXs(xs.getXh()).getKcs();
		//设置学生对应多项课程的 Set
		stu.setKcs(list);
		//修改
		xsDao.update(stu);
		return SUCCESS;
	}
	
	//得到学生选修的课程
	public String getXsKcs() throws Exception{
		Map session=(Map)ActionContext.getContext().getSession();
		Dlb user=(Dlb)session.get("user");
		String xh=user.getXh();
		//得到当前学生的信息
		Xsb xsb=new XsDaoImp().getOneXs(xh);
		//取出选修的课程 Set
		Set list=xsb.getKcs();
		Map request=(Map)ActionContext.getContext().get("request");
		//保存
		request.put("list",list);
		return SUCCESS;
	}
	
	//退选课程
	public String deleteKc() throws Exception{
		Map session=(Map)ActionContext.getContext().getSession();
		String xh=((Dlb)session.get("user")).getXh();
		xsDao=new XsDaoImp();
		Xsb xs2=xsDao.getOneXs(xh);
		Set list=xs2.getKcs();
		Iterator iter=list.iterator();
		//取出所有选择的课程进行迭代
		while(iter.hasNext()){
			Kcb kc2=(Kcb)iter.next();
			//如果遍历到退选的课程的课程号就从list中删除
			if(kc2.getKch().equals(kcb.getKch())){
				iter.remove();
			}
		}
		//设置课程的 Set
		xs2.setKcs(list);
		xsDao.update(xs2);
		return SUCCESS;
	}
	
	//选定课程
	public String selectKc() throws Exception{
		Map session=(Map)ActionContext.getContext().getSession();
		String xh=((Dlb)session.get("user")).getXh();
		xsDao=new XsDaoImp();
		Xsb xs3=xsDao.getOneXs(xh);
		Set list=xs3.getKcs();
		Iterator iter=list.iterator();
		//选修课程时先遍历已经选的课程，如果在已经选修的课程中找到就返回 ERROR
		while(iter.hasNext()){
			Kcb kc3=(Kcb)iter.next();
			if(kc3.getKch().equals(kcb.getKch())){
				return ERROR;
			}
		}
		//如果没找到，就添加到集合中
		list.add(new KcDaoImp().getOneKc(kcb.getKch()));
		xs3.setKcs(list);
		xsDao.update(xs3);
		return SUCCESS;
	}
}