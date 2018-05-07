package com.ssh.entity;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Created by XRom
 * On 11/16/2017.11:50 PM
 */
@Data
@Entity
@Table(name = "onlinebean")
public class OnlineBean  implements Serializable{
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "root")
    private String root;

    @Column(name = "roothref")
    private String rootHref;

    @Column(name = "RoomID")
    private String roomID;

    @Column(name = "roomtittle")
    private String roomTittle;

    @Column(name = "name")
    private String name; 
    
    @Column(name = "num")
    private String num; 
    
    @Column(name = "picSrc")
    private String picSrc;

 
    private double numInteger;
 
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public String getRootHref() {
		return rootHref;
	}

	public void setRootHref(String rootHref) {
		this.rootHref = rootHref;
	}

	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	public String getRoomTittle() {
		return roomTittle;
	}

	public void setRoomTittle(String roomTittle) {
		this.roomTittle = roomTittle;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	 

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getPicSrc() {
		return picSrc;
	}

	public void setPicSrc(String picSrc) {
		this.picSrc = picSrc;
	}

	public double getNumInteger() {
		return numInteger;
	}

	public void setNumInteger(double numInteger) {
		this.numInteger = numInteger;
	} 
    
    
    
    
}
