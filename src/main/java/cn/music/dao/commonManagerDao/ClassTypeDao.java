package cn.music.dao.commonManagerDao;

import cn.music.dao.baseDao.BaseDao;
import cn.music.entity.ClassType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2018/11/12.
 */
public class ClassTypeDao extends BaseDao{
    //获取全部班级类型分类
    public List<ClassType> getAllClassType(){
        List<ClassType> list = new ArrayList<ClassType>();
        String sql = "select classTypeId,classTypeName from classType";
        super.rs = super.executeSql(sql);
        try {
            while (rs.next()){
                ClassType classType = new ClassType();
                classType.setClassTypeId(rs.getInt(1));
                classType.setClassTypeName(rs.getString(2));
                list.add(classType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            super.closeAll();
        }
        return list;
    }

    //判断班级类型是否存在
    public String getClassTypeNameByClassTypeName(String classTypeName){
        String sql = "select classTypeName from classType where classTypeName=?";
        super.rs = super.executeSql(sql,classTypeName);
        String ctn = null;
        try {
            if(rs.next()){
                ctn = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            super.closeAll();
        }
        return ctn;
    }

    //增加班级类型
    public int addClassType(String classTypeName){
        String sql = "insert into classType values(null,?)";
        return super.executeUpdate(sql,classTypeName);
    }
}
