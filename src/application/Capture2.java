package application;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.Arrays;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
//import com.sun.jna.Pointer;
import com.sun.jna.Native;
//import com.sun.jna.*;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.win32.*;



public class Capture2 {
	
	static String[] WinBuffer = new String[10];
	static String[] WinSave = new String[5];
	static String[] selectFilepath = new String[5];
	
	
	public interface User32 extends StdCallLibrary {
        @SuppressWarnings("deprecation")
		User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class,
                W32APIOptions.DEFAULT_OPTIONS);

        HWND FindWindow(String lpClassName, String lpWindowName);
        //制亀酔 輩級聖 亜閃身
        int GetWindowRect(HWND handle, int[] rect);
        //GetWindowRect: 制亀酔 鉢檎 但 疎妊 尻至. 滴奄昔走 疎妊昔走 白哀軒澗汽 
        
        //HWND GetFocus();
        HWND GetForegroundWindow();
        //HWND GetActiveWindow();
        HWND GetWindowText(HWND handle, char[] buffer, int Max);
        void ShowWindow(HWND hWnd, int nCmdShow);
    }
    //--------------------
    //getRect : 背雁 梓端税 慎蝕(滴奄舛左)研 Rectangle 稽 軒渡
    // 2鯵税 Exception 神嫌亜 降持馬檎 戚楕拭辞 坦軒馬壱 硲窒廃 員生稽 軒渡?
    
    
	//五昔 五辞球
    public static void main(String[] args) {    
    	ReadWriteTime();
    	HandleUp();
    	   	
    }
    
    public static void ReadWriteTime() {
    	ReadwinBuffer();
    	ReadwinSave();
    	
    	//selectFilepath[0]="D:\\Dtest\\BlueStacks\\";
  	
    	
    }
    
	
    public static void HandleUp()  {
    	HWND hwnd0 = User32.INSTANCE.GetForegroundWindow();
        User32.INSTANCE.ShowWindow(hwnd0,6);
       // 銚坦覗稽益轡 但聖 需延陥.

    	char[] windowText = new char[512];
    	
    	HWND hwnd = User32.INSTANCE.GetForegroundWindow();
        User32.INSTANCE.GetWindowText(hwnd, windowText, 512);
        // 亜舌 蒋拭 赤澗 但税 制亀酔 輩級聖 達焼 制亀酔 薦鯉聖 鋼発廃陥.
        
        String windowName = Native.toString(windowText);
        //是税 制亀酔 薦鯉聖 String 展脊生稽 煽舌.
        
        newBuffer(windowName);
        //けけけけけけけけけ 獄遁拭 戚硯 蓄亜.
        
        int[] rect;
        try {
            rect = Capture2.getRect(windowName);
            //制亀酔 薦鯉税 輩級聖 達焼辞 疎妊研 姥廃陥.
            System.out.printf("薄仙 但精  \"%s\" 戚悟, 坪格 疎妊澗 %s脊艦陥\n",windowName, Arrays.toString(rect));
           
            
            
            Shot3(rect, windowName);
            //銚坦 & 煽舌
            System.out.println("煽舌 刃戟");
            Thread.sleep(500);
           
        } catch (Capture2.WindowNotFoundException e) {
            e.printStackTrace();
        } catch (Capture2.GetWindowRectException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        User32.INSTANCE.ShowWindow(hwnd0,9);
        //需医揮 銚坦覗稽益轡 但聖 差姥獣轍陥.
    	
        WritewinBuffer(); //獄遁 床奄
    }
	
	
    public static int[] getRect(String windowName) throws WindowNotFoundException,
            GetWindowRectException {
        HWND hwnd = User32.INSTANCE.FindWindow(null, windowName);
        
        if (hwnd == null) {
            throw new WindowNotFoundException("", windowName);
        }//醗失鉢 制亀酔亜 蒸生檎 硲窒廃 員生稽 windownotfoundException 拭君研 左浬陥.

        int[] rect = {0, 0, 0, 0};
        User32.INSTANCE.GetWindowRect(hwnd, rect);
        //但 輩級税 薄仙 制亀酔 疎妊研 rect拭 煽舌馬壱 掩戚研 result拭 煽舌
        int sum = rect[0] + rect[1] + rect[2] + rect[3];
        if (sum == 0) {
            throw new GetWindowRectException(windowName);
        }//葵戚 0蟹神檎 神嫌坦軒廃陥.
        return rect;
        //疎妊 葵聖 鋼発廃陥.
    }

    
    
    @SuppressWarnings("serial")
    public static class WindowNotFoundException extends Exception {
        public WindowNotFoundException(String className, String windowName) {
            super(String.format("Window null for className: %s; windowName: %s",
                    className, windowName));
        }
    }

    @SuppressWarnings("serial")
    public static class GetWindowRectException extends Exception {
        public GetWindowRectException(String windowName) {
            super("Window Rect not found for " + windowName + "or because it's null!");
        }
    }//是 砧 五辞球澗 神嫌 坦軒

    @SuppressWarnings("serial")
    public static class IllegalArgumentException extends Exception {
        public IllegalArgumentException() {
            super(String.format("Window is null & Rectangle is null!"));
        }
    }// Rectangle width and height must be > 0 坦軒

    
    //煽舌
    public static void Shot3(int[] A,String windowName) {
		String saveFilePath = "C:\\SelectedCapture\\";
        String saveFileName = "ScreenShotTest";
        String saveFileExtension = "png";
        
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Calendar cal = Calendar.getInstance();
    	String time = sdf.format(cal.getTime());
        
        
        int height,width = 0;
        try {
            Robot robot = new Robot();
            
           // height = A[3]-A[1]-10; //株戚
           // width =A[2]-A[0]-12;  //賑
            height = A[3]-A[1]; //株戚
            width =A[2]-A[0];  //賑
            
           System.out.println("x,y ="+A[0]+","+A[1]+"  株戚:"+width+"  賑:"+height);
          
           if(height<0)
            	height = A[1] - A[3];
            if(width<0)
            	width = A[0]-A[2];
            Rectangle rectangle = new Rectangle(A[0],A[1],width,height);
            //鉢檎税 滴奄研 条嬢 鎧澗 五社球
            //紫唖莫 因娃研 煽舌廃陥 
            
            BufferedImage image = robot.createScreenCapture(rectangle);
            //BufferedImage : 什滴鍵生稽採斗 石備澗 波漆聖 匂敗廃 戚耕走 持失
            image.setRGB(0,0,100);
            
            //督析 煽舌
                  
            
            for(int i = 0; i<5; i++)
            {
           	System.out.println("WinSave 窒径 掻 - "+WinSave[i]);
            	if(WinSave[i].equals(windowName))
            	{
            		System.out.println("蓄旋刃戟");
            		saveFilePath = selectFilepath[i];
            	}
            	
            }
            System.out.println("薦鯉 : "+windowName);
            System.out.println("煽舌井稽 : "+saveFilePath);
            
            File file = new File(saveFilePath+saveFileName+"_"+time+"."+saveFileExtension);
            if (!file.exists()) {
            		file.mkdirs(); //虹希亜 蒸聖 井酔 持失.
        		    System.out.println("虹希亜 持失鞠醸柔艦陥.");
            }
            
            
            ImageIO.write(image, saveFileExtension, file);
            
            
            
        } catch (Exception e){
            e.printStackTrace();
        }
	}
    
   
  //けけけけけけけけけけけけけけけけけけけけけけけけけけけけけけけけけけけけけけけけ
    
    
    
    public static void ReadwinBuffer() {
    	// Buffer.txt研 石嬢辞 String 10匝稽 煽舌廃陥.
    	
    	try{
    		BufferedReader in = new BufferedReader(new FileReader("Save/buffer.txt"));
    		
    		
    		String text;
    		int i = 0 ;
    	
    		
    		while((text = in.readLine()) != null)
    		{
    			WinBuffer[i] = text;
    			i++;
    		}
    
				in.close();
		
    	} catch (Exception e) { 
		      e.printStackTrace();
		  }
}

public static void WritewinBuffer() {
	// winBuffer String 10匝聖Buffer.txt拭 煽舌廃陥.
	
	try{
		BufferedWriter out = new BufferedWriter(new FileWriter("Save/buffer.txt"));
		//BufferedWriter out = new BufferedWriter(new FileWriter("Save/WindowName_buffer.txt"));
		
		//String text;
		//char[] Title = new char[255];
		
		for(int i=0; i<10; i++)
		{
			if(WinBuffer[i] == null)
			{
				out.write(" \n");
			}
			else
				out.write(WinBuffer[i]+"\n");
		}	
		
		out.close();
	
	} catch (Exception e) { 
	      e.printStackTrace();
	  }
	

}

public static void ReadwinSave() {
	// save.txt研 石嬢辞 String 5匝稽 煽舌廃陥.
	
	try{
		BufferedReader in = new BufferedReader(new FileReader("Save/save.txt"));
		BufferedReader in2 = new BufferedReader(new FileReader("Save/path.txt"));
		
		String text;
		
		int i =0;
		while((text = in.readLine()) != null)
		{
			WinSave[i] = text;
			i++;
		}
		i=0;
		while((text = in2.readLine()) != null)
		{
			selectFilepath[i] = text;
			i++;
		}

			in.close();
			in2.close();
	
	} catch (Exception e) { 
	      e.printStackTrace();
	  }
}

public static void WritewinSave() {
	// save String 10匝聖 save.txt拭 煽舌廃陥.+煽舌井稽亀 魚稽 煽舌.
	
	try{
		BufferedWriter out = new BufferedWriter(new FileWriter("Save/save.txt"));
		BufferedWriter out2 = new BufferedWriter(new FileWriter("Save/path.txt"));
		
		for(int i=0; i<5; i++)
		{
			out.write(WinSave[i]+"\n");
			out2.write(selectFilepath[i]+"\n");
		}	
		
		out.close();
		out2.close();
	
	} catch (Exception e) { 
	      e.printStackTrace();
	  }
	

}

public static void newBuffer(String w) {
	for(int i = 9; i>0;i--)
	{
		WinBuffer[i] = WinBuffer[i-1];
	}
	WinBuffer[0] = w;
}

 
    
    
    
}