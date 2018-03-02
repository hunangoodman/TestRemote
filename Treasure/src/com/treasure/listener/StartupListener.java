package com.treasure.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * @author JIAGUI
 * @email 1257896208@qq.com
 * @date 2017年4月25日 下午7:38:30
 * @description 启动完成执行
 */
@Service
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {
	@Override
	public void onApplicationEvent(ContextRefreshedEvent evt) {
		if (evt.getApplicationContext().getParent() == null) {
		}
	}
}