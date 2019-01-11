package com.bwie.android.e_commerceproject;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

import java.util.HashMap;
import java.util.Map;

public class Zxing implements Reader {

    @Override
    public Result decode(BinaryBitmap image) throws NotFoundException, ChecksumException, FormatException {


        return null;
    }


    @Override
    public Result decode(BinaryBitmap image, Map<DecodeHintType, ?> hints) throws NotFoundException, ChecksumException, FormatException {
        return null;
    }

    /**
     * Resets any internal state the implementation has after a decode, to prepare it
     * for reuse.
     */
    @Override
    public void reset() {

    }

    public static String decodes(BinaryBitmap image)
            throws FormatException, ChecksumException, NotFoundException {
        Map<DecodeHintType, Object> hints = new HashMap<>();
        hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
        hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        hints.put(DecodeHintType.POSSIBLE_FORMATS, BarcodeFormat.QR_CODE);

        Result result = new QRCodeReader().decode(image, hints);
        return result.getText();
    }

    public static BinaryBitmap Bitmap2BinaryBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        int[] pixels = new int[width * height];
        //获取像素
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);

        RGBLuminanceSource source = new RGBLuminanceSource(width, height, pixels);

        return new BinaryBitmap(new HybridBinarizer(source));
    }

}
