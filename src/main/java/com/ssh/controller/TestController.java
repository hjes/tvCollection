package com.ssh.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssh.entity.OnlineBean;
import com.ssh.service.OnlineBeanService;

/**
 * Created by XRom
 * On 11/16/2017.11:59 PM
 */
@Controller
@RequestMapping("/mvc")
public class TestController {

    @Autowired(required=true)
    private OnlineBeanService onlineBeanService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request) {
    	
    	List<OnlineBean> list=onlineBeanService.list();
    	request.setAttribute("list", list);
    	System.out.println(list.size());
        return "index";
    }

    @RequestMapping(value = "/savePerson", method = RequestMethod.GET)
    @ResponseBody
    public String savePerson() {
       // onlineBeanService.savePerson();
        return "success!";
    }
}
