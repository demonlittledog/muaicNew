package cn.music.service.commonManagerService;

import cn.music.dao.commonManagerDao.CommonTuitionDao;
import cn.music.entity.CommonTuition;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2018/11/8.
 */
public class CommonTuitionService {
    private CommonTuitionDao commonTuitionDao = new CommonTuitionDao();
    //修改剩余学费
    public int updateCommonTuition(CommonTuition commonTuition){
        return  commonTuitionDao.updateCommonTuition(commonTuition);
    }

    //得到学生学费信息
    public List<Map<String,Object>> getAllCommonTuition(int currentPageNo, int pageSize, String da1, String da2, String commonClassName, String studentName){
        return commonTuitionDao.getAllCommonTuition(currentPageNo,pageSize,da1,da2,commonClassName,studentName);
    }

    //得到学生学费信息数量
    public int getAllCommonTuitionCount(String da1, String da2, String commonClassName, String studentName){
        return commonTuitionDao.getAllCommonTuitionCount(da1,da2,commonClassName,studentName);
    }

    //给班级添加学生
    public int addStudentToCommonClass(CommonTuition commonTuition){
        return commonTuitionDao.addStudentToCommonClass(commonTuition);
    }

    //得到某班级的学生缴费信息
    public CommonTuition getCommonTuition(int commonClassId,int studentId){
        return commonTuitionDao.getCommonTuition(commonClassId,studentId);
    }

    //缴费
    public int payMoney(int commonClassId,int studentId,BigDecimal money){
        return commonTuitionDao.payMoney(commonClassId,studentId,money);
    }

    //在缴费表删除学生
    public int deleteStudent(int commonClassId ,int studentId){
        return commonTuitionDao.deleteStudent(commonClassId,studentId);
    }
}
