package paramer;

import java.io.Serializable;

public class GetAdsParamer implements Serializable{
	private Integer pageIndex;
    private Integer pageSize;
    private Integer type;
    private Integer secondtype;
    private String provinceCode;
    private String cityCode;
    private String districtCode;
    private String townCode;
    private String keyword;
    private String method;
    
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getSecondtype() {
		return secondtype;
	}
	public void setSecondtype(Integer secondtype) {
		this.secondtype = secondtype;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getTownCode() {
		return townCode;
	}
	public void setTownCode(String townCode) {
		this.townCode = townCode;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	@Override
	public String toString() {
		return "Paramer [pageIndex=" + pageIndex + ", pageSize=" + pageSize + ", type=" + type + ", secondtype="
				+ secondtype + ", provinceCode=" + provinceCode + ", cityCode=" + cityCode + ", districtCode="
				+ districtCode + ", townCode=" + townCode + ", keyword=" + keyword + ", method=" + method + "]";
	}
    

}
