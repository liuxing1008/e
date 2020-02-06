package controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import po.Clazz;
import po.PageBean;
import po.Student;
import service.IStudentBiz;
@Controller
public class StudentController implements IStudentController {
    @Resource(name="StudentBizImpl")
	private IStudentBiz ibiz;
    
	public IStudentBiz getIbiz() {
		return ibiz;
	}

	public void setIbiz(IStudentBiz ibiz) {
		this.ibiz = ibiz;
	}

	@Override
	@RequestMapping(value="save_Student.do")
	public String save(HttpServletRequest request, HttpServletResponse response, Student st) {
		System.out.println("11111111");
		Student ost=st;
		String realpath=request.getRealPath("/");
		System.out.println("222222");
		/***********文件上传****************/
		//获取文件上传的照片对象
		MultipartFile multipartFile=st.getPic();
		System.out.println("33333");
		if(multipartFile!=null&&!multipartFile.isEmpty()){
			System.out.println("44444");
			//获取文件上传名称
			String fname=multipartFile.getOriginalFilename();
			//更名
			System.out.println("44444"+fname);
			if(fname.lastIndexOf(".")!=-1){
				System.out.println("5555555");
				//获取后缀
				String ext=fname.substring(fname.lastIndexOf("."));
			//判断后缀格式
			if(ext.equalsIgnoreCase(".jpg")){
				String newfname=new Date().getTime()+ext;
				//创建文件对象，指定文件上传路径
				File doFile=new File(realpath+"/uppic/"+newfname);
				//上传
				try {
					FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), doFile);
					System.out.println("66666666");
					ost.setFanme (newfname );
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
		}else{
			System.out.println("12325435");
		}
		/***********文件上传end****************/
		System.out.println(ost.toString());
		int flag=ibiz.save(ost);
		
		if(flag>0){
			return "redirect:findPageAll_Student.do";
		}
		return "redirect:error.jsp";
	}

	@Override
	@RequestMapping(value="update_Student.do")
	public String update(HttpServletRequest request, HttpServletResponse response, Student st) {
		//获取原来的照片,在不修改照片的时候展示原来的
		String oldfname=ibiz.findById(st.getStuid()).getFanme ();
		String realpath=request.getRealPath("/");
		/***********文件上传****************/
		//获取文件上传的照片对象
		MultipartFile multipartFile=st.getPic();
		if(multipartFile!=null&&!multipartFile.isEmpty()){
			//获取文件上传名称
			String fname=multipartFile.getOriginalFilename();
			//更名
			if(fname.lastIndexOf(".")!=-1){
				//获取后缀
				String ext=fname.substring(fname.lastIndexOf("."));
			//判断后缀格式
			if(ext.equalsIgnoreCase("jpg")){
				String newfname=new Date().getTime()+ext;
				//创建文件对象，指定文件上传路径
				File doFile=new File(realpath+"/uppic/"+newfname);
				//上传
				try {
					FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), doFile);
				     st.setFanme (newfname);
				     System.out.println("文件上传成功");
				     //删除原来的照片
				     File oldfile=new File(realpath+"uppic"+oldfname);
				     if(oldfile.exists()&&!oldfile.equals("default.jpg")){
				    	 oldfile.delete();//删除
				     }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
		}else{
			st.setFanme (oldfname);
		}
		/***********文件上传end****************/
		int flag=ibiz.update(st);
		if(flag>0){
			return "redirect:findPageAll_Student.do";
		}
		return "redirect:error.jsp";
	}

	@Override
	@RequestMapping(value="delById_Student.do")
	public String delById(HttpServletRequest request, HttpServletResponse response, Integer sid) {
	     int flag=ibiz.delById(sid);
			if(flag>0){
				String oldfname=ibiz.findById(sid).getFanme ();
				 //删除原来的照片
				String realpath=request.getRealPath("/");
			     File oldfile=new File(realpath+"uppic"+oldfname);
			     if(oldfile.exists()&&!oldfile.equals("default.jpg")){
			    	 oldfile.delete();//删除
			     }
				return "redirect:findPageAll_Student.do";
			}
			return "redirect:error.jsp";
	}

	@Override
	@RequestMapping(value="findById_Student.do")
	public String findById(HttpServletRequest request, HttpServletResponse response, Integer sid) {
		Student oldst=ibiz.findById(sid);
		request.setAttribute("oldst", oldst);
		return "stuupdate.jsp";
	}

	@Override
	@RequestMapping(value="findPageAll_Student.do")
	public String findPageAll(HttpServletRequest request, HttpServletResponse response, Integer page, Integer rows) {
	   System.out.println("conn：page="+page);
		HttpSession session=request.getSession();
	    PageBean pb=(PageBean)session.getAttribute("pb");
	    pb=pb==null?new PageBean():pb;
	    page=page==null||page<1?pb.getPage():page;
	    rows=rows==null||rows<1?pb.getRows():rows;
	    int maxrows=ibiz.findmaxRows();
	    int maxpage=1;
	    if(maxrows>0){
	    	maxpage=maxrows%rows==0?maxrows/rows:maxrows/rows+1;
	    }
	    if(page>maxpage)page=maxpage;
	    pb.setPage(page);
	    pb.setRows(rows);
	    List<Student> lsst=ibiz.findPageAll(pb);
	    pb.setMaxpage(maxpage);
	    pb.setPagelist(lsst);
	    session.setAttribute("pb", pb);
		return "redirect:stulist.jsp";
	}

	@Override
	@RequestMapping(value="doinit_Student.do")
	public String doinit(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		List<Clazz> lsca=ibiz.doinit();
	    session.setAttribute("lsca", lsca);
		return "redirect:stuadd.jsp";
	}

}
