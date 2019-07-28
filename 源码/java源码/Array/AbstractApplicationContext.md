## AbstractApplicationContext

ioc的核心方法，它是Application的抽象方法。



### refresh

1.构建BeanFactory

2.注册Bean

3.创建Bean

4.触发监听

```java
​```java
	@Override
	public void refresh() throws BeansException, IllegalStateException {
        // 同步加锁
		synchronized (this.startupShutdownMonitor) {
			// 准备应用上下文
			prepareRefresh();

			// 通知子类刷新内部Bean工厂
			ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

			// 准备在这个上下文中使用Bean工厂
			prepareBeanFactory(beanFactory);

			try {
				// 设置BeanFactory在子类中的后置处理器
				postProcessBeanFactory(beanFactory);

				// 调用BeanFactory后置处理器将Bean注册到上下文中
				invokeBeanFactoryPostProcessors(beanFactory);

				// 注册Bean的后置处理器，在Bean创建的过程中被调用
				registerBeanPostProcessors(beanFactory);

				// 对上下文的消息进行初始化
				initMessageSource();

				// 对上下文的事件多播初始化
				initApplicationEventMulticaster();

				// 初始化其他特殊的Bean
				onRefresh();

				// 检查监听Bean并向容器注册
				registerListeners();

				// 初始化懒加载事件
				finishBeanFactoryInitialization(beanFactory);

				// 最后一步，发布对应的容器事件
				finishRefresh();
			}

			catch (BeansException ex) {
				if (logger.isWarnEnabled()) {
					logger.warn("Exception encountered during context initialization - " +
							"cancelling refresh attempt: " + ex);
				}

				// 为避免资源占用，发生异常时消费已经创建的单例Bean
				destroyBeans();

				// 重置'active'变量
				cancelRefresh(ex);

				// 抛出异常
				throw ex;
			}

			finally {
				// 重置公共缓存
				resetCommonCaches();
			}
		}
	}
​```
```

### prepareRefresh 

```
	protected void prepareRefresh() {
		// 获取开始时间
		this.startupDate = System.currentTimeMillis();
		// 关闭状态设置为false
		this.closed.set(false);
		// 启动状态设置为true
		this.active.set(true);
		// 当日志级别设置为info时，输出日志
		if (logger.isInfoEnabled()) {
			logger.info("Refreshing " + this);
		}

		// 初始化在上下文环境中的占位符资源
		initPropertySources();

		// 检验所有的属性是否被标记为可解析
		getEnvironment().validateRequiredProperties();

		// 早期的事件发布
		this.earlyApplicationEvents = new LinkedHashSet<>();
	}
```



