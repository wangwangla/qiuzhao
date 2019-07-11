package kw.test.service.impl;

import io.jboot.aop.annotation.Bean;
import kw.test.service.AdminService;
import kw.test.model.Admin;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class AdminServiceImpl extends JbootServiceBase<Admin> implements AdminService {

}