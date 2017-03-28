package ict_trainings.ictapp.courses.helper.model;

import android.graphics.Bitmap;
import android.os.Bundle;

/**
 * Created by FaiZi bUtt on 9/22/2016.
 */

public class Course {

    public Course() {
    }

    //    public static final String COURSE_VERSION = "courseVersion ";
    public static final String COURSE_TITLE = "title";
    public static final String COURSE_STARTING_DATE = "starting_date";
    public static final String COURSE_DAYS = "days";
    public static final String COURSE_DURATION = "duration";
    public static final String COURSE_CHARGES = "charges";
    public static final String COURSE_ICON = "icon";
    public static final String COURSE_BANNER = "banner";
    public static final String COURSE_DES = "description";
//    public static final String COURSE_LINK1 = "courseLink1";
//    public static final String COURSE_LINK2 = "courseLink2";
//    public static final String COURSE_CURRENTBACTH = "courseCurrentBatch";
//    public static final String COURSE_NEXTBATCH1 = "courseNextBatch1";
//    public static final String COURSE_NEXTBATCH2 = "courseNextBatch2";

    public static final String COURSE_BUNDLE = "coursebundle";

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
//        bundle.putString(COURSE_VERSION,this.courseVersion);
        bundle.putString(COURSE_TITLE, this.courseTitle);
        bundle.putString(COURSE_STARTING_DATE, this.startingDate);
        bundle.putString(COURSE_DAYS, this.days);
        bundle.putString(COURSE_DURATION, this.courseDuration);
        bundle.putString(COURSE_CHARGES, this.courseCharges);
        bundle.putString(COURSE_ICON, this.courseIcon);
        bundle.putString(COURSE_BANNER, this.courseBanner);
        bundle.putString(COURSE_DES, this.courseDes);
//        bundle.putString(COURSE_LINK1,this.courseLink1);
//        bundle.putString(COURSE_LINK2,this.courseLink2);
//        bundle.putString(COURSE_CURRENTBACTH,this.courseCurrentBatch);
//        bundle.putString(COURSE_NEXTBATCH1,this.courseNextBatch1);
//        bundle.putString(COURSE_NEXTBATCH2,this.courseNextBatch2);

        return bundle;
    }

    public Course(Bundle bundle) {
        if (bundle != null) {
//            this.courseVersion = bundle.getString("courseVersion ");
            this.courseTitle = bundle.getString("title");
            this.startingDate = bundle.getString("starting_date");
            this.days = bundle.getString("days");
            this.courseDuration = bundle.getString("duration");
            this.courseCharges = bundle.getString("charges");
            this.courseIcon = bundle.getString("icon");
            this.courseBanner = bundle.getString("banner");
            this.courseDes = bundle.getString("description");
//            this.courseLink1 = bundle.getString("courseLink1 ");
//            this.courseLink2 = bundle.getString("courseLink2 ");
//            this.courseCurrentBatch = bundle.getString("courseCurrentBatch");
//            this.courseNextBatch1 = bundle.getString("courseNextBatch1 ");
//            this.courseNextBatch2 = bundle.getString("courseNextBatch2 ");
        }
    }

//    public String getCourseVersion() {
//        return courseVersion;
//    }
//
//    public void setCourseVersion(String courseVersion) {
//        this.courseVersion = courseVersion;
//    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseDes() {
        return courseDes;
    }

    public void setCourseDes(String courseDes) {
        this.courseDes = courseDes;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getCourseCharges() {
        return courseCharges;
    }

    public void setCourseCharges(String courseCharges) {
        this.courseCharges = courseCharges;
    }

//    public String getCourseLink1() {
//        return courseLink1;
//    }
//
//    public void setCourseLink1(String courseLink1) {
//        this.courseLink1 = courseLink1;
//    }
//
//    public String getCourseLink2() {
//        return courseLink2;
//    }
//
//    public void setCourseLink2(String courseLink2) {
//        this.courseLink2 = courseLink2;
//    }
//
//    public String getCourseCurrentBatch() {
//        return courseCurrentBatch;
//    }
//
//    public void setCourseCurrentBatch(String courseCurrentBatch) {
//        this.courseCurrentBatch = courseCurrentBatch;
//    }
//
//    public String getCourseNextBatch1() {
//        return courseNextBatch1;
//    }
//
//    public void setCourseNextBatch1(String courseNextBatch1) {
//        this.courseNextBatch1 = courseNextBatch1;
//    }
//
//    public String getCourseNextBatch2() {
//        return courseNextBatch2;
//    }
//
//    public void setCourseNextBatch2(String courseNextBatch2) {
//        this.courseNextBatch2 = courseNextBatch2;
//    }

    public String getCourseIcon() {
        return courseIcon;
    }

    public void setCourseIcon(String courseIcon) {
        this.courseIcon = courseIcon;
    }

    public String getCourseBanner() {
        return courseBanner;
    }

    public void setCourseBanner(String courseBanner) {
        this.courseBanner = courseBanner;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    //    private String courseVersion;
    private String courseTitle;
    private String startingDate;
    private String days;
    private String courseDuration;
    private String courseCharges;
    private String courseIcon;
    private String courseBanner;
    private String courseDes;
    private Bitmap bitmap;

//    private String courseLink1;
//    private String courseLink2;
//    private String courseCurrentBatch;
//    private String courseNextBatch1;
//    private String courseNextBatch2;
}
