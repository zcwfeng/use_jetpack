package top.zcwfeng.sunflower_java.utilities;

/**
 * A helper function to determine a Plant's growing zone for a given latitude.
 *
 * The numbers listed here are roughly based on the United States Department of Agriculture's
 * Plant Hardiness Zone Map (http://planthardiness.ars.usda.gov/), which helps determine which
 * plants are most likely to thrive at a location.
 *
 * If a given latitude falls on the border between two zone ranges, the larger zone range is chosen
 * (e.g. latitude 14.0 => zone 12).
 *
 * Negative latitude values are converted to positive with [Math.abs].
 *
 * For latitude values greater than max (90.0), zone 1 is returned.
 *
 * Modified by Shawn Wang on 4/1/19.
 */
public class GrowZoneUtil {
    public static int getZoneForLatitude(Double latitude) {
        int minuend =  (int) (latitude / 7) - (latitude > 0 && (latitude % 7) == 0 ? 1 : 0);
        return 13 - minuend;
    }
}