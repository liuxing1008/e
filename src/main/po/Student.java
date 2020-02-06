package po;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student implements Serializable {
    private int stuid;
    private String sname;
    private String sex;
    private String address;
    private Date birthday;
    private String fanme="default.jpg";
    private int classid;
    private String canme;
    private String sdate;
    private MultipartFile pic;

    public Student () {
    }

    public Student (int stuid, String sname, String sex, String address, Date birthday, String fanme, int classid, String canme, String sdate, MultipartFile pic) {
        this.stuid = stuid;
        this.sname = sname;
        this.sex = sex;
        this.address = address;
        this.birthday = birthday;
        this.fanme = fanme;
        this.classid = classid;
        this.canme = canme;
        this.sdate = sdate;
        this.pic = pic;
    }

    public Student (String sname, String sex, String address, Date birthday, String fanme, int classid, String sdate, MultipartFile pic) {
        this.sname = sname;
        this.sex = sex;
        this.address = address;
        this.birthday = birthday;
        this.fanme = fanme;
        this.classid = classid;
        this.sdate = sdate;
        this.pic = pic;
    }

    public int getStuid () {
        return stuid;
    }

    public void setStuid (int stuid) {
        this.stuid = stuid;
    }

    public String getSname () {
        return sname;
    }

    public void setSname (String sname) {
        this.sname = sname;
    }

    public String getSex () {
        return sex;
    }

    public void setSex (String sex) {
        this.sex = sex;
    }

    public String getAddress () {
        return address;
    }

    public void setAddress (String address) {
        this.address = address;
    }

    public Date getBirthday () {
        return birthday;
    }

    public void setBirthday (Date birthday) {
        this.birthday = birthday;
    }

    public String getFanme () {
        return fanme;
    }

    public void setFanme (String fanme) {
        this.fanme = fanme;
    }

    public int getClassid () {
        return classid;
    }

    public void setClassid (int classid) {
        this.classid = classid;
    }

    public String getCanme () {
        return canme;
    }

    public void setCanme (String canme) {
        try {
            birthday=new SimpleDateFormat("yyyy-MM-dd").parse(sdate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.sdate = sdate;
    }

    public String getSdate () {
        sdate=new SimpleDateFormat ("yyyy-MM-dd").format(birthday);
        return sdate;
    }

    public void setSdate (String sdate) {
        this.sdate = sdate;
    }

    public MultipartFile getPic () {
        return pic;
    }

    public void setPic (MultipartFile pic) {
        this.pic = pic;
    }

    @Override
    public String toString () {
        return "Student{" +
                "stuid=" + stuid +
                ", sname='" + sname + '\'' +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                ", fanme='" + fanme + '\'' +
                ", classid=" + classid +
                ", canme='" + canme + '\'' +
                ", sdate='" + sdate + '\'' +
                ", pic=" + pic +
                '}';
    }
}
