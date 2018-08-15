package com.sun.mysql.entity;

import com.sun.rnum.QmResType;

public class QmRes {
	protected Long resId;

    protected Integer resType;

    protected String tags;
    
    private String resName;
    
    private String itemPic;

    private String resNamePy;
    
    private String resSingerPy;

    private String key;
    
    private String singerName;
	//资源id
	private Integer  sourceId;
	private String resTypeName;
	//全拼文件名，太长可能被裁剪
	private String pinyinFileName;
	
	private String resSongLocalUrl;
	private String lyricLocal;
	
	public String getLyricLocal() {
		return lyricLocal;
	}


	public void setLyricLocal(String lyricLocal) {
		this.lyricLocal = lyricLocal;
	}


	public String getResSongLocalUrl() {
		return resSongLocalUrl;
	}


	public void setResSongLocalUrl(String resSongLocalUrl) {
		this.resSongLocalUrl = resSongLocalUrl;
	}


	public String getPinyinFileName() {
		return pinyinFileName;
	}


	public void setPinyinFileName(String pinyinFileName) {
		this.pinyinFileName = pinyinFileName;
	}


	public void setResTypeName(String resTypeName) {
		this.resTypeName = resTypeName;
	}


	public String getResTypeName() {
		if(this.resType!=null){
			return QmResType.getResTypeMap().get(this.resType);
		}
		
		return resTypeName;
	}

	
    public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public String getSingerName() {
		return singerName;
	}

	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}

	public String getResNamePy() {
		return resNamePy;
	}

	public void setResNamePy(String resNamePy) {
		this.resNamePy = resNamePy;
	}

	public String getResSingerPy() {
		return resSingerPy;
	}

	public void setResSingerPy(String resSingerPy) {
		this.resSingerPy = resSingerPy;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}

	public String getItemPic() {

		return itemPic;
	}

	public void setItemPic(String itemPic) {
		this.itemPic = itemPic;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public Long getResId() {
        return resId;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }

    public Integer getResType() {
        return resType;
    }

    public void setResType(Integer resType) {
        this.resType = resType;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = (tags == null)? null : tags.trim();
    }
}