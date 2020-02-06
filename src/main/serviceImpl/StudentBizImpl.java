package serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mapper.IStudentMapper;
import po.Clazz;
import po.PageBean;
import po.Student;
import service.IStudentBiz;
@Service("StudentBizImpl")
@Transactional
public class StudentBizImpl implements IStudentBiz {
    @Resource(name="StudentMapper")
	private IStudentMapper sdao;
	public IStudentMapper getSdao() {
		return sdao;
	}

	public void setSdao(IStudentMapper sdao) {
		this.sdao = sdao;
	}

	@Override
	public int save(Student st) {
		// TODO Auto-generated method stub
		return sdao.save(st);
	}

	@Override
	public int update(Student st) {
		// TODO Auto-generated method stub
		return sdao.update(st);
	}

	@Override
	public int delById(Integer sid) {
		// TODO Auto-generated method stub
		return sdao.delById(sid);
	}

	@Override
	public Student findById(Integer sid) {
		// TODO Auto-generated method stub
		return sdao.findById(sid);
	}

	@Override
	public List<Student> findPageAll(PageBean pb) {
		int page=(pb.getPage()-1)*pb.getRows();
		int rows=pb.getRows();
		return sdao.findPageAll(page,rows);
	}

	@Override
	public int findmaxRows() {
		// TODO Auto-generated method stub
		return sdao.findmaxRows();
	}

	@Override
	public List<Clazz> doinit() {
		// TODO Auto-generated method stub
		return sdao.doinit();
	}

}
