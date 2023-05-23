package com.healinghouse.protocolshealinghouse.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtil {

	public static byte[] compressImage(File file) {
		Deflater deflater = new Deflater(Deflater.BEST_COMPRESSION);
		byte[] output = null;
		try {
			byte[] inputData = Files.readAllBytes(file.toPath());
			deflater.setInput(inputData);
			deflater.finish();

			ByteArrayOutputStream baos = new ByteArrayOutputStream(inputData.length);
			byte[] tmp = new byte[4*1024];
			while(!deflater.finished()) {
				int size = deflater.deflate(tmp);
				baos.write(tmp, 0 , size);
			}
			output = baos.toByteArray();
			baos.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return output;

	}

	public static byte[] decompressImage(File file) {
		Inflater inflater = new Inflater();
		byte[] data = null;
		byte[] output = null;
		try {
			data = Files.readAllBytes(file.toPath());
			inflater.setInput(data);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
			byte[] tmp = new byte[4*1024];

			while (!inflater.finished()) {
				int count = inflater.inflate(tmp);
				outputStream.write(tmp, 0, count);
			}
			outputStream.close();
			output = outputStream.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return output;
	}
}
