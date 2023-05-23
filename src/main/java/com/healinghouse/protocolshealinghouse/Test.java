package com.healinghouse.protocolshealinghouse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.healinghouse.protocolshealinghouse.utils.ImageUtil;

public class Test {

	public static void main(String[] args) throws IOException {
		File file = new File("D://devendra.jpg");
		byte[] output = ImageUtil.compressImage(file);
		
		File outputFile = new File("D://dev.zip");
		Files.write(outputFile.toPath(), output);
		
	}

}
