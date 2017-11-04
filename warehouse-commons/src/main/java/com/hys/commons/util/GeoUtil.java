package com.hys.commons.util;

public class GeoUtil
{

    /**
     * 根据经纬度和距离返回一个矩形范围
     * 
     * @param lng
     *        经度
     * @param lat
     *        纬度
     * @param distance
     *        距离(单位为米)
     * @return [lng1,lat1, lng2,lat2] 矩形的左下角(lng1,lat1)和右上角(lng2,lat2)
     */

    public static double[] getRectangle(double lng, double lat, long distance)
    {

        float delta = 111000;

        if (lng != 0 && lat != 0)
        {

            double lng1 = lng - distance / Math.abs(Math.cos(Math.toRadians(lat)) * delta);

            double lng2 = lng + distance / Math.abs(Math.cos(Math.toRadians(lat)) * delta);

            double lat1 = lat - (distance / delta);

            double lat2 = lat + (distance / delta);

            return new double[] { lng1, lat1, lng2, lat2 };

        }
        else
        {

            // TODO ZHCH 等于0时的计算公式

            double lng1 = lng - distance / delta;

            double lng2 = lng + distance / delta;

            double lat1 = lat - (distance / delta);

            double lat2 = lat + (distance / delta);

            return new double[] { lng1, lat1, lng2, lat2 };

        }

    }

    /**
     * 得到两点间的距离 米
     * 
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */

    public static double getDistanceOfMeter(double lng1, double lat1, double lng2, double lat2)
    {

        double radLat1 = rad(lat1);

        double radLat2 = rad(lat2);

        double a = radLat1 - radLat2;

        double b = rad(lng1) - rad(lng2);

        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)

        + Math.cos(radLat1) * Math.cos(radLat2)

        * Math.pow(Math.sin(b / 2), 2)));

        s = s * EARTH_RADIUS;

        s = Math.round(s * 10000) / 10;

        return s;

    }

    private static double rad(double d)
    {

        return d * Math.PI / 180.0;

    }

    /**
     * 地球半径：6378.137KM
     */

    private static double EARTH_RADIUS = 6378.137;

    public static void main(String[] args)
    {
        double lon1 = 113.989563;
        double lat1 = 22.594357;

        int radius = 5000;
        double arr[] = getRectangle(lon1, lat1, radius);
        System.out.println(arr[0] + "," + arr[1] + "," + arr[2] + "," + arr[3]);

        // arr = getAround(lat1, lon1, radius);
        // [34.25566276027792,108.94186385411045,34.27363323972208,108.96360814588955]
        // System.out.println(arr[0] + "," + arr[1] + "," + arr[2] + "," + arr[3]);

        // 911717.0 34.264648,108.952736,39.904549,116.407288
        double dis = getDistanceOfMeter(lon1, lat1, arr[2], arr[3]);
        System.out.println(dis);

        // System.out.println(convertToDecimal(22, 35, 41));
        // System.out.println(convertToDecimal(113, 59, 23));
    }

}
