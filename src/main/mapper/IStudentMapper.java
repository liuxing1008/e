package mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import po.Clazz;
import po.Student;

import java.util.List;

@Service("StudentMapper")
public interface IStudentMapper {
    public int save (Student st);
    public int update (Student st);
    public int delById (Integer sid);
    public Student findById (Integer sid);
    public List <Student> findPageAll (@Param(value = "page") Integer page, @Param(value = "rows") Integer rows);
    public int findmaxRows ();
    public List< Clazz > doinit ();
}
