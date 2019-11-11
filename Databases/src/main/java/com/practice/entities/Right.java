/*
 * Right.java
 *
 * created at 2019-09-09 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.practice.entities;


public class Right
{
    private int rightId;
    private String rightName;


    public Right()
    {
    }


    public Right(String rightName)
    {
        this.rightName = rightName;
    }


    public Right(int rightId, String rightName)
    {
        this.rightId = rightId;
        this.rightName = rightName;
    }


    public int getRightId()
    {
        return rightId;
    }


    public String getRightName()
    {
        return rightName;
    }


    public void setRightId(int rightId)
    {
        this.rightId = rightId;
    }


    public void setRightName(String rightName)
    {
        this.rightName = rightName;
    }


    @Override
    public String toString()
    {
        return String.format("RightId: %d; RightName: %s;", this.rightId, this.rightName);
    }

}
