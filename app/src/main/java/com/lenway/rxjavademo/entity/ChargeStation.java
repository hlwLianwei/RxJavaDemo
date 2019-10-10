package com.lenway.rxjavademo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChargeStation extends GsonObj<ChargeStation> implements Serializable
{
    /**
     * id : 1
     * name : 体育中心充电站
     * address : 广西南宁体育中心
     * area : 青秀区
     * lng : 108.393881685
     * lat : 22.763805758
     * icon :
     * state : 5
     * pileCount : 15
     * gunCount : 30
     * freeGunCount : 0
     * basePrice : 1.32
     * parkingFee :
     * busineHours :
     * openType : 对外开放
     * businessType : 自营
     * contactTel :
     */

    public int id;
    public String name;
    public String address;
    public String area;
    public double lng;
    public double lat;
    public String icon;
    public int state;
    public int pileCount;
    public int gunCount;
    public int freeGunCount;
    public double basePrice;
    public String pilePower;
    public String parkingFee;
    public String busineHours;
    public String openType;
    public String businessType;
    public String contactTel;

    public double distance;
    public List<String> pics;
    public List<String> tags;

    public void initTags()
    {
        if (tags == null)
        {
            tags = new ArrayList<>();

            if (businessType != null && !businessType.trim().equals(""))
            {
                tags.add(businessType.trim());
            }

            if (parkingFee != null && !parkingFee.trim().equals(""))
            {
                tags.add(parkingFee.trim());
            }

            if (openType != null && !openType.trim().equals(""))
            {
                tags.add(openType.trim());
            }
        }
    }
}
