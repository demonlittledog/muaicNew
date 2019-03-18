package cn.music.service.commonManagerService;

import cn.music.dao.commonManagerDao.ClassTypeDao;
import cn.music.entity.ClassType;

import java.util.List;

/**
 * Created by dell on 2018/11/12.
 */
public class ClassTypeService {
    private ClassTypeDao classTypeDao = new ClassTypeDao();
    //获取全部班级类型分类
    public List<ClassType> getAllClassType(){
        return classTypeDao.getAllClassType();
    }

    //判断用户类型是否存在
    public String getClassTypeNameByClassTypeName(String classTypeName){
        return classTypeDao.getClassTypeNameByClassTypeName(classTypeName);
    }

    //增加班级类型
    public int addClassType(String classTypeName){
        return classTypeDao.addClassType(classTypeName);
    }
}
