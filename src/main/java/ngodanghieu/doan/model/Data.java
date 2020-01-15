package ngodanghieu.doan.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
public class Data implements Serializable {
    @SerializedName("id")
    private String id;
    private Object title;
    private Object description;
    private Integer datetime;
    private String type;
    private Boolean animated;
    private Integer width;
    private Integer height;
    private Integer size;
    private Integer views;
    private Integer bandwidth;
    private Object vote;
    private Boolean favorite;
    private Object nsfw;
    private Object section;
    private Object accountUrl;
    private Integer accountId;
    private Boolean isAd;
    private Boolean inMostViral;
    private Boolean hasSound;
    private Integer adType;
    private String adUrl;
    private String edited;
    private Boolean inGallery;
    private String deletehash;
    private String name;
    @SerializedName("link")
    private String link;
}
