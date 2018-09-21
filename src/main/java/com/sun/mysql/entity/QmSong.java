package com.sun.mysql.entity;

import com.sun.mysql.entity.QmRes;

import java.util.Date;

public class QmSong extends QmRes {
    private Long songId;
    private Long singerId;
    private Long albumId;
    private String songMid;
    private String songName;
    private String genre;
    private Integer hot;
    private Integer isonly;
    private Long kSongId;
    private String kSongMid;
    private String language;
    private Integer playable;
    private String publicTime;
    private String singerMid;
    private String singerName;
    private String singerPic500x500Local;
    private String singerTitle;
    private Integer sizeTry;
    private Integer songPlayTime;
   
    /*private String songH5Url;
    private String songPlayUrl;
    private String songPlayUrlHq;
    private String songPlayUrlSq;
    private String songPlayUrlStandard;*/

    private Integer songSize;
    private Integer songSizeHq;
    private Integer songSizeSq;
    private Integer songSizeStandard;
    private String songTitle;
    private Integer tryBegin;
    private Integer tryEnd;
    private Integer userOwnRule;
    private Date createTime;
    private String songPlayUrlLocal;
    private String songPlayUrlHqLocal;
    private String songPlayUrlSqLocal;
    private String songPlayUrlStandardLocal;
    private String lyricLocal;
    private Integer unplayableCode;
    private String unplayableMsg;
    private String updateMsg;//更新时的备注
    private Long mvId;
    private String country;// 歌手国家
    private String area;//歌手地区
    private Integer pick;//0未处理1选中-1未选中
    private int weight;//权重
    private String album_mid;
    private String albumDesc;
    private String albumPic;
    private Long dissId;
    private String dissName;
    private String dissPicUrl;
    private Integer isDefaultImage;//0否1是

    public Integer getIsDefaultImage() {
		return isDefaultImage;
	}

	public void setIsDefaultImage(Integer isDefaultImage) {
		this.isDefaultImage = isDefaultImage;
	}

	public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAlbumPic() {
		return albumPic;
	}

	public void setAlbumPic(String albumPic) {
		this.albumPic = albumPic;
	}

	public String getDissName() {
        return dissName;
    }

    public void setDissName(String dissName) {
        this.dissName = dissName;
    }

    public String getDissPicUrl() {
        return dissPicUrl;
    }

    public void setDissPicUrl(String dissPicUrl) {
        this.dissPicUrl = dissPicUrl;
    }

    public Long getDissId() {
        return dissId;
    }

    public void setDissId(Long dissId) {
        this.dissId = dissId;
    }

    public String getAlbumDesc() {
		return albumDesc;
	}

	public void setAlbumDesc(String albumDesc) {
		this.albumDesc = albumDesc;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Integer getPick() {
		return pick;
	}

	public void setPick(Integer pick) {
		this.pick = pick;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Long getMvId() {
		return mvId;
	}

	public void setMvId(Long mvId) {
		this.mvId = mvId;
	}

	public String getUpdateMsg() {
		return updateMsg;
	}

	public void setUpdateMsg(String updateMsg) {
		this.updateMsg = updateMsg;
	}

	public Long getSongId() {
		return songId;
	}

	public void setSongId(Long songId) {
		this.songId = songId;
	}

	public Long getSingerId() {
        return singerId;
    }

    public void setSingerId(Long singerId) {
        this.singerId = singerId;
    }
    public Long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

    public String getSongMid() {
        return songMid;
    }

    public void setSongMid(String songMid) {
        this.songMid = songMid == null ? null : songMid.trim();
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName == null ? null : songName.trim();
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre == null ? null : genre.trim();
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public Integer getIsonly() {
        return isonly;
    }

    public void setIsonly(Integer isonly) {
        this.isonly = isonly;
    }

    public Long getkSongId() {
        return kSongId;
    }

    public void setkSongId(Long kSongId) {
        this.kSongId = kSongId;
    }

    public String getkSongMid() {
        return kSongMid;
    }

    public void setkSongMid(String kSongMid) {
        this.kSongMid = kSongMid == null ? null : kSongMid.trim();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public Integer getPlayable() {
        return playable;
    }

    public void setPlayable(Integer playable) {
        this.playable = playable;
    }

    public String getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(String publicTime) {
        this.publicTime = publicTime == null ? null : publicTime.trim();
    }

    public String getSingerMid() {
        return singerMid;
    }

    public void setSingerMid(String singerMid) {
        this.singerMid = singerMid == null ? null : singerMid.trim();
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName == null ? null : singerName.trim();
    }

    public String getSingerPic500x500Local() {
        return singerPic500x500Local;
    }

    public void setSingerPic500x500Local(String singerPic500x500Local) {
        this.singerPic500x500Local = singerPic500x500Local == null ? null : singerPic500x500Local.trim();
    }

    public String getSingerTitle() {
        return singerTitle;
    }

    public void setSingerTitle(String singerTitle) {
        this.singerTitle = singerTitle == null ? null : singerTitle.trim();
    }

    public Integer getSizeTry() {
        return sizeTry;
    }

    public void setSizeTry(Integer sizeTry) {
        this.sizeTry = sizeTry;
    }

    public Integer getSongPlayTime() {
        return songPlayTime;
    }

    public void setSongPlayTime(Integer songPlayTime) {
        this.songPlayTime = songPlayTime;
    }

    public Integer getSongSize() {
        return songSize;
    }

    public void setSongSize(Integer songSize) {
        this.songSize = songSize;
    }

    public Integer getSongSizeHq() {
        return songSizeHq;
    }

    public void setSongSizeHq(Integer songSizeHq) {
        this.songSizeHq = songSizeHq;
    }

    public Integer getSongSizeSq() {
        return songSizeSq;
    }

    public void setSongSizeSq(Integer songSizeSq) {
        this.songSizeSq = songSizeSq;
    }

    public Integer getSongSizeStandard() {
        return songSizeStandard;
    }

    public void setSongSizeStandard(Integer songSizeStandard) {
        this.songSizeStandard = songSizeStandard;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle == null ? null : songTitle.trim();
    }

    public Integer getTryBegin() {
        return tryBegin;
    }

    public void setTryBegin(Integer tryBegin) {
        this.tryBegin = tryBegin;
    }

    public Integer getTryEnd() {
        return tryEnd;
    }

    public void setTryEnd(Integer tryEnd) {
        this.tryEnd = tryEnd;
    }

    public Integer getUserOwnRule() {
        return userOwnRule;
    }

    public void setUserOwnRule(Integer userOwnRule) {
        this.userOwnRule = userOwnRule;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSongPlayUrlLocal() {
        return songPlayUrlLocal;
    }

    public void setSongPlayUrlLocal(String songPlayUrlLocal) {
        this.songPlayUrlLocal = songPlayUrlLocal == null ? null : songPlayUrlLocal.trim();
    }

    public String getSongPlayUrlHqLocal() {
        return songPlayUrlHqLocal;
    }

    public void setSongPlayUrlHqLocal(String songPlayUrlHqLocal) {
        this.songPlayUrlHqLocal = songPlayUrlHqLocal == null ? null : songPlayUrlHqLocal.trim();
    }

    public String getSongPlayUrlSqLocal() {
        return songPlayUrlSqLocal;
    }

    public void setSongPlayUrlSqLocal(String songPlayUrlSqLocal) {
        this.songPlayUrlSqLocal = songPlayUrlSqLocal == null ? null : songPlayUrlSqLocal.trim();
    }

    public String getSongPlayUrlStandardLocal() {
        return songPlayUrlStandardLocal;
    }

    public void setSongPlayUrlStandardLocal(String songPlayUrlStandardLocal) {
        this.songPlayUrlStandardLocal = songPlayUrlStandardLocal == null ? null : songPlayUrlStandardLocal.trim();
    }

    public String getLyricLocal() {
        return lyricLocal;
    }

    public void setLyricLocal(String lyricLocal) {
        this.lyricLocal = lyricLocal == null ? null : lyricLocal.trim();
    }

    public Integer getUnplayableCode() {
        return unplayableCode;
    }

    public void setUnplayableCode(Integer unplayableCode) {
        this.unplayableCode = unplayableCode;
    }

    public String getUnplayableMsg() {
        return unplayableMsg;
    }

    public void setUnplayableMsg(String unplayableMsg) {
        this.unplayableMsg = unplayableMsg == null ? null : unplayableMsg.trim();
    }

	public String getAlbum_mid() {
		return album_mid;
	}

	public void setAlbum_mid(String album_mid) {
		this.album_mid = album_mid;
	}
}