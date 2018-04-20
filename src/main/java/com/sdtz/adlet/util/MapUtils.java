package com.sdtz.adlet.util;

public class MapUtils {
    //private static double EARTH_RADIUS = 6378.137;
    private static double EARTH_RADIUS = 6371.393;
    private static double rad(double d)
    {
        return d * Math.PI / 180.0;
    }

    /**
     * 计算两个经纬度之间的距离 
     * @param long1
     * @param lat1
     * @param long2
     * @param lat2
     * @return
     */
    public static double GetDistance(double long1, double lat1, double long2, double lat2)
    {
        double a, b, d, sa2, sb2;
        lat1 = rad(lat1);
        lat2 = rad(lat2);
        a = lat1 - lat2;
        b = rad(long1 - long2);

        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        d = 2   * EARTH_RADIUS
                * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)
                * Math.cos(lat2) * sb2 * sb2));
        return d*1000;
    }


    public static void main(String[] args) {
        System.out.println(MapUtils.GetDistance(35.51705903645091,118.43335211277008,35.166417,118.399278));
    }
}
