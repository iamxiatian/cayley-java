package cayley;

import java.util.HashMap;
import java.util.Map;

/**
 * 网页抽取结果
 * @author lpf
 *
 */
public class FetchResult {
	/** 原始网页url */
	private String url;
    /** 自动跳转后的URL */
    private String followedUrl;
	/** 网页编码 */
	private String encoding;
	/** 网页内容，二进制 */
	private byte[] content;
	/** 网页http响应代码 */
	private int httpCode;
	/** http头部状态行 */
	private String statusLine;
	/** http响应头 */
	private Map<String, String> headers = new HashMap<String, String>();

	private String extField;
	/**原始列表页地址**/
	private String listPageUrl;

	
	public String getListPageUrl() {
		return listPageUrl;
	}

	public void setListPageUrl(String listPageUrl) {
		this.listPageUrl = listPageUrl;
	}

	public String getExtField() {
		return extField;
	}

	public void setExtField(String extField) {
		this.extField = extField;
	}

	public FetchResult() {
		super();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFollowedUrl() {
		return followedUrl;
	}

	public void setFollowedUrl(String followedUrl) {
		this.followedUrl = followedUrl;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}

	public String getStatusLine() {
		return statusLine;
	}

	public void setStatusLine(String statusLine) {
		this.statusLine = statusLine;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public String getHeader(String header) {
		return headers.get(header);
	}
}
