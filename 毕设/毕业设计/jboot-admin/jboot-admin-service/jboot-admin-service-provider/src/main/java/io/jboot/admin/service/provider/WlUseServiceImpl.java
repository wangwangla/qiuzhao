package io.jboot.admin.service.provider;

import io.jboot.aop.annotation.Bean;
import io.jboot.admin.service.api.WlUseService;
import io.jboot.admin.service.entity.model.WlUse;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class WlUseServiceImpl extends JbootServiceBase<WlUse> implements WlUseService {

}