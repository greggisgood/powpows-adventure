package com.futuristech.powpow.box2d;

import com.futuristech.powpow.enums.UserDataType;

public class GroundUserData extends UserData {

    public GroundUserData(float width, float height)
    {
        super(width, height);
        userDataType = UserDataType.GROUND;
    }
}
