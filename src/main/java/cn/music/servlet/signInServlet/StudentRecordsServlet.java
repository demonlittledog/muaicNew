package cn.music.servlet.signInServlet;

import cn.music.entity.CommonClassInfo;
import cn.music.entity.StudentInfo;
import cn.music.service.commonManagerService.StudentInfoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StudentRecordsServlet extends HttpServlet {
    private StudentInfoService ss = new StudentInfoService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        //得到班级名称
        String password=null;
        //得到姓名，密码，班级号
        String parentsPhone = request.getParameter("parentsPhone");
        password=request.getParameter("studentPWD");
        //String select=request.getParameter("select");
        //int classID=Integer.parseInt(select);
        synchronized ("abc"){
            int studentId = ss.getStudentId(parentsPhone,password);

            int count = ss.status(studentId,password);

            //得到首次签到时间
            SimpleDateFormat b=new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat a=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String time1 = b.format(date);
            String date2 = a.format(date);
            Date date1=null;
            //得到学生状态 是否冻结
       /* int status = 3;
        status=ss.status(studentName,password);*/
            try {
                date1 = a.parse(date2);
            } catch (ParseException e) {
                e.printStackTrace();
            }


            //d得到今天周几
            int day = ss.commonWeek();
            //签到判断，是否有这个学生
            if(studentId!=-1){

                //根据学生id和周几得到今天这个学生都上那个课,如果一个学生今天有很多课,判断一下现在时间距离哪节课的时间最近
                CommonClassInfo c = ss.studentTodayClass(studentId,day);
                if(c==null){
                    request.setAttribute("mess", "签到失败,不在上课时间！");
                    request.getRequestDispatcher("jsp/studentSign.jsp").forward(request, response);
                }else {
                    int classId = 0;
                    String className=null;
                    classId=c.getCommonClassId();//得到班级id
                    className=c.getCommonClassName();//得到班级名称
                    //得到这节课的课时费
                    BigDecimal commonCost = ss.commonCost(classId);

                    //得到签到状态，是否签到过
                    int commonStatus = 0;
                    commonStatus = ss.commonStatus(studentId, classId, time1);

                    //student s = new student();

                    //事务回滚的方法
                    int ceshi=0;
                    if(classId!=0&&count==1&&commonStatus==0){
                        try {
                            ceshi = ss.a(studentId,classId,date1,1,commonCost,studentId,classId);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    String studentName = ss.getStudentNameById(studentId);
                    if(ceshi==1){
                        request.setAttribute("mess","签到成功，" +"---"+
                                "签到学生是"+studentName+"---"+
                                "签到班级是"+className+"---"+
                                "签到时间"+date2);
                        request.getRequestDispatcher("jsp/studentSign.jsp").forward(request, response);
                    }/*else if(ceshi!=1&&classId!=0&&count>=1){
                request.setAttribute("mess","签到失败，事务回滚，请从新签到");
                request.getRequestDispatcher("jsp/studentSign.jsp").forward(request, response);
            }*/
                    else if(count==0) {
                        request.setAttribute("mess", "签到失败,用户名已冻结");
                        request.getRequestDispatcher("jsp/studentSign.jsp").forward(request, response);
                    }else if(classId==0){
                        request.setAttribute("mess","签到失败，该生今天目前没课");
                        request.getRequestDispatcher("jsp/studentSign.jsp").forward(request, response);
                    }else if (commonStatus==1||commonStatus==2){
                        request.setAttribute("mess","签到失败，该段时间已经签到过");
                        request.getRequestDispatcher("jsp/studentSign.jsp").forward(request, response);
                    }
                    else if(ceshi==0){
                        request.setAttribute("mess","签到失败，出现异常，事务回滚，请从新签到，或者联系管理员");
                        request.getRequestDispatcher("jsp/studentSign.jsp").forward(request, response);
                    }else {
                        request.setAttribute("mess","签到失败，请联系管理员");
                        request.getRequestDispatcher("jsp/studentSign.jsp").forward(request, response);
                    }
                    //总这里------------------------------------------------------------------------
            /*if(classId!=0&&count>=1&&commonStatus==0){
                //根据学生签到班级对该学生的招呼余额进行减掉课时费
                int jian = ss.commontuition(commonCost,ss.studentId(studentName,password),classId,commonCost);
                int qd = ss.qiandao(ss.studentId(studentName,password),classId,date1,1);
                if(qd>0&&jian>0){
                request.setAttribute("mess","签到成功，" +"---"+
                        "签到学生是"+studentName+"---"+
                        "签到班级是"+className+"---"+
                        "签到时间"+date2);
                request.getRequestDispatcher("studentSign.jsp").forward(request, response);
                }
            } else if(classId==0&&count>=1){
                request.setAttribute("mess","签到失败，该生今天目前没课");
                request.getRequestDispatcher("studentSign.jsp").forward(request, response);
            }else if(count==0){
                request.setAttribute("mess","签到失败,用户名已冻结");
                request.getRequestDispatcher("studentSign.jsp").forward(request, response);
            }
            else{
                request.setAttribute("mess","签到失败，该段时间已经签到过");
                request.getRequestDispatcher("studentSign.jsp").forward(request, response);
            }*/
                    //总这里------------------------------------------------------------------------
                }

            }else{
                request.setAttribute("mess","用户名或密码错误");
                request.getRequestDispatcher("jsp/studentSign.jsp").forward(request, response);
            }
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
