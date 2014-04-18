
package com.coolprogrammers.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.sun.codemodel.JDefinedClass;

@Entity
public class test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String desc;
    private JDefinedClass item;
    private String code;

    public test() {
    }

    public test(String desc, JDefinedClass item, String code) {
        this.desc = desc;
        this.item = item;
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public JDefinedClass getItem() {
        return item;
    }

    public void setItem(JDefinedClass item) {
        this.item = item;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
