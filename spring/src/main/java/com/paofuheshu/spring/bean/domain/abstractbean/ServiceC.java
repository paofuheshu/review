package com.paofuheshu.spring.bean.domain.abstractbean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/12 18:50
 * @des
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceC {

    private String name;

    private ServiceA serviceA;
}
