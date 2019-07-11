package kw.test.service.impl;

import io.jboot.aop.annotation.Bean;
import kw.test.service.User03Service;
import kw.test.model.User03;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class User03ServiceImpl extends JbootServiceBase<User03> implements User03Service {

}