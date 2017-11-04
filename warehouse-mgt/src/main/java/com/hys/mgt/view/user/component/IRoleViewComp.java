package com.hys.mgt.view.user.component;

import java.util.List;

import org.springframework.ui.Model;

import com.hys.commons.page.PageData;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.user.vo.SysRoleVo;
import com.hys.model.user.SysRole;


/**
 * 角色管理
 * 
 * @author: oyytoy
 * @date 2014年3月29日 下午3:35:54
 */
public interface IRoleViewComp
{
    /**
     * 获取所有的菜单
     * 
     * @return
     * @author: maowu222
     * @date: 2014年5月10日下午7:06:17
     */
    public void queryAll(Model model);

    /*
     * 新增角色
     */
    public String roleAdd(SysRoleVo vo);

    /**
     * 更新角色
     * 
     * @param vo
     * @author: MaoWu1
     * @date: 2014年3月29日下午3:41:49
     */
    public ResultPrompt roleUpdate(SysRoleVo vo, String[] addAuthId, String[] delAuthId);

    /**
     * 添加系统角色与用户关联
     * 
     * @param addRoleIds
     *        待增系统角色Id
     * @param delRoleIds
     *        待删系统角色Id
     * @param userId
     *        用户Id
     * @return 成功true，失败为false
     */
    public boolean updateUserSysRole(String userId, List<String> addRoleIds, List<String> delRoleIds);

    /**
     * 分页查询角色
     * 
     * @param vo
     * @param pageNo
     * @return
     * @author: MaoWu1
     * @date: 2014年3月29日下午3:46:21
     */
    public PageData<SysRole> querySysRoles(SysRoleVo vo, int pageNo);

    /*
     * 根据Id查询角色
     */
    public SysRoleVo queryRoleById(String id);

    /**
     * 根据用户id查询角色列表
     * 
     * @param userId
     *        用户编号
     * @return 角色列表
     */
    public List<SysRole> queryRolesByUserId(String userId);

    /**
     * 根据指定roleid删除角色
     * 
     * @param roleId
     * @return
     * @author: maowu222
     * @date: 2014年5月17日上午9:42:12
     */
    public ResultPrompt roleDelete(String roleId);

}
