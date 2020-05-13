package com.iteroa.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CompleteMultipartUploadResult;
import com.aliyun.oss.model.UploadFileRequest;
import com.aliyun.oss.model.UploadFileResult;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 阿里云oss上传
 * 
 * @author Daniel Duan
 *
 */
public class OSSManagement {
	private String endPoint = "http://oss-cn-beijing.aliyuncs.com/";
	private String accessKeyId = "";
	private String accessKeySecret = "";
	private String bucketName = "";
	private OSSClient ossClient;

	public OSSManagement() {
		String ossStr = ApiURL.getOSS();
		String[] oss = ossStr.split(";");
		this.accessKeyId = oss[0];
		this.accessKeySecret = oss[1];
		this.bucketName = oss[2];
		this.ossClient = new OSSClient(this.endPoint, this.accessKeyId, this.accessKeySecret);
	}

	public String getOSSURL(File file, String fileName) throws Throwable {
		SimpleDateFormat dateFormat;
		dateFormat = new SimpleDateFormat("yyyyMMdd");
		String currentDate = dateFormat.format(Calendar.getInstance().getTime());

		String year = currentDate.substring(0, 4);
		String month = currentDate.substring(4, 6);
		String day = currentDate.substring(6, 8);
		String date = year + "/" + month + "/" + day;

		UploadFileRequest uploadFileRequest = new UploadFileRequest(this.bucketName,
				"healthcare/" + fileName + "/" + date + "/" + file.getName());

		uploadFileRequest.setUploadFile(file.getAbsolutePath());

		uploadFileRequest.setTaskNum(5);

		uploadFileRequest.setPartSize(1048576L);

		uploadFileRequest.setEnableCheckpoint(true);

		UploadFileResult uploadResult = this.ossClient.uploadFile(uploadFileRequest);

		CompleteMultipartUploadResult multipartUploadResult = uploadResult.getMultipartUploadResult();

		return multipartUploadResult.getLocation();
	}

	public void Shutdown() {
		this.ossClient.shutdown();
	}

	public static void main(String[] args) {
		OSSManagement oss = new OSSManagement();
		try {
			String url = oss.getOSSURL(new File("D:\\itearoa\\XinDian\\result\\pppppp111112.JPG"), "XinDian");
			System.out.println(url);
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			if (oss != null) {
				oss.Shutdown();
			}
		}
	}
}