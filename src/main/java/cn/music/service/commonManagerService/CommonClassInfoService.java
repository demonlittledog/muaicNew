package cn.music.service.commonManagerService;

import cn.music.dao.commonManagerDao.CommonClassInfoDao;
import cn.music.entity.CommonClassInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2018/11/11.
 */
public class CommonClassInfoService {
    private CommonClassInfoDao commonClassInfoDao = new CommonClassInfoDao();
    //查询打卡班级是否冻结
    public int getCommonStatus(String commonClassName){
        return commonClassInfoDao.getCommonStatus(commonClassName);
    }

    //得到普通班级信息
    public List<Map<String,Object>> getAllCommonClassInfo(int currentPageNo, int pageSize, String da1, String da2, String commonClassName, String teaManName){
        return commonClassInfoDao.getAllCommonClassInfo(currentPageNo,pageSize,da1,da2,commonClassName,teaManName);
    }

    //得到普通班级信息数量
    public int getAllCommonClassInfoCount(String da1, String da2, String commonClassName, String teaManName){
        return commonClassInfoDao.getAllCommonClassInfoCount(da1,da2,commonClassName,teaManName);
    }

    //增加普通班级信息
    public int addCommonClassInfo(CommonClassInfo commonClassInfo){
        return commonClassInfoDao.addCommonClassInfo(commonClassInfo);
    }

    //查询班级是否存在
    public String getCommonClassNameByCommonClassName(String commonClassName){
        return commonClassInfoDao.getCommonClassNameByCommonClassName(commonClassName);
    }

    //修改班级打卡状态
    public int updateCommonClassStatus(CommonClassInfo commonClassInfo){
        return commonClassInfoDao.updateCommonClassStatus(commonClassInfo);
    }

    //查询班级信息通过id
    public CommonClassInfo getCommonClassInfoById(int id){
        return commonClassInfoDao.getCommonClassInfoById(id);
    }

    //修改班级信息
    public int updateCommonClassInfo(CommonClassInfo commonClassInfo){
        return commonClassInfoDao.updateCommonClassInfo(commonClassInfo);
    }

    //修改班级人数
    public int updateCommonClassStudentNum(int sum,int commonClassId){
        return commonClassInfoDao.updateCommonClassStudentNum(sum,commonClassId);
    }

    //查询班级人数
    public int getStudentNum(int commonClassId){
        return commonClassInfoDao.getStudentNum(commonClassId);
    }

    //得到缴费金额
    public CommonClassInfo getMoney(int commonClassId){
        return commonClassInfoDao.getMoney(commonClassId);
    }

    //得到自己班级信息
    public List<Map<String,Object>> getMyCommonClassInfo(String teaManName,int currentPageNo, int pageSize,String commonClassName){
        return commonClassInfoDao.getMyCommonClassInfo(teaManName,currentPageNo,pageSize,commonClassName);
    }

    //得到自己班级信息数
    public int getMyCommonClassInfoCount(String teaManName,String commonClassName){
        return commonClassInfoDao.getMyCommonClassInfoCount(teaManName,commonClassName);
    }

    //得到自己的班级
    public List<CommonClassInfo> getMyCommonClass(String teaManName){
        return commonClassInfoDao.getMyCommonClass(teaManName);
    }
}
