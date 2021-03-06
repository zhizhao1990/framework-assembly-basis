package com.cmc.designer.facade.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * table designer entity.
 * @author Thomas Lee
 * @version 2017年3月21日 上午10:21:28
 */
@Entity
@Table(name = "designer")
public class Designer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 默认column名称为age
    private Integer age;

    @Column(name = "nice_name")
    private String niceName;

    @Column(name = "user_name")
    private String userName;
    
    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age
     */
    // @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return nice_name
     */
    public String getNiceName() {
        return niceName;
    }

    /**
     * @param niceName
     */
    public void setNiceName(String niceName) {
        this.niceName = niceName;
    }

    /**
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

}