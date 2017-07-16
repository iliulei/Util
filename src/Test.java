import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Test {
	
	public static String uploadPath = ""; 
	public static void main(String[] args) throws InterruptedException {
		
		boolean isTrue = true; 
		String uPath="";
		String path = "d:/test";
		String filePath = "d:/test/testA.batch";
		Thread listener = new Thread(new FileListenerThread(path,filePath));
		listener.start();
		while(isTrue){
			System.err.println(isTrue);
			if(!uploadPath.equals("")){
				uPath = uploadPath;
				isTrue=false;
			}
		}
		System.out.println(uPath);
		

		//listener.interrupt(); 
		//listener.
		//listener.exit = true; // 终止线程thread 
		//listener.join(); 
		
		//listener.join(); 
	        
		
	}
}

class FileListenerThread implements Runnable {

	private String path;
	private String filePath;


	private volatile boolean stop = false;

	@Override
	public void run() {
		// 需要监听的文件夹
		File file = new File(path);
		
		// 原始文件中的文件数量
		int orginalSize = file.list().length;
	
		
	
		while (stop) {
			File fileN = new File(filePath);
			
			System.out.println(fileN.exists());
			System.out.println(fileN.getName());
			 FileInputStream fis = null;
			 long s= 0;
//	         try {
//				fis = new FileInputStream(fileN);
//			} catch (FileNotFoundException e2) {
//				// TODO Auto-generated catch block
//				e2.printStackTrace();
//			}
//	         try {
//	        	 if(fis != null){
//				s= fis.available();};
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			 if(fileN.exists()){
				 s = fileN.length();
				 System.out.println(fileN.length());
			 }
			 
	         System.out.println(s);
//			int size = file.list().length;
//			if (size > orginalSize) {
//				System.out.println("文件新增，数量为： " + (size - orginalSize));
//				orginalSize = size;
//			} else if (size < orginalSize) {
//				System.out.println("文件删除，数量为： " + (orginalSize - size));
//				orginalSize = size;
//			}
			
			try {
				// 睡1秒
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}
			//System.out.println(size);
			if(s >0){
				stop = false;
				Test.uploadPath="http://www.baidu.com";
			}
		}
	}

	public void setPath(String path) {
		this.path = path;
	}

	public FileListenerThread(String path,String filePath) {
		this.filePath = filePath;
		this.path = path;
		stop = true;
	}
}
