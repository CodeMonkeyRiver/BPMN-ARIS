package com.joinfun.wj.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class IOUtils {
	
	/** 
     * ��ȡ������Ϊbyte[]���� 
     */  
    public static byte[] read(InputStream instream) throws IOException  
    {  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        byte[] buffer = new byte[1024];  
        int len = 0;  
        while ((len = instream.read(buffer)) != -1)  
        {  
            bos.write(buffer, 0, len);  
        }  
        return bos.toByteArray();  
    }  
    
}
