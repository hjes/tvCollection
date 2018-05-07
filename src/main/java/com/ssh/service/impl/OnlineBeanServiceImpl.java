package com.ssh.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssh.entity.OnlineBean;
import com.ssh.service.OnlineBeanService;
import com.ssh.util.JedisUtils;

/**
 * Created by XRom
 * On 11/16/2017.11:58 PM
 */
@Service
public class OnlineBeanServiceImpl  implements OnlineBeanService {
   
	public Long saveOnlineBean(OnlineBean bean) {
		//this.save(bean);
		return null;
	}

	public List<OnlineBean> list() {
		// TODO Auto-generated method stub
		return (List<OnlineBean>) JedisUtils.getObject("list");
	}
}
