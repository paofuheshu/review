package com.paofuheshu.spring.bean.domain.annotationinjection.test18;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/23 13:46
 * @des
 */
public class BaseService<T> {

    @Autowired
    private IDao<T> dao;

    public IDao<T> getDao() {
        return dao;
    }

    public void setDao(IDao<T> dao) {
        this.dao = dao;
    }
}
