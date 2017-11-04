package com.hys.model.user;
import java.io.Serializable;
import java.util.Date;
public class SysUser implements Serializable
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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


	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	@Override
	public String toString() {
		return "SysUser [id=" + id + ", loginname=" + loginname + ", password="
				+ password + ", realname=" + realname + ", email=" + email
				+ ", sex=" + sex + ", age=" + age + ", mobile=" + mobile
				+ ", birthday=" + birthday + ", officephone=" + officephone
				+ ", userStatus=" + userStatus + ", createDatetime="
				+ createDatetime + ", createUserId=" + createUserId
				+ ", updateDatetime=" + updateDatetime + ", updateUserId="
				+ updateUserId + "]";
	}

} 
