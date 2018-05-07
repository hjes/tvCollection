package com.ssh.service;

import java.util.List;

import com.ssh.entity.OnlineBean;

/**
 * Created by XRom
 * On 11/16/2017.11:57 PM
 */
public interface OnlineBeanService {
    Long saveOnlineBean(OnlineBean bean);

    List<OnlineBean> list();
}
