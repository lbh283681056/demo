package com.base.util;

import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;

/**
 * 文字工具类
  * @ClassName: PaintUtil
  * @Description: 
  * @author Comsys-linbinghuang
  * @date 2014-11-3 下午4:31:59
  *
  */
public class PaintUtil {

    /**
     * 获取文字高度
     * 
     * @param paint
     * @return
     */
    public static float getTextHeight(Paint paint) {
        if (paint == null)
            return 0;
        FontMetrics fontMetrics = paint.getFontMetrics();
        return fontMetrics.bottom - fontMetrics.top;
    }

    /**
     * 获取文字宽度
     * 
     * @param paint
     * @param content
     * @return
     */
    public static float getTextWidth(Paint paint, String content) {
        if (paint == null)
            return 0;
        return paint.measureText(content);
    }
}
