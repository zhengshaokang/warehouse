package com.hys.mgt.view.user.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.hys.mgt.view.common.constants.WebConstants;
import com.hys.model.user.SysAuth;
import com.hys.model.user.SysRole;

public class SysUserVo implements Serializable
{
    private static final long serialVersionUID = 1L;


    private String id;// 编号
    private String loginname;// 登录名
    private String password;// 用户密码
    private String realname;// 真实姓名
    private String email;// 邮箱
    private Integer sex = -1;// 性别:
    private Integer age = -1;// 年龄
    private String mobile;// 手机
    private String birthday;
    private String officephone;
	
	private Integer userStatus=1;//0关闭1正常
	
	private Date createDatetime;// 创建时间
    private String createUserId;// 创建者ID
    private Date updateDatetime;// 更新时间
    private String updateUserId;// 更新者ID
    private int pageNum = 1;
    private int numPerPage = WebConstants.PAGESIZE;
    private List<SysAuth> auths;
    private List<SysRole> role;
    
    private int isWarhouse;//是否仓库用户
    private int isComment;//是否测评用户
    private int vipLevel;//会员级别
    private String vipDate;//会员到期日期
    private String commentUrl ;//测评模板路径
    private String loginTime ;//用户登录时间
    private String loginIp ;//用户登录ip
    private Integer systemType;// 1 仓库系统  0 评价系统
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getOfficephone() {
		return officephone;
	}
	public void setOfficephone(String officephone) {
		this.officephone = officephone;
	}
	public Integer getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}
	public Date getCreateDatetime() {
		return createDatetime;
	}
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public Date getUpdateDatetime() {
		return updateDatetime;
	}
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	public String getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	public List<SysAuth> getAuths() {
		return auths;
	}
	public void setAuths(List<SysAuth> auths) {
		this.auths = auths;
	}
	public List<SysRole> getRole() {
		return role;
	}
	public void setRole(List<SysRole> role) {
		this.role = role;
	}
	public int getIsWarhouse() {
		return isWarhouse;
	}
	public void setIsWarhouse(int isWarhouse) {
		this.isWarhouse = isWarhouse;
	}
	public int getIsComment() {
		return isComment;
	}
	public void setIsComment(int isComment) {
		this.isComment = isComment;
	}
	public int getVipLevel() {
		return vipLevel;
	}
	public void setVipLevel(int vipLevel) {
		this.vipLevel = vipLevel;
	}
	public String getVipDate() {
		return vipDate;
	}
	public void setVipDate(String vipDate) {
		this.vipDate = vipDate;
	}
	public String getCommentUrl() {
		return commentUrl;
	}
	public void setCommentUrl(String commentUrl) {
		this.commentUrl = commentUrl;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public Integer getSystemType() {
		return systemType;
	}
	public void setSystemType(Integer systemType) {
		this.systemType = systemType;
	}
	@Override
	public String toString() {
		return "SysUserVo [id=" + id + ", loginname=" + loginname
				+ ", password=" + password + ", realname=" + realname
				+ ", email=" + email + ", sex=" + sex + ", age=" + age
				+ ", mobile=" + mobile + ", birthday=" + birthday
				+ ", officephone=" + officephone + ", userStatus=" + userStatus
				+ ", createDatetime=" + createDatetime + ", createUserId="
				+ createUserId + ", updateDatetime=" + updateDatetime
				+ ", updateUserId=" + updateUserId + ", pageNum=" + pageNum
				+ ", numPerPage=" + numPerPage + ", auths=" + auths + ", role="
				+ role + ", isWarhouse=" + isWarhouse + ", isComment="
				+ isComment + ", vipLevel=" + vipLevel + ", vipDate=" + vipDate
				+ ", commentUrl=" + commentUrl + ", loginTime=" + loginTime
				+ ", loginIp=" + loginIp + ", systemType=" + systemType + "]";
	}
}
