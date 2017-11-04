package com.hys.mgt.view.user.component;

import org.springframework.ui.Model;
import com.hys.mgt.view.user.vo.SysUserVo;

/**
 * @author: chenjunxiu 
 * @date 
 */
public interface ILoginViewComp
{

    /**
     * 用户登录验证
     * 
     * @param userVo
     * @return
     * @author: chenjunxiu
     * @date: 
     */
    public String login(Model model, SysUserVo sysAdminVo);

}
