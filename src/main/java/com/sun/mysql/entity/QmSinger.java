package com.sun.mysql.entity;

import com.sun.mysql.entity.QmRes;
import com.sun.rnum.QmResType;

public class QmSinger extends QmRes {
    private Long singerId;

    private String singerName;

    private String singerMid;

    private String singerTranslatorName;

    private String country;

    private Integer genre;

    private Integer type;

    private Integer area;

    private String singerPic;
    
    private String singerPic300;
    
    private String singerPic500;

    private String singerPicLocal;
    
    private Integer isHot;
    //出生地
    private String birthplace;
    //职业
    private String profession;
    //作品
    private String production;
    //简介
    private String intro;
    
    
    
    
    public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getProduction() {
		return production;
	}

	public void setProduction(String production) {
		this.production = production;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public QmSinger(){
    	super.resType = QmResType.SINGER.getCode();
    }
    
    public QmSinger(String tags){
    	super.resType = QmResType.SINGER.getCode();
    	this.setTags(tags);
    }
    
	public String getSingerPic300() {
		return singerPic300;
	}

	public void setSingerPic300(String singerPic300) {
		this.singerPic300 = singerPic300;
	}

	public String getSingerPic500() {
		return singerPic500;
	}

	public void setSingerPic500(String singerPic500) {
		this.singerPic500 = singerPic500;
	}

	public Integer getIsHot() {
		return isHot;
	}

	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}

    public Long getSingerId() {
		return singerId;
	}

	public void setSingerId(Long singerId) {
		this.singerId = singerId;
	}

	public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName == null ? null : singerName.trim();
    }

    public String getSingerMid() {
        return singerMid;
    }

    public void setSingerMid(String singerMid) {
        this.singerMid = singerMid == null ? null : singerMid.trim();
    }

    public String getSingerTranslatorName() {
        return singerTranslatorName;
    }

    public void setSingerTranslatorName(String singerTranslatorName) {
        this.singerTranslatorName = singerTranslatorName == null ? null : singerTranslatorName.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public Integer getGenre() {
        return genre;
    }

    public void setGenre(Integer genre) {
        this.genre = genre;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getSingerPic() {
        return singerPic;
    }

    public void setSingerPic(String singerPic) {
        this.singerPic = singerPic == null ? null : singerPic.trim();
    }

    public String getSingerPicLocal() {
        return singerPicLocal;
    }

    public void setSingerPicLocal(String singerPicLocal) {
        this.singerPicLocal = singerPicLocal == null ? null : singerPicLocal.trim();
    }
}