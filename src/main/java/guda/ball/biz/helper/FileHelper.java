package guda.ball.biz.helper;

import guda.ball.util.DateHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;


public class FileHelper {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String  defaultFileExt =  "_180x180.png";

    private String fileSever;
    private String fileDir;

    public String getFileSever() {
        return fileSever;
    }

    public void setFileSever(String fileSever) {
        this.fileSever = fileSever;
    }

    public String getFileDir() {
        return fileDir;
    }

    public void setFileDir(String fileDir) {
        this.fileDir = fileDir;
    }

    public String putFileToPlace(MultipartFile file) throws IOException {
		File newFile = getNewFile(file, getFileDir());
		file.transferTo(newFile);
        String absolutePath = newFile.getAbsolutePath();
        File resizeFile = new File(absolutePath + defaultFileExt);
        ImageUtil.resize(new File(absolutePath),resizeFile,180);
		return DateHelper.formatYMD(new Date()) + "/" + resizeFile.getName() + "?mod="+resizeFile.lastModified();
	}



	private File getNewFile(MultipartFile file, String path) {
		String fileName = fixFileName(file.getOriginalFilename());
        String f = getDestinationFileName(fileName);
        path = path + File.separator + DateHelper.formatYMD(new Date());
        File file1 = new File(path);
        if(!file1.exists()){
            file1.mkdirs();
        }
        File newFile = new File(path ,f);

		return newFile;
	}

	private String fixFileName(String fileName) {
		while(fileName.contains("..")){
			fileName = fileName.replace("..", ".");
		}
		
		if(fileName.lastIndexOf(".")==0){
			fileName = System.currentTimeMillis() + fileName;
		}
		if(fileName.startsWith(".")){
			fileName = fileName.substring(1, fileName.length());
		}
		return fileName;
	}


	public static String getDestinationFileName(String fileName) {
		return UUID.randomUUID().toString() ;
	}


	public boolean isImage(MultipartFile file) {
		return "image/pjpeg".equals(file.getContentType()) || "image/jpeg".equals(file.getContentType()) || "image/gif".equals(file.getContentType())
				|| "image/png".equals(file.getContentType());
	}


	public boolean deleteFile(String fileName, String path) {
		String file = path + fileName;
		File f = new File(file);
		if (!f.exists()) {
			logger.debug("FileService.deleteFile(): can't delete " + fileName + " - not exist");
		}
		if (!f.canWrite()) {
			logger.debug("FileService.deleteFile(): can't delete " + fileName + " - not allowed to delete");
		}
		if (f.isDirectory()) {
			logger.debug("FileService.deleteFile(): can't delete " + fileName + " - not file, but directory");
	    }
		return f.delete();
	}
	
}