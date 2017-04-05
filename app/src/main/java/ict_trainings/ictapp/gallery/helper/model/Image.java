package ict_trainings.ictapp.gallery.helper.model;

import android.graphics.Bitmap;
import android.os.Bundle;

/**
 * Created by FaiZi bUtt on 9/22/2016.
 */

public class Image {

    public Image() {
    }

    //    public static final String IMAGE_VERSION = "imageVersion ";
    public static final String IMAGE_TITLE = "title";
//    public static final String IMAGE_STARTING_DATE = "starting_date";
//    public static final String IMAGE_DAYS = "days";
//    public static final String IMAGE_DURATION = "duration";
//    public static final String IMAGE_CHARGES = "charges";
//    public static final String IMAGE_ICON = "icon";
//    public static final String IMAGE_BANNER = "banner";
    public static final String IMAGE_DES = "description";
//    public static final String IMAGE_LINK1 = "imageLink1";
//    public static final String IMAGE_LINK2 = "imageLink2";
//    public static final String IMAGE_CURRENTBACTH = "imageCurrentBatch";
//    public static final String IMAGE_NEXTBATCH1 = "imageNextBatch1";
//    public static final String IMAGE_NEXTBATCH2 = "imageNextBatch2";

    public static final String IMAGE_BUNDLE = "imagebundle";

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
//        bundle.putString(IMAGE_VERSION,this.imageVersion);
        bundle.putString(IMAGE_TITLE, this.imageTitle);
//        bundle.putString(IMAGE_STARTING_DATE, this.startingDate);
//        bundle.putString(IMAGE_DAYS, this.days);
//        bundle.putString(IMAGE_DURATION, this.imageDuration);
//        bundle.putString(IMAGE_CHARGES, this.imageCharges);
//        bundle.putString(IMAGE_ICON, this.imageIcon);
//        bundle.putString(IMAGE_BANNER, this.imageBanner);
        bundle.putString(IMAGE_DES, this.imageDes);
//        bundle.putString(IMAGE_LINK1,this.imageLink1);
//        bundle.putString(IMAGE_LINK2,this.imageLink2);
//        bundle.putString(IMAGE_CURRENTBACTH,this.imageCurrentBatch);
//        bundle.putString(IMAGE_NEXTBATCH1,this.imageNextBatch1);
//        bundle.putString(IMAGE_NEXTBATCH2,this.imageNextBatch2);

        return bundle;
    }

    public Image(Bundle bundle) {
        if (bundle != null) {
//            this.imageVersion = bundle.getString("imageVersion ");
            this.imageTitle = bundle.getString("title");
//            this.startingDate = bundle.getString("starting_date");
//            this.days = bundle.getString("days");
//            this.imageDuration = bundle.getString("duration");
//            this.imageCharges = bundle.getString("charges");
//            this.imageIcon = bundle.getString("icon");
//            this.imageBanner = bundle.getString("banner");
            this.imageDes = bundle.getString("description");
//            this.imageLink1 = bundle.getString("imageLink1 ");
//            this.imageLink2 = bundle.getString("imageLink2 ");
//            this.imageCurrentBatch = bundle.getString("imageCurrentBatch");
//            this.imageNextBatch1 = bundle.getString("imageNextBatch1 ");
//            this.imageNextBatch2 = bundle.getString("imageNextBatch2 ");
        }
    }

//    public String getImageVersion() {
//        return imageVersion;
//    }
//
//    public void setImageVersion(String imageVersion) {
//        this.imageVersion = imageVersion;
//    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public String getImageDes() {
        return imageDes;
    }

    public void setImageDes(String imageDes) {
        this.imageDes = imageDes;
    }

//    public String getImageDuration() {
//        return imageDuration;
//    }
//
//    public void setImageDuration(String imageDuration) {
//        this.imageDuration = imageDuration;
//    }
//
//    public String getImageCharges() {
//        return imageCharges;
//    }
//
//    public void setImageCharges(String imageCharges) {
//        this.imageCharges = imageCharges;
//    }

//    public String getImageLink1() {
//        return imageLink1;
//    }
//
//    public void setImageLink1(String imageLink1) {
//        this.imageLink1 = imageLink1;
//    }
//
//    public String getImageLink2() {
//        return imageLink2;
//    }
//
//    public void setImageLink2(String imageLink2) {
//        this.imageLink2 = imageLink2;
//    }
//
//    public String getImageCurrentBatch() {
//        return imageCurrentBatch;
//    }
//
//    public void setImageCurrentBatch(String imageCurrentBatch) {
//        this.imageCurrentBatch = imageCurrentBatch;
//    }
//
//    public String getImageNextBatch1() {
//        return imageNextBatch1;
//    }
//
//    public void setImageNextBatch1(String imageNextBatch1) {
//        this.imageNextBatch1 = imageNextBatch1;
//    }
//
//    public String getImageNextBatch2() {
//        return imageNextBatch2;
//    }
//
//    public void setImageNextBatch2(String imageNextBatch2) {
//        this.imageNextBatch2 = imageNextBatch2;
//    }

//    public String getImageIcon() {
//        return imageIcon;
//    }
//
//    public void setImageIcon(String imageIcon) {
//        this.imageIcon = imageIcon;
//    }
//
//    public String getImageBanner() {
//        return imageBanner;
//    }
//
//    public void setImageBanner(String imageBanner) {
//        this.imageBanner = imageBanner;
//    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

//    public String getStartingDate() {
//        return startingDate;
//    }
//
//    public void setStartingDate(String startingDate) {
//        this.startingDate = startingDate;
//    }
//
//    public String getDays() {
//        return days;
//    }
//
//    public void setDays(String days) {
//        this.days = days;
//    }
//    private String imageVersion;
    private String imageTitle;
//    private String startingDate;
//    private String days;
//    private String imageDuration;
//    private String imageCharges;
//    private String imageIcon;
//    private String imageBanner;
    private String imageDes;
    private Bitmap bitmap;

//    private String imageLink1;
//    private String imageLink2;
//    private String imageCurrentBatch;
//    private String imageNextBatch1;
//    private String imageNextBatch2;
}
