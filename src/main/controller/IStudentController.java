package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import po.Clazz;
import po.PageBean;
import po.Student;

public interface IStudentController {
	  public String save (HttpServletRequest request, HttpServletResponse response, Student st);
	  public String update (HttpServletRequest request, HttpServletResponse response, Student st);
	  public String delById (HttpServletRequest request, HttpServletResponse response, Integer sid);
	  public String findById (HttpServletRequest request, HttpServletResponse response, Integer sid);
	  public String findPageAll (HttpServletRequest request, HttpServletResponse response, Integer page, Integer rows);
	  public String doint(HttpServletRequest request, HttpServletResponse response);

	@RequestMapping(value="doinit_Student.do")
	String doinit (HttpServletRequest request, HttpServletResponse response);
}
